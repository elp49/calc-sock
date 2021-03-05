package state;

import java.math.BigDecimal;

import client.CalculatorController;
import equation.AdditionEquation;
import equation.DivisionEquation;
import equation.EquationComponent;
import equation.MultiplicationEquation;
import equation.Number;
import equation.SubtractionEquation;

public class GettingFirstOperandState extends State {

	public GettingFirstOperandState(CalculatorController context) {
		super(context);
	}

	@Override
	public void compute() {
		changeStateToError(this);
	}

	@Override
	public void add() {
		handleOperation(new AdditionEquation());
	}

	@Override
	public void subtract() {
		handleOperation(new SubtractionEquation());
	}

	@Override
	public void multiply() {
		handleOperation(new MultiplicationEquation());
	}

	@Override
	public void divide() {
		handleOperation(new DivisionEquation());
	}

	private void handleOperation(EquationComponent equation) {
		if (isOperandValid()) {
			setEquation(equation);
			context.setState(new WaitingForNextOperandState(context));
		}
	}

	private void setEquation(EquationComponent equation) {
		BigDecimal firstOperand = context.getNumberBuffer();
		EquationComponent leftSide = new Number(firstOperand);
		equation.setLeftSide(leftSide);
		context.setEquation(equation);
	}

}
