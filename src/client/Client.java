package client;

import main.Util;

public class Client {

	public Client(){
		System.out.println("Starting client...");
		Util.readTextFileAt("client/client_config.txt");
	}

	public Client(String[] args){
		this();
		for(int i=0; i<args.length; i++){
			Util.printMsg(args[i]);
		}
	}
}
