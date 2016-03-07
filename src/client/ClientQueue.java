package client;

import main.MessageQueue;
import main.Util;

import java.net.Socket;
import java.net.InetAddress;

import java.io.DataOutputStream;
import java.io.DataInputStream;
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
            case("welcome"):
                printServerInformation();
                printWelcomeMessage();
                break;
            case("connected to server"):
                askForSongList();
                break;
            case("shutdown"):
                closeConnection();
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
            this.server.writeUTF("list");
	    DataInputStream input = new DataInputStream (this.socket.getInputStream());
	    String numSongs = input.readUTF();
	    Util.printMsg("Number of songs in directory: " + numSongs);
	    int numSongsInt = Integer.parseInt(numSongs);
	    for (int i = 0; i < numSongsInt; i++)
		{
		    Util.printMsg(input.readUTF());
		}
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
