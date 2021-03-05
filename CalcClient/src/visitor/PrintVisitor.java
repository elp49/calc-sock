package visitor;

import equation.EquationComponent;

public class PrintVisitor implements Visitor {

	private StringBuilder sb = null;

	public PrintVisitor() {
		sb = new StringBuilder();
	}

	@Override
	public String getState() {
		return sb.toString();
	}

	@Override
	public void visit(EquationComponent node) {
		sb.append(node.toString() + " ");
	}

}
