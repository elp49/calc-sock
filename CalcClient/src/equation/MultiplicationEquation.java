package equation;

import java.math.BigDecimal;

public class MultiplicationEquation extends Equation {

	public MultiplicationEquation() {
		super();
	}

	@Override
	public BigDecimal compute() {
		return leftSide.compute().multiply(rightSide.compute());
	}

	@Override
	public String toString() {
		return "*";
	}

}
