package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;

import equation.EquationComponent;
import pipe.Pipe;
import state.StartState;
import state.State;

public class CalculatorController {

	private State state = null;

	private CalculatorOutputModel model = null;
	private EquationComponent equation = null;

	private Pipe equationOutputPipe = null;
	private CalculatorErrorWindow errorView = null;

	public CalculatorController(Pipe equationOutputPipe) {
		this.state = new StartState(this);
		this.equationOutputPipe = equationOutputPipe;
	}

	public void setState(State state) {
		this.state = state;
	}

	public CalculatorController getContext() {
		return this;
	}

	public void setOutputDisplayModel(CalculatorOutputModel model) {
		this.model = model;
	}

	public BigDecimal getNumberBuffer() throws NumberFormatException {
		return new BigDecimal(model.getText());
	}

	public void setNumberBuffer(int number) {
		model.setText(String.valueOf(number));
	}

	public void setNumberBuffer(BigDecimal number) {
		model.setText(number.toString());
	}

	public void displayErrorMessage(String message) {
		model.setText("Error: " + message);
	}

	public void appendToNumberBuffer(int n) {
		model.appendText(String.valueOf(n));
	}

	public void clearNumberBuffer() {
		model.clearText();
	}

	public EquationComponent getEquation() {
		return equation;
	}

	public void setEquation(EquationComponent equation) {
		this.equation = equation;
	}

	public void publishEquation(String equationString) {
		equationOutputPipe.put(equationString);
	}

	// Calculator Input Window button listeners.
	private class NumberListener implements ActionListener {

		int value = 0;

		public NumberListener(int value) {
			this.value = value;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			state.appendNumber(value);
		}

	}

	private class OperationListener implements ActionListener {

		String operation = null;

		public OperationListener(String operation) {
			this.operation = operation;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (operation) {
			case "=":
				state.compute();
				break;
			case "C":
				state.clear();
				break;
			case "+":
				state.add();
				break;
			case "-":
				state.subtract();
				break;
			case "*":
				state.multiply();
				break;
			case "/":
				state.divide();
				break;
			}
		}

	}

	// Register Calculator Input Window listeners.
	public void setInputWindow(CalculatorInputWindow view) {
		for (JButton btn : view.getBtnList()) {
			String text = btn.getText();
			Integer number = parseInt(text);

			// Test if button text is a number.
			if (number != null) {
				// Add number listener.
				btn.addActionListener(new NumberListener((int) number));
			} else {
				// Add operation listener.
				btn.addActionListener(new OperationListener(text));
			}
		}
	}

	private Integer parseInt(String text) {
		Integer number = null;

		try {
			number = Integer.parseInt(text);
		} catch (NumberFormatException ignore) {
		}

		return number;
	}

	// Calculator Error Window button listeners.
	private class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			clearNumberBuffer();
			setState(new StartState(getContext()));
			errorView.dispose();
		}

	}

	private class DiscardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			state.discard();
			errorView.dispose();
		}

	}

	// Register Calculator Error Window listeners.
	public void setInputWindow(CalculatorErrorWindow errorView) {
		this.errorView = errorView;
		errorView.getResetBtn().addActionListener(new ResetListener());
		errorView.getDiscardBtn().addActionListener(new DiscardListener());
	}

}
