/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

/*
 * AlgoTangentLine.java
 *
 * Created on 30. August 2001, 21:37
 */

package org.geogebra.common.geogebra3D.kernel3D.algos;

import org.geogebra.common.euclidian.EuclidianConstants;
import org.geogebra.common.geogebra3D.kernel3D.geos.GeoPoint3D;
import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.Matrix.Coords;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.LabelManager;
import org.geogebra.common.kernel.kernelND.GeoConicND;
import org.geogebra.common.kernel.kernelND.GeoLineND;

/**
 *
 * @author mathieu
 */
public class AlgoIntersectLineConic3D extends AlgoIntersectConic3D {

	/**
	 * 
	 * @param cons
	 * @param label
	 * @param g
	 * @param c
	 */
	AlgoIntersectLineConic3D(Construction cons, String label, GeoLineND g,
			GeoConicND c) {
		this(cons, g, c);
		LabelManager.setLabels(label, P);
	}

	/**
	 * 
	 * @param cons
	 * @param labels
	 * @param g
	 * @param c
	 */
	AlgoIntersectLineConic3D(Construction cons, String[] labels, GeoLineND g,
			GeoConicND c) {
		this(cons, g, c);
		LabelManager.setLabels(labels, P);
	}

	@Override
	public Commands getClassName() {
		return Commands.Intersect;
	}

	@Override
	public int getRelatedModeID() {
		return EuclidianConstants.MODE_INTERSECT;
	}

	/**
	 * 
	 * @param cons
	 * @param g
	 * @param c
	 */
	AlgoIntersectLineConic3D(Construction cons, GeoLineND g, GeoConicND c) {
		super(cons, (GeoElement) g, c);

	}

	/**
	 * 
	 * @return line input
	 */
	GeoLineND getLine() {
		return (GeoLineND) getFirtGeo();
	}

	@Override
	protected Coords getFirstGeoStartInhomCoords() {
		return getLine().getStartInhomCoords();
	}

	@Override
	protected Coords getFirstGeoDirectionInD3() {
		return getLine().getDirectionInD3();
	}

	@Override
	protected boolean getFirstGeoRespectLimitedPath(Coords p) {
		return getLine().respectLimitedPath(p, Kernel.STANDARD_PRECISION);
	}

	@Override
	protected void checkIsOnFirstGeo(GeoPoint3D p) {
		if (!p.isDefined()) {
			return;
		}
		if (!getLine().respectLimitedPath(p.getCoords(), Kernel.MIN_PRECISION)) {
			p.setUndefined();
		}
	}
}
