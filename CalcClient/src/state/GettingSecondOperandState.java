package state;

import java.math.BigDecimal;

import client.CalculatorController;
import equation.AdditionEquation;
import equation.DivisionEquation;
import equation.EquationComponent;
import equation.MultiplicationEquation;
import equation.Number;
import equation.SubtractionEquation;
import visitor.PrintVisitor;
import visitor.Visitor;

public class GettingSecondOperandState extends State {

	public GettingSecondOperandState(CalculatorController context) {
		super(context);
	}

	@Override
	public void compute() {
		if (isOperandValid()) {
			addSecondOperandToEquation();
			displayResult();
			String equationString = getEquationString();
			context.publishEquation(equationString);
			context.setState(new CalculateState(context));
		}
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

	private void addSecondOperandToEquation() {
		EquationComponent equation = context.getEquation();
		BigDecimal secondOperand = context.getNumberBuffer();
		equation.setRightSide(new Number(secondOperand));
	}

	private void displayResult() {
		EquationComponent equation = context.getEquation();
		BigDecimal result = equation.compute();
		context.setNumberBuffer(result);
	}

	private String getEquationString() {
		EquationComponent equation = context.getEquation();
		Visitor printVisitor = new PrintVisitor();
		equation.accept(printVisitor);
		String equationString = printVisitor.getState();

		// Append result to the end.
		BigDecimal result = equation.compute();
		equationString += " = " + result.toString();

		return equationString;
	}

	private void handleOperation(EquationComponent newParentEquation) {
		if (isOperandValid()) {
			acceptNextOperand(newParentEquation);
		}
	}

	private void acceptNextOperand(EquationComponent newParentEquation) {
		addSecondOperandToEquation();
		displayResult();
		setNewParentEquation(newParentEquation);
		context.setState(new WaitingForNextOperandState(context));
	}

	private void setNewParentEquation(EquationComponent newParentEquation) {
		EquationComponent equation = context.getEquation();
		newParentEquation.setLeftSide(equation);
		context.setEquation(newParentEquation);
	}

}
