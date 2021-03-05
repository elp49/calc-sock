package equation;

import java.math.BigDecimal;

public class SubtractionEquation extends Equation {

	public SubtractionEquation() {
		super();
	}

	@Override
	public BigDecimal compute() {
		return leftSide.compute().subtract(rightSide.compute());
	}

	@Override
	public String toString() {
		return "-";
	}

}
