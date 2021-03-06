package org.geogebra.common.kernel.statistics;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CommandProcessor;
import org.geogebra.common.kernel.geos.GeoBoolean;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoFunction;
import org.geogebra.common.kernel.geos.GeoNumberValue;
import org.geogebra.common.main.MyError;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Cauchy Distribution
 */
public class CmdCauchy extends CommandProcessor {

	/**
	 * Create new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdCauchy(Kernel kernel) {
		super(kernel);
	}

	@Override
	@SuppressFBWarnings({ "SF_SWITCH_FALLTHROUGH",
			"missing break is deliberate" })
	public GeoElement[] process(Command c) throws MyError {
		int n = c.getArgumentNumber();
		boolean[] ok = new boolean[n];
		GeoElement[] arg;

		arg = resArgs(c);

		GeoBoolean cumulative = null; // default for n=3 (false)
		switch (n) {
		case 4:
			if (arg[3].isGeoBoolean()) {
				cumulative = (GeoBoolean) arg[3];
			} else {
				throw argErr(app, c, arg[3]);
			}

			// fall through
		case 3:
			if ((ok[0] = arg[0] instanceof GeoNumberValue)
					&& (ok[1] = arg[1] instanceof GeoNumberValue)) {
				if (arg[2].isGeoFunction() && ((GeoFunction) arg[2])
						.toString(StringTemplate.defaultTemplate).equals("x")) {

					AlgoCauchyDF algo = new AlgoCauchyDF(cons, c.getLabel(),
							(GeoNumberValue) arg[0], (GeoNumberValue) arg[1],
							forceBoolean(cumulative, true));
					return algo.getResult().asArray();

				} else if (arg[2] instanceof GeoNumberValue) {
					AlgoCauchy algo = new AlgoCauchy(cons,
							(GeoNumberValue) arg[0], (GeoNumberValue) arg[1],
							(GeoNumberValue) arg[2], cumulative);

					GeoElement[] ret = { algo.getResult() };
					ret[0].setLabel(c.getLabel());
					return ret;
				} else {
					throw argErr(app, c, arg[2]);
				}

			} else if (!ok[0]) {
				throw argErr(app, c, arg[0]);
			} else if (!ok[1]) {
				throw argErr(app, c, arg[1]);
			}

		default:
			throw argNumErr(c);
		}
	}
}
