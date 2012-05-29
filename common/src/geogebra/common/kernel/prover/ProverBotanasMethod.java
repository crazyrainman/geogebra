package geogebra.common.kernel.prover;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import geogebra.common.kernel.algos.AlgoElement;
import geogebra.common.kernel.algos.SymbolicParametersAlgo;
import geogebra.common.kernel.algos.SymbolicParametersBotanaAlgo;
import geogebra.common.kernel.algos.SymbolicParametersBotanaAlgoAre;
import geogebra.common.kernel.geos.GeoElement;
import geogebra.common.kernel.prover.Prover.NDGCondition;
import geogebra.common.kernel.prover.Prover.ProofResult;
import geogebra.common.main.AbstractApplication;

/**
 * A prover which uses Francisco Botana's method to prove geometric theorems.
 * 
 * @author Zoltan Kovacs
 *
 */
public class ProverBotanasMethod {
	
	private static HashSet<GeoElement> getFreePoints(GeoElement statement) {
		HashSet<GeoElement> freePoints = new HashSet<GeoElement>();
		Iterator<GeoElement> it = statement.getAllPredecessors().iterator();
		while (it.hasNext()) {
			GeoElement geo = it.next();
			if (geo.isGeoPoint() && geo.getParentAlgorithm() == null) { // this is a free point
				freePoints.add(geo);
			}
		}
		return freePoints;
	}
	
	/** 
	 * Creates those polynomials which describe that none of 3 free points
	 * can lie on the same line. 
	 * @return the NDG polynomials (in denial form)
	 */
	private static Polynomial[] create3FreePointsNeverCollinearNDG(Prover prover) {
		// Creating the set of free points first:
		HashSet<GeoElement> freePoints = getFreePoints(prover.statement);
		int setSize = freePoints.size();

		// Creating NDGs:
		NDGCondition ndgc = new NDGCondition();
		if (setSize > 3)
			ndgc.condition = "DegeneratePolygon";
		else
			ndgc.condition = "AreCollinear";
		ndgc.geos = new GeoElement[setSize];
		int i = 0;
		Iterator<GeoElement> it = freePoints.iterator();
		while (it.hasNext()) {
			ndgc.geos[i++] = it.next();
		}
		Arrays.sort(ndgc.geos);
		prover.addNDGcondition(ndgc);
		
		// The output will contain $\binom{n}{3}$ elements:
		Polynomial[] ret = new Polynomial[setSize * (setSize - 1) * (setSize - 2) / 6];
		i = 0;
		// Creating the set of triplets:
		HashSet<HashSet<GeoElement>> triplets = new HashSet<HashSet<GeoElement>>();
		Iterator<GeoElement> it1 = freePoints.iterator();
		while (it1.hasNext()) {
			GeoElement geo1 = it1.next();
			Iterator<GeoElement> it2 = freePoints.iterator();
			while (it2.hasNext()) {
				GeoElement geo2 = it2.next();
				if (!geo1.isEqual(geo2)) {
					Iterator<GeoElement> it3 = freePoints.iterator();
					while (it3.hasNext()) {
						GeoElement geo3 = it3.next();
						if (!geo1.isEqual(geo3) && !geo2.isEqual(geo3)) {
							HashSet<GeoElement> triplet = new HashSet<GeoElement>();
							triplet.add(geo1);
							triplet.add(geo2);
							triplet.add(geo3);
							// Only the significantly new triplets will be processed:
							if (!triplets.contains(triplet)) {
								triplets.add(triplet);
								Variable[] fv1 = ((SymbolicParametersBotanaAlgo)geo1).getBotanaVars();
								Variable[] fv2 = ((SymbolicParametersBotanaAlgo)geo2).getBotanaVars();
								Variable[] fv3 = ((SymbolicParametersBotanaAlgo)geo3).getBotanaVars();
								// Creating the polynomial for collinearity:
								Polynomial p = Polynomial.collinear(fv1[0], fv1[1],
										fv2[0], fv2[1], fv3[0], fv3[1]);
								// Rabinowitsch trick for prohibiting collinearity:
								ret[i] = p.multiply(new Polynomial(new Variable())).subtract(new Polynomial(1));
								// FIXME: this always introduces an extra variable, shouldn't do
								/*
								// This is unelegant and confusing:
								NDGCondition ndgc = new NDGCondition();
								ndgc.condition = "AreCollinear";
								ndgc.geos = new GeoElement[3];
								ndgc.geos[0] = geo1;
								ndgc.geos[1] = geo2;
								ndgc.geos[2] = geo3;
								Arrays.sort(ndgc.geos);
								prover.addNDGcondition(ndgc);
								*/
								i++;
							}
						}
					}
				}
			}
		}
		return ret;
	}
	
