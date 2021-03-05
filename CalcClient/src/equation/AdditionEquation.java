package equation;

import java.math.BigDecimal;

public class AdditionEquation extends Equation {

	public AdditionEquation() {
		super();
	}

	@Override
	public BigDecimal compute() {
		return leftSide.compute().add(rightSide.compute());
	}

	@Override
	public String toString() {
		return "+";
	}
}
