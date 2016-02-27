package client;

import main.Util;

public class Client {

	public Client(){
		System.out.println("Hello from client");
		Util.readConfigFileAt("config.txt");
	}
}
