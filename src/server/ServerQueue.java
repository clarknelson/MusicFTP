package server;

import main.MessageQueue;
import main.Util;
import main.MusicManager;

import java.net.Socket;
import java.net.InetAddress;

import java.io.DataOutputStream;

public class ServerQueue extends MessageQueue {

    Socket socket;
    DataOutputStream client;

    public ServerQueue(Socket s){
        this.socket = s;
        try{
            this.client = new DataOutputStream(this.socket.getOutputStream());
            this.client.writeUTF("connected to server");
        } catch (Exception e) {
            Util.catchException("Could not open output to server in server queue", e);
        }
    }
    public void add(String message){
        //Util.printMsg("New message in server queue: " + message);
        switch(message){
            case("connected to client"):
                printClientInformation();
                break;
            case("shutdown"):
                closeConnection();
                break;
            case("list"):
                Util.printMsg("Client is asking for a list of songs");
                MusicManager.getSongs();
                break;
            default:
                Util.printMsg(message);
                break;
        }
    }

    private void printClientInformation(){
        int port = this.socket.getLocalPort();
        InetAddress ia = this.socket.getInetAddress();
        String host = ia.getHostName();
        Util.printMsg("New client connected from " + host + " on port " + port);
    }
    private void closeConnection(){
        try{
            this.socket.close();
        } catch (Exception e){
            Util.catchException("Could not close socket in server", e);
        }
    }
}
