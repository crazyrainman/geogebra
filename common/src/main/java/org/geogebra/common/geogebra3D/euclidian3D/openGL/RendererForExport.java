package org.geogebra.common.geogebra3D.euclidian3D.openGL;

import org.geogebra.common.awt.GBufferedImage;
import org.geogebra.common.geogebra3D.euclidian3D.EuclidianView3D;
import org.geogebra.common.geogebra3D.euclidian3D.draw.DrawLabel3D;
import org.geogebra.common.main.Feature;

/**
 * Renderer in the background (no visible 3D view)
 *
 */
public class RendererForExport extends RendererWithImpl {

	/**
	 * constructor
	 * 
	 * @param view
	 *            3D view
	 */
	public RendererForExport(EuclidianView3D view) {
		super(view, RendererType.SHADER);
	}

	/**
	 * set the geometry manager
	 */
	public void setGeometryManager() {
		geometryManager = createManager();
	}

	@Override
	public Manager createManager() {
		if (view3D.getApplication().has(Feature.MOB_PACK_BUFFERS_3D)) {
			return new ManagerShadersElementsGlobalBufferPacking(this, view3D);
		}
		return new ManagerShadersElementsGlobalBuffer(this, view3D);
	}

	public void drawScene() {
		updateViewAndDrawables();
	}

	@Override
	public boolean useShaders() {
		return true;
	}

	/**
	 * set x/y min/max
	 * 
	 * @param xmin
	 * @param xmax
	 * @param ymin
	 * @param ymax
	 */
	public void setXYMinMax(double xmin, double xmax, double ymin, double ymax) {
		left = (int) xmin;
		bottom = (int) ymin;
		right = (int) xmax;
		top = (int) ymax;
	}

	@Override
	public Object getCanvas() {
		return null;
	}

	@Override
	public void setLineWidth(double width) {
		// no need
	}

	@Override
	public void resumeAnimator() {
		// no need
	}

	@Override
	protected void disableStencilLines() {
		// no need
	}

	@Override
	public void enableTextures2D() {
		// no need
	}

	@Override
	public void disableTextures2D() {
		// no need
	}

	@Override
	public GBufferedImage createBufferedImage(DrawLabel3D label) {
		return null;
	}

	@Override
	public void createAlphaTexture(DrawLabel3D label, GBufferedImage bimg) {
		// no need
	}

	@Override
	public int createAlphaTexture(int sizeX, int sizeY, byte[] buf) {
		return 0;
	}

	@Override
	public void textureImage2D(int sizeX, int sizeY, byte[] buf) {
		// no need
	}

	@Override
	public void setTextureLinear() {
		// no need
	}

	@Override
	public void setTextureNearest() {
		// no need
	}

	@Override
	protected void setDepthFunc() {
		// no need
	}

	@Override
	protected void enablePolygonOffsetFill() {
		// no need
	}

	@Override
	protected void setBlendFunc() {
		// no need
	}

}
