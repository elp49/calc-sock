package equation;

import java.math.BigDecimal;

import visitor.Visitor;

public interface EquationComponent {

	public BigDecimal compute();

	public void setLeftSide(EquationComponent leftSide);

	public void setRightSide(EquationComponent rightSide);

	public void accept(Visitor visitor);

	public String toString();

}
