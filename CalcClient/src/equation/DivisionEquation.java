package equation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionEquation extends Equation {

	public DivisionEquation() {
		super();
	}

	@Override
	public BigDecimal compute() {
		BigDecimal quotient = null;

		try {
			// Try to divide decimals.
			quotient = leftSide.compute().divide(rightSide.compute());
		} catch (ArithmeticException e) {
			// Catch infinitely nonterminating decimal.
			quotient = leftSide.compute().divide(rightSide.compute(), 2, RoundingMode.HALF_UP);
		}

		return quotient;
	}

	@Override
	public String toString() {
		return "/";
	}

}
