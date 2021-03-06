package org.geogebra.keyboard.web;

import org.geogebra.common.euclidian.event.PointerEventType;
import org.geogebra.web.html5.gui.util.ClickEndHandler;
import org.geogebra.web.html5.gui.util.ClickStartHandler;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
/**
 * A button of the {@link TabbedKeyboard}.
 */
public class KeyBoardButtonBase extends SimplePanel {

	private String caption;
	/**
	 * the feedback that is returned when the button is clicked
	 */
	protected String feedback;
	/**
	 * the label that is displayed on the button
	 */
	protected Label label;
	/**
	 * @param caption
	 *            text of the button
	 * @param feedback
	 *            String to send if click occurs
	 * @param handler
	 *            {@link ClickHandler}
	 */
	public KeyBoardButtonBase(String caption, String feedback,
			ButtonHandler handler) {
		this(handler);
		this.label = new Label();
		this.setWidget(label);
		setCaption(checkThai(caption));
		this.feedback = feedback;
	}

	// https://codepoints.net/search?gc=Mn
	// these Thai characters need a placeholder added to display nicely
	private static String checkThai(String str) {
		if ("\u0E31\u0E33\u0E34\u0E35\u0E36\u0E37\u0E38\u0E39\u0E3A\u0E47\u0E48\u0E49\u0E4A\u0E4B\u0E4C\u0E4D"
				.indexOf(str) > -1) {
			return "\u25CC" + str;
		}
		return str;
	}

	/**
	 * @param caption
	 *            text of the button and feedback (same)
	 * @param handler
	 *            {@link ClickHandler}
	 */
	public KeyBoardButtonBase(String caption, ButtonHandler handler) {
		this(caption, caption, handler);
	}

	private native void addWave(Element element) /*-{
		$wnd.Waves.initialized || $wnd.Waves.displayEffect({
			"duration" : 100
		});
		$wnd.Waves.initialized = true;
		$wnd.Waves.attach(element);
	}-*/;

	/**
	 * Constructor for subclass {@link KeyBoardButtonFunctionalBase}
	 * 
	 * @param handler
	 *            {@link ClickHandler}
	 */
	protected KeyBoardButtonBase(final ButtonHandler handler) {
		ClickStartHandler.init(this, new ClickStartHandler(true, true) {
			@Override
			public void onClickStart(int x, int y, PointerEventType type) {
				handler.onClick(KeyBoardButtonBase.this, type);
			}
		});
		// only used for preventDefault and stopPropagation
		ClickEndHandler.init(this, new ClickEndHandler(true, true) {
			@Override
			public void onClickEnd(int x, int y, PointerEventType type) {
				// do nothing
			}
		});
		addStyleName("KeyBoardButton");
		this.getElement().setAttribute("role", "button");
		if (handler.hasTouchFeedback()) {
			this.addStyleName("waves-effect");
			this.addStyleName("waves-keyboard");
			this.addStyleName("btn");
			addWave(this.getElement());
		}
	}

	/**
	 * @return text of the button
	 */
	public String getCaption() {
		return this.caption;
	}

	/**
	 * @param caption
	 *            text of the button
	 * @param feedback1
	 *            feedback of the button (to be inserted in textfield)
	 */
	public void setCaption(String caption, String feedback1) {
		this.getElement().setAttribute("aria-label", caption);
		this.caption = caption;
		if (feedback1 != null) {
			this.feedback = feedback1;
		}
		if (caption.length() > 1 && caption.indexOf('^') > -1) {
			int index = caption.indexOf('^');
			this.label.setText(caption.substring(0, index));
			Element sup = Document.get().createElement("sup");
			sup.appendChild(Document.get().createTextNode(
			        caption.substring(index + 1)));
			sup.getStyle().setFontSize(14, Unit.PX);
			sup.getStyle().setFontStyle(FontStyle.NORMAL);
			this.label.getElement().appendChild(sup);
			this.addStyleName("sup");
		} else if (caption.length() > 1 && caption.indexOf('_') > -1) {
			int index = caption.indexOf('_');
			this.label.setText(caption.substring(0, index));
			Element sub = Document.get().createElement("sub");
			sub.appendChild(Document.get().createTextNode(
			        caption.substring(index + 1)));
			sub.getStyle().setFontSize(14, Unit.PX);
			sub.getStyle().setFontStyle(FontStyle.NORMAL);
			this.label.getElement().appendChild(sub);
			this.addStyleName("sub");
		} else {
			this.label.setText(caption);
		}
	}

	/**
	 * @param caption
	 *            text of the button (also used as new feedback)
	 */
	public final void setCaption(String caption) {
		setCaption(caption, caption);
	}

	/**
	 * @return the String to be sent if a click occurs
	 */
	public String getFeedback() {
		return this.feedback;
	}
}
