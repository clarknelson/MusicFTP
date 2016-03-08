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

    public void add(String message){
        //Util.printMsg("New message in server queue: " + message);
        switch(message){
            case("connected to client"):
                printClientInformation();
                break;
            case("list"):
                getSongList();
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

    private void getSongList(){
        int songCount = music.getNumberOfSongs();
        print("Number of songs available for download: " + Integer.toString(songCount));
    }
}
