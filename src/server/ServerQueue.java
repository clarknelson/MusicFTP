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
        //Util.printMsg("New message in server queue: " + message);
        switch(message){
            case("connected to client"):
                printClientInformation();
                break;
            case("list"):
                getSongList();
                // print the usage again
                // print("help");
                break;
            case("list artists"):
                getListOfArtists();
                break;
            case("help"):
                // client is asking for usage
                print("help");
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
