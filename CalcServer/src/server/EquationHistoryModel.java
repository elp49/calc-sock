package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EquationHistoryModel {

	private List<String> history = null;
	private ActionListener listener = null;

	public EquationHistoryModel() {
		history = new ArrayList<String>();
	}

	public List<String> getHistory() {
		return history;
	}

	public void addEquation(String equation) {
		history.add(equation);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add equation"));
	}

	public synchronized void setActionListener(ActionListener listener) {
		this.listener = listener;
	}

	private void processEvent(ActionEvent e) {
		ActionListener listener = null;

		synchronized (this) {
			if (this.listener == null) {
				return;
			}

			listener = this.listener;
		}

		listener.actionPerformed(e);
	}

}
