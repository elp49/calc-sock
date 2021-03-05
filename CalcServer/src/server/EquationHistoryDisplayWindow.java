package server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class EquationHistoryDisplayWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7544472247240102278L;

	private Font font = null;
	private JTextField equationCounter = null;
	private JList equationHistory = null;
	private EquationHistoryModel model = null;

	public EquationHistoryDisplayWindow() {
		font = new Font("Arial", Font.BOLD, 25);

		// Initialize counter.
		equationCounter = new JTextField();
		equationCounter.setText("Count: 0");
		equationCounter.setFont(font);
		equationCounter.setEditable(false);

		// Initialize history.
		equationHistory = new JList();
		equationHistory.setFont(font);

		// Initialize frame.
		setTitle("History");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(equationCounter, BorderLayout.PAGE_START);
		add(equationHistory, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		setEquationHistory(model);
	}

	public void setModel(EquationHistoryModel model) {
		this.model = model;

		if (model != null) {
			model.setActionListener(this);
			setEquationHistory(model);
		}
	}

	private void setEquationHistory(EquationHistoryModel model) {
		Object[] history = model.getHistory().toArray();

		// Set counter.
		equationCounter.setText("Count: " + history.length);

		// Set history.
		equationHistory.setListData(history);

		pack();
	}

}
