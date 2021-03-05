package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection implements Runnable {

	private boolean is_started_ = false;
	private boolean is_closed_ = false;

	private int id = -1;
	private Socket sock = null;
	private DataInputStream in = null;

	private EquationHistoryModel model = null;

	public Connection(int id, Socket sock, EquationHistoryModel model) {
		this.id = id;
		this.sock = sock;
		this.model = model;
	}

	public void start() {
		if (!is_started_) {
			is_started_ = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		is_started_ = false;
		close();
	}

	@Override
	public void run() {
		initializeInputStream();

		String equation = readEquation();
		while (equation != null) {
			// Add equation to model.
			model.addEquation(equation);
			equation = readEquation();
		}

		close();
	}

	public String readEquation() {
		String equation = null;

		try {
			equation = (String) in.readUTF();
		} catch (IOException e) {
			System.err.println("Failed to read from server input stream.");
		}

		return equation;
	}

	private void initializeInputStream() {
		try {
			// Try to get server input stream.
			in = new DataInputStream(sock.getInputStream());
		} catch (IOException e) {
			System.err.println("Failed to get server input stream.");
		}
	}

	private void close() {
		if (!is_closed_) {
			try {
				// Try to close connection.
				sock.close();
				is_closed_ = true;
				System.out.println("Connection " + id + " - closed");
			} catch (IOException e) {
				System.err.println("Failed to close a connection from server.");
			}
		}
	}

}
