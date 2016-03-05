package client;

import main.MessageQueue;
import main.Util;

import java.net.Socket;

public class ClientQueue extends MessageQueue {

    Socket socket;
    public ClientQueue(Socket s){
        this.socket = s;
    }
    public void add(String message){
        switch(message){
            case("shutdown"):
                closeConnection();
                break;
            case("welcome"):
                printWelcomeMessage();
                break;
            default:
                Util.printMsg(message);
        }
    }
    private void closeConnection(){
        try{
            this.socket.close();
        } catch (Exception e){
            Util.catchException("Could not close socket in client", e);
        }
    }
    private void printWelcomeMessage(){
        Util.printMsg("--------------------------------------------------");
        Util.printMsg("------------- WELCOME TO MUSICFTP ----------------");
        Util.printMsg("--------------------------------------------------");
        Util.printMsg("Available commands:");
        Util.printMsg("  shutdown : disconnects client from server");
        Util.printMsg("--------------------------------------------------");

    }
}
