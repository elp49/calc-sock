package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorOutputModel {

	private ActionListener actionListener = null;
	private String text = "";

	public CalculatorOutputModel() {
		// Intentionally empty.
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "set text"));
	}

	public void appendText(String text) {
		this.text += text;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "append text"));
	}

	public void clearText() {
		text = "";
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clear text"));
	}

	public synchronized void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	private void processEvent(ActionEvent e) {
		ActionListener actionListener;

		synchronized (this) {
			if (this.actionListener == null) {
				return;
			}

			actionListener = this.actionListener;
		}

		actionListener.actionPerformed(e);
	}

}
