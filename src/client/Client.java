package client;

import main.Util;

public class Client {

	public Client(){
		System.out.println("Hello from client");
		Util.readConfigFileAt("config.txt");
	}
	public Client(String[] args){
		this();
		for(int i=0; i<args.length; i++){
			Util.printMsg(args[i]);
		}
	}
}
