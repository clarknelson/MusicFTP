package main;

import client.Client;
import server.Server;

public class MusicFTP {

	public static void main(String[] args) {
		if(args.length < 1){
			// we need to know if this is a client or server
			Util.printErr("Not enough arguments...");
			Util.printHelp();
			System.exit(1);
		}

		if(args[0].equals("--client")){
			Client c = new Client(args);
		}
		if(args[0].equals("--server")){
			Server s = new Server(args);
		}
	}
}
