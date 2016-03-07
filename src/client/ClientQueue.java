package client;

import util.MessageQueue;
import util.Util;

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

        if(message.equals("welcome")){
            printServerInformation();
            printWelcomeMessage();
            return;
        }
        if(message.equals("connected to server")){
            askForSongList();
            return;
        }
        if(message.equals("list")){
            askForSongList();
            return;
        }
        super.add(message);
    }

    private void askForSongList(){
        try{
            this.output.writeUTF("list");

            // we dont need to add an input stream here
            // we dont even have to explicitly listen and catch the songs
            // the input from the stream is sent to the "add()" method in this class
            // unless its a command, the text will trickle down to a Util.printMsg()

            /*
            DataInputStream input = new DataInputStream (this.socket.getInputStream());
            String numSongs = input.readUTF();
            Util.printMsg("Number of songs in directory: " + numSongs);
            int numSongsInt = Integer.parseInt(numSongs);
            for (int i = 0; i < numSongsInt; i++) {
                Util.printMsg(input.readUTF());
            }*/
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
        Util.printMsg("==================================================");
        Util.printMsg("------------- WELCOME TO MUSICFTP ----------------");
        Util.printMsg("==================================================");
        Util.printMsg("Available commands:");
        Util.printMsg("  shutdown - ends client and server program");
        Util.printMsg("  list - prints the available songs on the server");
        Util.printMsg("==================================================");

    }
}
