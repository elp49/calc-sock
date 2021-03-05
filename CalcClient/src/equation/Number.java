package equation;

import java.math.BigDecimal;

import visitor.Visitor;

public class Number implements EquationComponent {

	BigDecimal value = null;

	public Number(BigDecimal value) {
		this.value = value;
	}

	@Override
	public BigDecimal compute() {
		return value;
	}

	@Override
	public void setLeftSide(EquationComponent leftSide) {
		// Intentionally empty.
	}

	@Override
	public void setRightSide(EquationComponent rightSide) {
		// Intentionally empty.
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
