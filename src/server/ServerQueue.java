package server;

import util.MessageQueue;
import util.Util;
import util.MusicManager;

import java.net.Socket;
import java.net.InetAddress;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServerQueue extends MessageQueue {

    MusicManager music;

    public ServerQueue(Socket s){
        super(s);
        print("connected to server");
        music = new MusicManager("../src/songs");
    }

    /* INCOMMING MESSAGES FROM CLIENT */
    public void add(String message){
        // visualize raw client to server messages
        //Util.printMsg("New message in server queue: " + message);

        // the user might want to see a list of commands after running something
        //print("help") // tell the client to print usage
        if(message.startsWith("list")){

            if(message.startsWith("list artists")){
                getListOfArtists();
                return;
            }
            getSongList();
        }

        if(message.equals("connected to client")){
            printClientInformation();
            return;
        }

        if(message.equals("help")){
            printHelp();
            return;
        }

        if(message.startsWith("download")){

        }
        // searches methods in util.MessageQueue
        super.add(message);
    }

    private void printClientInformation(){
        int port = this.socket.getLocalPort();
        InetAddress ia = this.socket.getInetAddress();
        String host = ia.getHostName();
        Util.printMsg("New client connected from " + host + " on port " + port);
    }

    private void printHelp(){
        // this class is run on the server, and does not have a help menu
        // we send a message back to the client telling it to print usage
        print("help");
    }

    private void getNumberOfSongs(){
        int songCount = music.getNumberOfSongs();
        print("Number of songs available for download: " + Integer.toString(songCount));
    }

    private void getListOfArtists(){
        String[] artists = music.getArtistList();
        for(int i=0; i<artists.length; i++){
            print(artists[i]);
        }
    }
    private void getSongList(){
        String[] songs = music.getSongs();
        for(int i=0; i<songs.length; i++){
            print(songs[i]);
        }
    }
}
