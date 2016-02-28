package client;

import main.Util;

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

	private PrintWriter CLIENT_OUT = null;
	private BufferedReader CLIENT_IN = null;

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

		openOutputToServer();
		openInputFromServer();

		closeStreams();
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
			Util.printMsg("connecting to socket with " + this.PORT_NUMBER + " and " + this.HOSTNAME.getHostName());
			this.SOCKET = new Socket(this.HOSTNAME, this.PORT_NUMBER);


		} catch (Exception e){
			Util.catchException("Could not open socket to server", e);
		}
	}


	private void openOutputToServer(){
		try{
			OutputStream op = this.SOCKET.getOutputStream();


			this.CLIENT_OUT = new PrintWriter(op);
		} catch (Exception e){
			Util.catchException("Could not open output to server", e);
		}

	}
	private void openInputFromServer(){

		try{
			InputStream is = this.SOCKET.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			this.CLIENT_IN = new BufferedReader(isr);

			String nextLine = this.CLIENT_IN.readLine();

		} catch (Exception e) {
			Util.catchException("Could not read from server", e);
		}

	}
	private void closeStreams(){

	}
}
