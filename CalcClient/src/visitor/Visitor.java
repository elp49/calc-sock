package visitor;

import equation.EquationComponent;

public interface Visitor {

	public String getState();

	public void visit(EquationComponent node);

}
