package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalculatorErrorWindow extends JFrame {

	private static final long serialVersionUID = -4658329928947153330L;

	private Font font = null;
	private JTextArea errorTextArea = null;
	private JButton resetBtn = null;
	private JButton discardBtn = null;
	private JPanel btnPanel = null;

	public CalculatorErrorWindow() {
		font = new Font("Arial", Font.BOLD, 25);

		// Initialize error message.
		errorTextArea = new JTextArea("Error: You entered an invalid operation.");
		errorTextArea.setFont(font);
		errorTextArea.setEditable(false);
		errorTextArea.setWrapStyleWord(true);
		errorTextArea.setLineWrap(true);

		// Initialize buttons.
		resetBtn = new JButton("Reset");
		discardBtn = new JButton("Discard");
		resetBtn.setFont(font);
		discardBtn.setFont(font);

		// Initialize button panel.
		btnPanel = new JPanel(new GridLayout(1, 2, 25, 25));
		btnPanel.add(resetBtn);
		btnPanel.add(discardBtn);

		// Initialize frame.
		setTitle("Error");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(errorTextArea, BorderLayout.PAGE_START);
		add(btnPanel, BorderLayout.CENTER);
		setBounds(200, 200, 0, 0);
		setMinimumSize(new Dimension(315, 150));
		pack();
		setVisible(true);
	}

	public JButton getResetBtn() {
		return resetBtn;
	}

	public JButton getDiscardBtn() {
		return discardBtn;
	}

}
