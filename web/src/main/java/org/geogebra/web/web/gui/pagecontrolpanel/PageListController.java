package org.geogebra.web.web.gui.pagecontrolpanel;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.geogebra.common.main.Feature;
import org.geogebra.common.move.ggtapi.models.json.JSONArray;
import org.geogebra.common.move.ggtapi.models.json.JSONException;
import org.geogebra.common.move.ggtapi.models.json.JSONObject;
import org.geogebra.common.move.ggtapi.models.json.JSONTokener;
import org.geogebra.common.util.debug.Log;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.main.GgbFile;
import org.geogebra.web.html5.main.PageListControllerInterface;
import org.geogebra.web.web.gui.applet.GeoGebraFrameBoth;

/**
 * controller for page actions, such as delete or add slide
 * 
 * @author csilla
 *
 */
public class PageListController implements PageListControllerInterface {
	/**
	 * application {@link AppW}
	 */
	protected AppW app;
	/**
	 * list of slides (pages)
	 */
	private ArrayList<PagePreviewCard> slides;
	private PagePreviewCard selectedCard;
	private int dragIndex = -1;
	private PagePreviewCard lastDragTarget;
	private PagePreviewCard dragCard;

	/**
	 * @param app
	 *            {@link AppW}
	 */
	public PageListController(AppW app) {
		this.app = app;
		slides = new ArrayList<>();
	}

	/**
	 * @return list of slides; never null
	 */
	public ArrayList<PagePreviewCard> getCards() {
		return slides != null ? slides : new ArrayList<PagePreviewCard>();
	}

	public GgbFile getSlide(int index) {
		if(slides == null){
			return null;
		}
		if(selectedCard == slides.get(index)){
			return app.getGgbApi().createArchiveContent(true);
		}
		return slides.get(index).getFile();
	}

