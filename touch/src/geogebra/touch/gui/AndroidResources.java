package geogebra.touch.gui;

import org.vectomatic.dom.svg.ui.SVGResource;
import org.vectomatic.dom.svg.ui.SVGResource.Validated;

import com.google.gwt.core.client.GWT;

public interface AndroidResources extends CommonResources {
	
	public static AndroidResources INSTANCE = GWT.create(AndroidResources.class);

	// Dialogs
	
	@Override
	@Source("icons/svg/android/button_ok.svg")
	@Validated(validated = false)
	SVGResource dialog_ok();
	
	@Override
	@Source("icons/svg/android/button_cancel.svg")
	@Validated(validated = false)
	SVGResource dialog_cancel();
	
	@Override
	@Source("icons/svg/android/button_trashcan.svg")
	@Validated(validated = false)
	SVGResource dialog_trash();

	// Header
	
	@Override
	@Source("icons/svg/android/document-new.svg")
	@Validated(validated = false)
	SVGResource document_new();
	
	@Override
	@Source("icons/svg/android/document-open.svg")
	@Validated(validated = false)
	SVGResource document_open();

	@Override
	@Source("icons/svg/android/document-save.svg")
	@Validated(validated = false)
	SVGResource document_save();

	@Override
	@Source("icons/svg/android/menu_edit_undo.svg")
	@Validated(validated = false)
	SVGResource undo();
	
	@Override
	@Source("icons/svg/android/menu_edit_redo.svg")
	@Validated(validated = false)
	SVGResource redo();	
	
	// GeoGebraTube View
	
	@Override
	@Source("icons/svg/android/document-open.svg")
	@Validated(validated = false)
	SVGResource search();
	
	@Override
	@Source("icons/svg/android/menu_back.svg")
	@Validated(validated = false)
	SVGResource back();
	
	@Override
	@Source("icons/svg/android/document_viewer.svg")
	@Validated(validated = false)
	SVGResource document_viewer();
	
	@Override
	@Source("icons/svg/android/document_edit.svg")
	@Validated(validated = false)
	SVGResource document_edit();

	@Override
	@Source("icons/svg/android/icon_fx.svg")
	@Validated(validated = false)
	SVGResource icon_fx();

}
