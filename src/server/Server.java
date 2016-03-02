package server;

import main.Util;
import main.ReaderThread;

import java.net.ServerSocket;
import java.net.Socket;

// write to client
import java.io.OutputStream;
import java.io.PrintWriter;

// read from client
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

//Get songs
import musicManager.MusicManager;

public class Server {

	private ServerSocket SERVER = null;
	private Socket CLIENT = null;
	private int PORT_NUMBER = 3000;

	private PrintWriter SERVER_OUT = null;
	private BufferedReader SERVER_IN = null;

	public Server(String[] args){
		Util.printMsg("Starting server...");

		if(args.length <= 1){
			// user did not supply any additional arguemets
			Util.printMsg("Optional arguments: -p=[port-number]");
		}

		// args[0] is equal to "--server"
		for(int i=1; i<args.length; i++){
			parseArgument(args[i]);
		}

		startServer();

		openInputFromTerminal();
		openInputFromClient();
	}

	private void parseArgument(String arg){
		Util.printMsg("Command line argument: " + arg);

		if(arg.startsWith("-p")){
			try{ // to parse port number
				String portString = arg.substring(3, arg.length());
				int portInt = Integer.parseInt(portString);
				Util.printMsg("Setting port number to: " + portString);
				// @TODO make sure the port number isnt a bad value
				this.PORT_NUMBER = portInt;
			} catch (Exception e) {
				Util.catchException("Can not parse port number", e);
			}

		}
	}

	private void startServer(){
		try{
			// ServerSocket can take one extra parameter:
			// backlog - requested maximum length of the queue of incoming connections.
			this.SERVER = new ServerSocket(this.PORT_NUMBER);
			Util.printMsg("Waiting for client...");
			// Program locks up while waiting for a client
			this.CLIENT = this.SERVER.accept();
			Util.printMsg("Server connected to client...");
		} catch (Exception e) {
			Util.catchException("Can not open socket", e);
		}
	}

	private void openInputFromTerminal(){
		try{
			ReaderThread console = new ReaderThread("terminal", System.in, this.CLIENT.getOutputStream());
			Thread consoleThread = new Thread(console);
			consoleThread.start();
		} catch (Exception e) {
			Util.catchException("Could not open output to server", e);
		}
	}
	private void openInputFromClient(){
		try{
			ReaderThread client = new ReaderThread("client", this.CLIENT.getInputStream(), System.out);
			Thread clientThread = new Thread(client);
			clientThread.start();
		} catch (Exception e) {
			Util.catchException("Could not open output to server", e);
		}
	}
}
