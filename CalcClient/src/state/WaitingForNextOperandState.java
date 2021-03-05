package state;

import client.CalculatorController;

public class WaitingForNextOperandState extends State {

	public WaitingForNextOperandState(CalculatorController context) {
		super(context);
	}

	@Override
	public void appendNumber(int n) {
		context.setNumberBuffer(n);
		context.setState(new GettingSecondOperandState(context));
	}

	@Override
	public void compute() {
		changeStateToError(this);
	}

	@Override
	public void add() {
		changeStateToError(this);
	}

	@Override
	public void subtract() {
		changeStateToError(this);
	}

	@Override
	public void multiply() {
		changeStateToError(this);
	}

	@Override
	public void divide() {
		changeStateToError(this);
	}

}
