package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class CalculatorOutputDisplay extends JTextArea implements ActionListener {

	private static final long serialVersionUID = 6755293804729598021L;

	CalculatorOutputModel model = null;

	public CalculatorOutputDisplay() {
		super();
		setEditable(false);
		setWrapStyleWord(true);
		setLineWrap(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setText(model.getText());
	}

	public void setModel(CalculatorOutputModel model) {
		this.model = model;

		if (model != null) {
			model.setActionListener(this);
			setText(model.getText());
		}
	}

	public CalculatorOutputModel getModel() {
		return model;
	}

}
