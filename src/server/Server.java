package server;

import main.Util;


public class Server {
	public Server(){
		Util.printMsg("Hello from server");
		Util.readConfigFileAt("server/config.txt");
	}
}
