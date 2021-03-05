package state;

import client.CalculatorController;
import client.CalculatorErrorWindow;

public abstract class State {

	protected CalculatorController context = null;

	public State(CalculatorController context) {
		this.context = context;
	}

	public void appendNumber(int n) {
		context.appendToNumberBuffer(n);
	}

	public void clear() {
		context.clearNumberBuffer();
		context.setState(new StartState(context));
	}

	public abstract void compute();

	public abstract void add();

	public abstract void subtract();

	public abstract void multiply();

	public abstract void divide();

	public void discard() {
		// Intentionally empty.
	}

	protected void changeStateToError(State currentState) {
		ErrorState errorState = new ErrorState(context, currentState);
		context.setState(errorState);
		CalculatorErrorWindow view = new CalculatorErrorWindow();
		context.setInputWindow(view);
	}

	protected boolean isOperandValid() {
		boolean isValid = false;

		try {
			context.getNumberBuffer();
			isValid = true;
		} catch (NumberFormatException e) {
			context.displayErrorMessage("invalid input");
			context.setState(new StartState(context));
		}

		return isValid;
	}

}
