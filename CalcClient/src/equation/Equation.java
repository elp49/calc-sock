package equation;

import visitor.Visitor;

public abstract class Equation implements EquationComponent {

	protected EquationComponent leftSide = null;
	protected EquationComponent rightSide = null;

	public Equation() {
		// Intentionally empty.
	}

	@Override
	public void setLeftSide(EquationComponent leftSide) {
		this.leftSide = leftSide;
	}

	@Override
	public void setRightSide(EquationComponent rightSide) {
		this.rightSide = rightSide;
	}

	@Override
	public void accept(Visitor visitor) {
		leftSide.accept(visitor);
		visitor.visit(this);
		rightSide.accept(visitor);
	}

}
