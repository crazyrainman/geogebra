package geogebra.kernel.cas;

import geogebra.kernel.Construction;
import geogebra.kernel.geos.CasEvaluableFunction;

public class AlgoSimplify extends AlgoCasBase {

	public AlgoSimplify(Construction cons,  String label, CasEvaluableFunction f) {
		super(cons, label, f);
	}

	@Override
	public String getClassName() {
		return "AlgoSimplify";
	}

	@Override
	protected void applyCasCommand() {
		g.setUsingCasCommand("Simplify(%)", f, false);		
	}
	
}
