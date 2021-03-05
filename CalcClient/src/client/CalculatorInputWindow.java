package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalculatorInputWindow extends JFrame {

	private static final long serialVersionUID = 21305503976948004L;

	private Font font = null;
	private CalculatorOutputDisplay outputDisplay = null;
	private List<JButton> btnList = null;
	private JPanel btnPanel = null;

	public CalculatorInputWindow() {

		font = new Font("Arial", Font.BOLD, 25);

		// Initialize output display.
		outputDisplay = new CalculatorOutputDisplay();
		outputDisplay.setFont(font);

		// Initialize button list.
		btnList = new ArrayList<JButton>();
		btnList.add(new JButton("1"));
		btnList.add(new JButton("2"));
		btnList.add(new JButton("3"));
		btnList.add(new JButton("+"));
		btnList.add(new JButton("4"));
		btnList.add(new JButton("5"));
		btnList.add(new JButton("6"));
		btnList.add(new JButton("-"));
		btnList.add(new JButton("7"));
		btnList.add(new JButton("8"));
		btnList.add(new JButton("9"));
		btnList.add(new JButton("*"));
		btnList.add(new JButton("0"));
		btnList.add(new JButton("="));
		btnList.add(new JButton("C"));
		btnList.add(new JButton("/"));

		// Initialize button panel.
		btnPanel = new JPanel(new GridLayout(4, 3, 10, 10));
		btnPanel.setFont(font);
		for (JButton btn : btnList) {
			btn.setFont(font);
			btnPanel.add(btn);
		}

		// Initialize frame.
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(outputDisplay, BorderLayout.PAGE_START);
		add(btnPanel, BorderLayout.CENTER);
		setBounds(200, 200, 0, 0);
		setMinimumSize(new Dimension(315, 315));
		pack();
		setVisible(true);
	}

	public void setOutputDisplayModel(CalculatorOutputModel model) {
		outputDisplay.setModel(model);
	}

	public List<JButton> getBtnList() {
		return btnList;
	}

}
