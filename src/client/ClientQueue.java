package client;

import main.MessageQueue;
import main.Util;

import java.net.Socket;
import java.net.InetAddress;


public class ClientQueue extends MessageQueue {

    public ClientQueue(Socket s){
        super(s);
        print("connected to client");
    }

    public void add(String message){
        //Util.printMsg("New message in client queue: " + message);
        switch(message){
            case("welcome"):
                printServerInformation();
                printWelcomeMessage();
                break;
            case("connected to server"):
                askForSongList();
                break;
            default:
                super.add(message);
                break;
        }
    }

    private void askForSongList(){
        try{
            this.output.writeUTF("list");
        } catch (Exception e){
            Util.catchException(e);
        }
    }

    private void printServerInformation(){
        int port = this.socket.getLocalPort();
        InetAddress ia = this.socket.getInetAddress();
        String host = ia.getHostName();
        Util.printMsg("Connected to server on " + host + " port " + port);
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
