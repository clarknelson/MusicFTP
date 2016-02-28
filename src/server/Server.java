package server;

import main.Util;

public class Server {

	private ServerSocket server = null;
	private int PORT_NUMBER = 3000;

	public Server(String[] args){

		// @TODO read configuration file
		Util.printMsg("Starting server...");

		if(args.length <= 1){
			// the user didnt supply any additional arguemets when they ran the server
			Util.printMsg("Optional arguments:");
			Util.printMsg("-p=[port-number]");
		}

		// args[0] is equal to "--server"
		for(int i=1; i<args.length; i++){
			parseArgument(args[i]);
		}

		startServer();
	}

	private void parseArgument(String arg){
		Util.printMsg("Command line argument: " + arg);

		if(arg.startsWith("-p")){ // parse port number
			// @TODO make sure the port number isnt something bad
			// maybe check what ports are already open
			String portString = arg.substring(3, arg.length());
			int portInt = Integer.parseInt(portString);
			Util.printMsg("Setting port number to: " + portString);
			this.PORT_NUMBER = portInt;
		}
	}

	private void startServer(){
		/* ServerSocket takes one extra parameter:
		backlog - requested maximum length of the queue of incoming connections. */
		this.server = new ServerSocket(this.PORT_NUMBER);
	}
}
