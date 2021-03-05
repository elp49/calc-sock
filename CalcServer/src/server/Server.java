package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	private int port = -1;
	private ServerSocket server = null;
	private List<Connection> connections = null;

	private EquationHistoryModel model = null;
	private EquationHistoryDisplayWindow displayWindow = null;

	public Server(int port) {
		this.port = port;
		this.connections = new ArrayList<Connection>();

		model = new EquationHistoryModel();
		displayWindow = new EquationHistoryDisplayWindow();
	}

	public static void main(String[] args) {
		int port = 3000;

		Server server = new Server(port);
		server.run();
	}

	public void run() {
		initialize();

		Socket sock = accept();
		while (sock != null) {
			int id = getConnectionId();
			Connection c = new Connection(id, sock, model);
			connections.add(c);
			c.start();
			sock = accept();
		}

		close();
	}

	private void initialize() {
		initializeServer();
		displayWindow.setModel(model);
	}

	private void initializeServer() {
		try {
			// Try to connect to port.
			server = new ServerSocket(port);
			System.out.println("Server - bound to port " + port);
		} catch (IOException e) {
			System.err.println("Failed to bind server to port " + port + ".");
		}
	}

	private Socket accept() {
		Socket sock = null;

		try {
			// Try to accept client.
			sock = server.accept();
			int id = getConnectionId();
			System.out.println("Server - connected to client " + id);
		} catch (IOException e) {
			System.err.println("Failed to connect server to client.");
		}

		return sock;
	}

	private void close() {
		closeConnections();
		closeServer();
	}

	private void closeConnections() {
		for (Connection c : connections) {
			c.stop();
		}
	}

	private void closeServer() {
		try {
			// Try to close server socket.
			server.close();
			System.out.println("Server - closed");
		} catch (IOException e) {
			System.err.println("Failed to close server socket.");
		}
	}

	private int getConnectionId() {
		return connections.size() + 1;
	}

}
