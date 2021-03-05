package state;

import client.CalculatorController;

public class CalculateState extends State {

	public CalculateState(CalculatorController context) {
		super(context);
	}

	@Override
	public void appendNumber(int n) {
		context.setNumberBuffer(n);
		context.setState(new GettingFirstOperandState(context));
	}

	@Override
	public void compute() {
		context.clearNumberBuffer();
		context.setState(new StartState(context));
	}

	@Override
	public void add() {
		context.clearNumberBuffer();
		context.setState(new StartState(context));
	}

	@Override
	public void subtract() {
		context.clearNumberBuffer();
		context.setState(new StartState(context));
	}

	@Override
	public void multiply() {
		context.clearNumberBuffer();
		context.setState(new StartState(context));
	}

	@Override
	public void divide() {
		context.clearNumberBuffer();
		context.setState(new StartState(context));
	}

}