	/**
	 * Uses a minimal heuristics to fix the first four variables to certain "easy" numbers.
	 * The first two variables (usually the coordinates of the first point) are set to 0,
	 * and the second two variables (usually the coordinates of the second point) are set to 0 and 1.
	 * @param statement the input statement
	 * @return the command for Singular (e.g. "v1,0,v2,0,v3,0,v4,1")
	 */
	static String fixValues(GeoElement statement) {
		HashSet<GeoElement> freePoints = getFreePoints(statement);
		String ret = "";
		Iterator<GeoElement> it = freePoints.iterator();
		int i = 0;
		while (it.hasNext() && i<2) {
			Variable[] fv = ((SymbolicParametersBotanaAlgo) it.next()).getBotanaVars();
			if (i==0) {
				ret = fv[0].toString() + ",0," + fv[1].toString() + ",0";
				++i;
			}
			else {
				ret += "," + fv[0].toString() + ",0," + fv[1].toString() + ",1";
				++i;
			}
		}
		return ret;
	}

	/**
	 * Proves the statement by using Botana's method 
	 * @param prover the prover input object 
	 * @return if the statement is true
	 */
	public static ProofResult prove(Prover prover) {
		// Getting the hypotheses:
		Polynomial[] hypotheses = null;
		Iterator<GeoElement> it = prover.statement.getAllPredecessors().iterator();
		while (it.hasNext()) {
			GeoElement geo = it.next();
			// AbstractApplication.debug(geo);
			if (geo instanceof SymbolicParametersBotanaAlgo) {
				try {
					Polynomial[] geoPolys = ((SymbolicParametersBotanaAlgo) geo).getBotanaPolynomials();
					if (geoPolys != null) {
						int nHypotheses = 0;
						if (hypotheses != null)
							nHypotheses = hypotheses.length;
						Polynomial[] allPolys = new Polynomial[nHypotheses + geoPolys.length];
						for (int i=0; i<nHypotheses; ++i)
							allPolys[i] = hypotheses[i];
						for (int i=0; i<geoPolys.length; ++i)
							allPolys[nHypotheses + i] = geoPolys[i];
						hypotheses = allPolys;
					}
				} catch (NoSymbolicParametersException e) {
					return ProofResult.UNKNOWN;
				}
			}
			else return ProofResult.UNKNOWN;
		}
		try {
			// The sets of statement polynomials.
			// The last equation of each set will be negated.
			Polynomial[][] statements = ((SymbolicParametersBotanaAlgoAre) prover.statement.getParentAlgorithm()).getBotanaPolynomials();
			// The NDG conditions (automatically created):
			Polynomial[] ndgConditions = null;
			if (AbstractApplication.freePointsNeverCollinear)
				ndgConditions = create3FreePointsNeverCollinearNDG(prover);
			String substitutions = fixValues(prover.statement);
			int nHypotheses = 0;
			int nNdgConditions = 0;
			int nStatements = 0;
			if (hypotheses != null)
				nHypotheses = hypotheses.length;
			if (ndgConditions != null)
				nNdgConditions = ndgConditions.length;
			if (statements != null)
				nStatements = statements.length;
						
			boolean ans = true;
			// Solving the equation system for each sets of polynomials of the statement:
			for (int i=0; i<nStatements && ans; ++i) {
				int nPolysStatement = statements[i].length;
				Polynomial[] eqSystem = new Polynomial[nHypotheses + nNdgConditions + nPolysStatement];
				// These polynomials will be in the equation system always:
				for (int j=0; j<nHypotheses; ++j)
					eqSystem[j] = hypotheses[j];
				for (int j=0; j<nNdgConditions; ++j)
					eqSystem[j + nHypotheses] = ndgConditions[j];
				for (int j=0; j<nPolysStatement - 1; ++j)
					eqSystem[j + nHypotheses + nNdgConditions] = statements[i][j];

				// Rabinowitsch trick for the last polynomial of the current statement:
				Polynomial spoly = statements[i][nPolysStatement - 1].multiply(new Polynomial(new Variable())).subtract(new Polynomial(1));
				// FIXME: this always introduces an extra variable, shouldn't do
				eqSystem[nHypotheses + nNdgConditions + nPolysStatement - 1] = spoly;
				if (Polynomial.solvable(eqSystem, substitutions)) // FIXME: here seems NPE if SingularWS not initialized 
					ans = false;
			}
			if (ans)
				return ProofResult.TRUE;
			return ProofResult.FALSE;
		} catch (NoSymbolicParametersException e) {
			return ProofResult.UNKNOWN;
		}
	}
}
