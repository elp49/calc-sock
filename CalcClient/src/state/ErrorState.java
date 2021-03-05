package state;

import client.CalculatorController;

public class ErrorState extends State {

	private State previousState = null;

	public ErrorState(CalculatorController context, State previousState) {
		super(context);
		this.previousState = previousState;
	}

	@Override
	public void appendNumber(int n) {
		// Intentionally empty.
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

	@Override
	public void discard() {
		context.setState(previousState);
	}

}
