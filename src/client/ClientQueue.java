package client;

import main.MessageQueue;
import main.Util;

import java.net.Socket;
import java.net.InetAddress;


import java.io.DataOutputStream;
import java.io.DataInputStream;

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
<<<<<<< HEAD
            this.server.writeUTF("list");
	    DataInputStream input = new DataInputStream (this.socket.getInputStream());
	    String numSongs = input.readUTF();
	    Util.printMsg("Number of songs in directory: " + numSongs);
	    int numSongsInt = Integer.parseInt(numSongs);
	    for (int i = 0; i < numSongsInt; i++)
		{
		    Util.printMsg(input.readUTF());
		}
=======
            this.output.writeUTF("list");
>>>>>>> b03f86a44d5b078918264503ae7d003d635a5a73
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
