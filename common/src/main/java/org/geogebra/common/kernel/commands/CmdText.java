package org.geogebra.common.kernel.commands;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.algos.AlgoText;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.geos.GeoBoolean;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.kernelND.GeoPointND;
import org.geogebra.common.main.MyError;

/**
 * Text[ <text> ]
 */
public class CmdText extends CommandProcessor {
	/**
	 * Creates new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdText(Kernel kernel) {
		super(kernel);
	}

	@Override
	public GeoElement[] process(Command c, EvalInfo info) throws MyError {
		int n = c.getArgumentNumber();
		GeoElement[] arg;

		switch (n) {
		case 1:

			arg = resArgs(c, true, info);
			AlgoText algo = new AlgoText(cons, c.getLabel(), arg[0]);

			GeoElement[] ret = { algo.getGeoText() };
			return ret;

		case 2:

			arg = resArgs(c);
			if (arg[1].isGeoBoolean()) {
				algo = new AlgoText(cons, c.getLabel(), arg[0],
						(GeoBoolean) arg[1]);

				GeoElement[] ret2 = { algo.getGeoText() };
				return ret2;
			} else if (arg[1].isGeoPoint()) {
				algo = new AlgoText(cons, c.getLabel(), arg[0],
						(GeoPointND) arg[1]);

				GeoElement[] ret2 = { algo.getGeoText() };
				return ret2;
			} else {
				throw argErr(app, c, arg[1]);
			}

		case 3:
			boolean ok;
			arg = resArgs(c);
			if ((ok = arg[1].isGeoPoint()) && arg[2].isGeoBoolean()) {
				algo = new AlgoText(cons, c.getLabel(), arg[0],
						(GeoPointND) arg[1], (GeoBoolean) arg[2]);

				GeoElement[] ret2 = { algo.getGeoText() };
				return ret2;
			}
			throw argErr(app, c, ok ? arg[2] : arg[1]);

		case 4:
			boolean ok1 = false;
			arg = resArgs(c);
			if ((ok = arg[1].isGeoPoint()) && (ok1 = arg[2].isGeoBoolean())
					&& arg[3].isGeoBoolean()) {

				algo = new AlgoText(cons, c.getLabel(), arg[0],
						(GeoPointND) arg[1], (GeoBoolean) arg[2],
						(GeoBoolean) arg[3]);

				GeoElement[] ret2 = { algo.getGeoText() };
				return ret2;
			}
			throw argErr(app, c,
					ok ? (ok1 ? arg[3] : arg[2]) : arg[1]);

		default:
			throw argNumErr(c);
		}
	}

}