	/**
	 * loads the slide with index i from the list
	 * 
	 * @param curSelCard
	 *            currently selected card
	 * 
	 * @param i
	 *            index of the slide to load
	 * @param newPage
	 *            true if slide is new slide
	 */
	public void loadSlide(PagePreviewCard curSelCard, int i, boolean newPage) {
		if (slides == null) {
			return;
		}
		// save file status of currently selected card
		savePreviewCard(curSelCard);
		try {
			if (newPage) {
				// new file
				app.fileNew();
			} else {
				// load last status of file
				app.resetPerspectiveParam();
				app.loadGgbFile(slides.get(i).getFile());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save current file to selected card
	 * 
	 * @param card
	 *            selected card
	 */
	public void savePreviewCard(PagePreviewCard card) {
		if (card != null) {
			card.setFile(app.getGgbApi().createArchiveContent(true));
		}
	}
	
	/**
	 * @param dest
	 *            slide to load
	 */
	public void changeSlide(PagePreviewCard dest) {
		try {
			app.resetPerspectiveParam();
			app.loadGgbFile(dest.getFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Duplicates slide
	 * 
	 * @param sourceCard
	 *            to duplicate.
	 * @return the new, duplicated card.
	 */
	public PagePreviewCard duplicateSlide(PagePreviewCard sourceCard) {
		if (sourceCard == selectedCard) {
			savePreviewCard(sourceCard);
		}
		
		PagePreviewCard dup = PagePreviewCard.duplicate(sourceCard);
		int dupIdx = dup.getPageIndex();
		
		boolean lastCard = (dupIdx == slides.size());
		
		slides.add(dupIdx, dup);
		
		if (!lastCard) {
			updatePageIndexes(dupIdx);
		}
		
		changeSlide(dup);
		setCardSelected(dup);

		return dup;
	}

	/**
	 * adds a new slide to the list
	 * 
	 * @return index of the added slide
	 */
	public PagePreviewCard addSlide() {
		if (slides == null) {
			slides = new ArrayList<>();
		}
		PagePreviewCard previewCard = new PagePreviewCard(
				app, slides.size(), new GgbFile());
		slides.add(previewCard);
		return previewCard;
	}

	/**
	 * removes the slide with given index from the list
	 * 
	 * @param index
	 *            of the slide to be removed
	 */
	public void removeSlide(int index) {
		if (slides == null || index >= slides.size()) {
			return;
		}
		slides.remove(index);
	}

	/**
	 * gets the number of slides in the list
	 * 
	 * @return number of slides
	 */
	public int getSlideCount() {
		return slides.size();
	}

	@Override
	public void resetPageControl() {
		if (!app.has(Feature.MOW_MULTI_PAGE)) {
			return;
		}
		// clear preview card list
		slides = new ArrayList<>();
		// clear gui
		((GeoGebraFrameBoth) app.getAppletFrame()).getPageControlPanel()
				.reset();
	}
	
	private void updatePageIndexes(int masterIdx) {
		for (int i = masterIdx; i < slides.size(); i++) {
			slides.get(i).setPageIndex(i);
		}
	}

	public String getStructureJSON() {
		try {
			JSONObject book = new JSONObject();
			JSONObject chapter = new JSONObject();
			JSONArray pages = new JSONArray();
			if (slides != null) {
				for (int i = 0; i < slides.size(); i++) {
					JSONArray elements = new JSONArray();
					elements.put(new JSONObject().put("id",
							GgbFile.SLIDE_PREFIX + i));
					pages.put(new JSONObject().put("elements", elements));
				}
			}
			chapter.put("pages", pages);
			book.put("chapters", new JSONArray().put(chapter));
			return book.toString();
		} catch (JSONException e) {
			Log.warn("can't save slides:" + e.getMessage());
		}
		return "{}";
	}

	public boolean loadSlides(GgbFile archive) {
		if (!archive.containsKey(GgbFile.STRUCTURE_JSON)) {
			return false;
		}
		String structure = archive.remove(GgbFile.STRUCTURE_JSON);
		slides.clear();
		Log.debug(structure);
		try {
			JSONObject response = new JSONObject(new JSONTokener(structure));
			JSONArray pages = response.getJSONArray("chapters").getJSONObject(0)
					.getJSONArray("pages");
			for (int i = 0; i < pages.length(); i++) {
				slides.add(new PagePreviewCard(app, i, filter(archive,
						pages.getJSONObject(i).getJSONArray("elements")
								.getJSONObject(0).getString("id"))));
			}
			app.loadGgbFile(slides.get(0).getFile());
			/// TODO this breaks MVC
			((GeoGebraFrameBoth) app.getAppletFrame()).getPageControlPanel()
					.update();
			setCardSelected(slides.get(0));
		} catch (Exception e) {
			Log.debug(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Sets the selected page visible and highlights the preview card
	 * 
	 * @param previewCard
	 *            selected preview card
	 */
	protected void setCardSelected(PagePreviewCard previewCard) {
		if (selectedCard != null) {
			// deselect old selected card
			selectedCard.removeStyleName("selected");
		}
		// select new card
		previewCard.addStyleName("selected");
		selectedCard = previewCard;
	}

	private static GgbFile filter(GgbFile archive, String prefix) {
		GgbFile ret = new GgbFile();
		for (Entry<String, String> e : archive.entrySet()) {
			if (e.getKey().startsWith(prefix + "/")
					|| e.getKey().startsWith(GgbFile.SHARED_PREFIX)) {
				ret.put(e.getKey().substring(prefix.length() + 1),
						e.getValue());
			}
		}
		return ret;
	}

	/**
	 * @param srcIdx
	 *            source index
	 * @param destIdx
	 *            destination index
	 */
	public void reorder(int srcIdx, int destIdx) {
		PagePreviewCard src = slides.get(srcIdx);
		slides.remove(srcIdx);
		slides.add(destIdx, src);
		updatePageIndexes(Math.min(srcIdx, destIdx));
		
	}

	@Override
	public int cardIndexAt(int x, int y) {
		int result = -1;
		for (PagePreviewCard card: slides) {
			if (card.getPageIndex() != dragIndex && card.isHit(x, y)) {
				result = card.getPageIndex();
			}
		}
		return result;
	}
	
	public boolean dropTo(int x, int y) {
		int destIdx = lastDragTarget != null ? lastDragTarget.getPageIndex()
				: -1;
		if (destIdx != -1) {
			Log.debug("drag: " + dragIndex  + " drop to " + destIdx);

			reorder(dragIndex, destIdx);
			dragIndex = -1;
			clearSpaces();
			return true;
		} 
		
		Log.debug("card was not hit");
		return false;
	}

	/**
	 * Add style to a given card, removes from all other ones.
	 * 
	 * @param pageIndex
	 *            the card index to add style to.
	 * @param name
	 *            the style name.
	 */
	public void styleCard(int pageIndex, String name) {
		for (PagePreviewCard card : slides) {
			if (card.getPageIndex() == pageIndex) {
				card.addStyleName(name);
			} else {
				card.removeStyleName(name);
			}
		}
	}

	/**
	 * Updates the preview image of the active preview card
	 */
	public void updatePreviewImage() {
		if (selectedCard != null) {
			selectedCard.updatePreviewImage();
		}
	}

	/**
	 * load existing page
	 * 
	 * @param index
	 *            index of page to load
	 * @param newPage
	 *            true if slide is new page
	 */
	protected void loadPage(int index, boolean newPage) {
		loadSlide(selectedCard, index, newPage);
		setCardSelected(getCards().get(index));
	}

	private void clearSpaces() {
		clearSpaces(-1);
	}

	private void clearSpaces(int index) {
		for (PagePreviewCard card: slides) {
			if (index != card.getPageIndex()) {
				card.removeStyleName("spaceBefore");
				card.removeStyleName("spaceAfter");
				card.removeStyleName("spaceBeforeAnimated");
				card.removeStyleName("spaceAfterAnimated");
			}
		}
	}

	@Override
	public void startDrag(int x, int y) {
		dragIndex = cardIndexAt(x, y);
		if (dragIndex != -1) {
			dragCard = slides.get(dragIndex);
			dragCard.setAbsolutePosition();
			if (dragIndex > 0 && dragIndex < slides.size()) {
				// Making room to "take out" the card from list.
				lastDragTarget = slides.get(dragIndex - 1);
				lastDragTarget.addStyleName("spaceAfter");
			}
		}
	}

	@Override
	public void startDrag(int pageIndex) {
		clearSpaces();
		dragIndex = pageIndex;
		PagePreviewCard card = slides.get(pageIndex);
		if (dragIndex != slides.size() - 1) {
			card.addStyleName("spaceBeforeAnimated");
		}
	}
	
	@Override
	public void drag(int x, int y) {
		if (dragCard == null) {
			return;
		}

		dragCard.setDragPosition(0, y);

		int idx = cardIndexAt(x, y);
		 if (idx == -1) {
			// Log.debug("[DND] no card at (" + x + ", " + y + ")");
			return;
		 }
		 PagePreviewCard target = slides.get(idx);
		if (target == null) {
			return;
		}

		int targetIdx = target.getPageIndex();
		int lastTargetIdx = lastDragTarget != null
				? lastDragTarget.getPageIndex()
				: -1;
		if (lastTargetIdx != -1 && lastTargetIdx != targetIdx) {
			lastDragTarget.removeStyleName("highlight");
			lastDragTarget.removeStyleName("spaceAfterAnimated");
			lastDragTarget.removeStyleName("spaceBeforeAnimated");
			if (targetIdx < dragIndex) {
				target.addStyleName("spaceBeforeAnimated");
			} else {
				target.addStyleName("spaceAfterAnimated");
			}
		}

		target.addStyleName("highlight");
		lastDragTarget = target;
	}
	
	@Override
	public void stopDrag() {
		if (dragCard != null) {
			dragCard.clearPosition();

		}
		clearSpaces();
		dragCard = null;
	}

}
