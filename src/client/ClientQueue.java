package client;

import main.MessageQueue;
import main.Util;

import java.net.Socket;

import java.io.DataOutputStream;

public class ClientQueue extends MessageQueue {

    Socket socket;
    DataOutputStream server;
    public ClientQueue(Socket s){
        this.socket = s;
        try{
            this.server = new DataOutputStream(this.socket.getOutputStream());
            this.server.writeUTF("connected to client");
        } catch (Exception e) {
            Util.catchException("Could not open output to server in client queue", e);
        }
    }

    public void add(String message){
        //Util.printMsg("New message in client queue: " + message);
        switch(message){
            case("connected to server"):
                askForSongList();
                break;
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
    private void askForSongList(){
        try{
            Util.printMsg("connected to the server, asking for a song list");
            this.server.writeUTF("list");
        } catch (Exception e){
            Util.catchException(e);
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
