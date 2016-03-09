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


    /* INCOMMING MESSAGES FROM THE SERVER */
    public void add(String message){
        //Util.printMsg("New message in client queue: " + message);
        if(message.equals("welcome")){
            printServerInformation();
            printWelcomeMessage();
            printUsage();
            return;
        }
        if(message.equals("connected to server")){
            //askForSongList();
            return;
        }
        if(message.equals("help")){
            printUsage();
            return;
        }
        if(message.equals("list")){
            print("list");
            return;
        }
        super.add(message);
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
    }
    private void printUsage(){
        Util.printMsg("==================================================");
        Util.printMsg("Program commands:");
        Util.printMsg("    [help] - prints this message");
        Util.printMsg("    [shutdown] - ends client and server program");
        Util.printMsg("List commands:");
        Util.printMsg("    [list] - prints all songs on the server");
        Util.printMsg("    [list files] - same as above");
        Util.printMsg("    [list artists] - prints all artists available");
        Util.printMsg("    [list artist <foobar>] - prints songs by artist");
        Util.printMsg("    //[list songs] - prints out song names");
        Util.printMsg("    //[list song <foobar>] - prints songs named asdf");
        Util.printMsg("Download commands:");
        Util.printMsg("    //[download] - downloads all files from the server");
        Util.printMsg("    //[download song <foobar>] - downloads all files from the server");
        Util.printMsg("    //[download artist Bassnectar]");
        Util.printMsg("==================================================");
    }
}
