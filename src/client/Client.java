package client;

import main.Util;
import main.ReaderThread;

// connection packages
import java.net.Socket;
import java.net.InetAddress;

// open output from server
import java.io.OutputStream;
import java.io.PrintWriter;

// open input from server
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;



public class Client {

	private Socket SOCKET = null;
	private InetAddress HOSTNAME = null;
	private int PORT_NUMBER = 3000;

	public PrintWriter CLIENT_OUT = null;

	public Client(String[] args){
		System.out.println("Starting client...");

		if(args.length <= 1){
			// user did not supply any additional parameters
			Util.printMsg("Optional arguments: -h=[hostname] -p=[port-number]");
		}

		// generate a default InetAddress to connect to
		parseHostname("localhost");
		parsePortNumber("3000");
		for(int i=1; i<args.length; i++){
			handleArgument(args[i]);
		}

		connectToServer();

		openInputFromTerminal();
		openInputFromServer();

	}

	private void handleArgument(String arg){
		Util.printMsg("Command line argumnet: " + arg);

		if(arg.startsWith("-h")){
			String hostString = arg.substring(3, arg.length());
			parseHostname(hostString);
		}

		if(arg.startsWith("-p")){

			String port = arg.substring(3, arg.length());
			parsePortNumber(port);
		}
	}

	private void parseHostname(String host){
		if(host.equals("localhost")){
			try{
				this.HOSTNAME = InetAddress.getLocalHost();
			} catch (Exception e){
				Util.catchException("Could not find localhost", e);
			}
		} else {
			try{
				this.HOSTNAME = InetAddress.getByName(host);
			} catch (Exception e){
				Util.catchException("Could not parse hostname", e);
			}
		}
		Util.printMsg("Set hostname to: " + this.HOSTNAME.getHostName());
	}

	private void parsePortNumber(String port){
		try{
			int portInt = Integer.parseInt(port);
			this.PORT_NUMBER = portInt;
			Util.printMsg("Set port number to: " + this.PORT_NUMBER);
		} catch (Exception e) {
			Util.catchException("Can not parse port number", e);
		}
	}

	private void connectToServer(){
		try{
			this.SOCKET = new Socket(this.HOSTNAME, this.PORT_NUMBER);
			Util.printMsg("Connected to server...");
		} catch (Exception e){
			Util.catchException("Could not open socket to server", e);
		}
	}

	private void openInputFromTerminal(){
		try{
			ReaderThread console = new ReaderThread("terminal", System.in, this.SOCKET.getOutputStream());
			Thread consoleThread = new Thread(console);
			consoleThread.start();
		} catch (Exception e) {
			Util.catchException("Could not open output to server", e);
		}
	}

	private void openInputFromServer(){
		try{
			ReaderThread server = new ReaderThread("server", this.SOCKET.getInputStream(), System.out);
			Thread serverThread = new Thread(server);
			serverThread.start();
		} catch (Exception e){
			Util.catchException("Can not open thread to listen to server", e);
		}
	}
}
