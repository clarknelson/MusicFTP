package server;

import main.Util;
import main.SocketListener;
import main.ConsoleListener;

import server.ServerQueue;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.DataOutputStream;

public class Server {

	private int PORT_NUMBER = 3000;
	private ServerSocket SERVER = null;
	private Socket CLIENT = null;
	private DataOutputStream SERVER_OUT = null;
	private ServerQueue MESSAGES = null;

	public Server(String[] args){
		if(args.length <= 1){
			Util.printMsg("Optional arguments: -p=[port-number]");
		}

		// args[0] is equal to "--server"
		for(int i=1; i<args.length; i++){
			parseArgument(args[i]);
		}

		startServer();

		openOutputToClient();

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

	/** This should be abstracted into a thread
		So we can connect multiple clients
		The ServerSocket will need to be continuously listening
	*/
	private void startServer(){
		try{
			// ServerSocket can take one extra parameter:
			// backlog - requested maximum length of the queue of incoming connections.
			this.SERVER = new ServerSocket(this.PORT_NUMBER);
			InetAddress inet = this.SERVER.getInetAddress();
			Util.printMsg("Waiting for client on "+ inet.getHostName() + "...");

			// Program locks up while waiting for a client
			this.CLIENT = this.SERVER.accept();

			//MusicManager.getSongs(); -> String[]
		} catch (Exception e) {
			Util.catchException("Can not open socket", e);
		}
	}

	private void openOutputToClient(){
		try{
			this.SERVER_OUT = new DataOutputStream(this.CLIENT.getOutputStream());
			this.SERVER_OUT.writeUTF("welcome");
		} catch (Exception e) {
			Util.catchException("Could not open output to client", e);
		}
	}

	private void openInputFromTerminal(){
		try{
			ConsoleListener console = new ConsoleListener(System.in, this.CLIENT.getOutputStream());
			Thread consoleThread = new Thread(console);
			consoleThread.start();
		} catch (Exception e) {
			Util.catchException("Could not start console listener thread", e);
		}
	}
	private void openInputFromClient(){
		try{
			this.MESSAGES = new ServerQueue(this.CLIENT);
			SocketListener client = new SocketListener(this.CLIENT, this.MESSAGES);
			Thread clientThread = new Thread(client);
			clientThread.start();
		} catch (Exception e) {
			Util.catchException("Could not start client listener thread", e);
		}
	}
}
