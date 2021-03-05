package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import pipe.Pipe;
import pipe.PipeImpl;

public class Client {

	private String address = "";
	private int port = -1;
	private Socket sock = null;
	private DataOutputStream out = null;

	private Pipe equationPipe = null;
	private CalculatorOutputModel model = null;
	private CalculatorInputWindow inputWindow = null;
	private CalculatorController controller = null;

	public Client(String address, int port) {
		this.address = address;
		this.port = port;

		equationPipe = new PipeImpl();
		model = new CalculatorOutputModel();
		inputWindow = new CalculatorInputWindow();
		controller = new CalculatorController(equationPipe);
	}

	public static void main(String[] args) {
		String address = "localhost";
		int port = 3000;

		Client client = new Client(address, port);
		client.run();
	}

	public void run() {
		initialize();

		String equation = getEquation();
		while (equation != null) {
			write(equation);
			equation = getEquation();
		}

		close();
	}

	private String getEquation() {
		String equation = null;

		try {
			// Get equation from input pipe.
			equation = (String) equationPipe.get();
		} catch (InterruptedException e) {
			System.err.println("Failed to get equation from client equation pipe.");
		}

		return equation;
	}

	private void write(String equation) {
		try {
			// Write equation to output stream.
			out.writeUTF(equation);
			out.flush();
		} catch (IOException e) {
			System.err.println("Failed to write equation to client output stream.");
		}
	}

	private void initialize() {
		initializeClient();
		initializeOutputStream();
		inputWindow.setOutputDisplayModel(model);
		controller.setInputWindow(inputWindow);
		controller.setOutputDisplayModel(model);
	}

	private void initializeClient() {
		try {
			sock = new Socket(address, port);
			System.out.println("Client - connected to port " + port + " on host " + address);
		} catch (IOException e) {
			System.err.println("Failed to connect client to port " + port + " on host " + address + ".");
		}
	}

	private void initializeOutputStream() {
		try {
			out = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			System.err.println("Failed to get client output stream.");
		}
	}

	private void close() {
		try {
			sock.close();
			System.out.println("Client - closed");
		} catch (IOException e) {
			System.err.println("Failed to close client socket.");
		}
	}

}
