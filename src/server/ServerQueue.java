package server;

import main.MessageQueue;
import main.Util;
import main.MusicManager;

import java.net.Socket;
import java.net.InetAddress;


public class ServerQueue extends MessageQueue {


    public ServerQueue(Socket s){
        super(s);
        print("connected to server");
    }

    public void add(String message){
        //Util.printMsg("New message in server queue: " + message);

        switch(message){
            case("connected to client"):
                printClientInformation();
                break;
            case("list"):
                Util.printMsg("Client is asking for a list of songs");
                MusicManager.getSongs();
                break;
            default:
                super.add(message);
                break;
        }
    }

    private void printClientInformation(){
        int port = this.socket.getLocalPort();
        InetAddress ia = this.socket.getInetAddress();
        String host = ia.getHostName();
        Util.printMsg("New client connected from " + host + " on port " + port);
    }
}
