package state;

import client.CalculatorController;

public class StartState extends State {

	public StartState(CalculatorController context) {
		super(context);
	}

	@Override
	public void appendNumber(int n) {
		context.setNumberBuffer(n);
		context.setState(new GettingFirstOperandState(context));
	}

	@Override
	public void clear() {
		// Intentionally empty.
	}

	@Override
	public void compute() {
		// Intentionally empty.
	}

	@Override
	public void add() {
		// Intentionally empty.
	}

	@Override
	public void subtract() {
		// Intentionally empty.
	}

	@Override
	public void multiply() {
		// Intentionally empty.
	}

	@Override
	public void divide() {
		// Intentionally empty.
	}

}
