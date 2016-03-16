package server;

import util.MessageQueue;
import util.Util;
import util.MusicManager;

import java.net.Socket;
import java.net.InetAddress;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.File;

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

            if(message.startsWith("list files")){
                getSongList();
                return;
            }
            if(message.startsWith("list artist")){
                if(message.equals("list artists") || message.equals("list artist")){
                    getListOfArtists();
                    return;
                } else {
                    String artist = message.substring(12);
                    getSongsByArtist(artist);
                    return;
                }
            }
            getSongList();
            return;
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

            String query = message.substring(9);
            if(query.startsWith("artist")){
                String artist = query.substring(7);
                downloadArtist(artist);
                return;
            }
            if(query.startsWith("song")){
                String song = query.substring(5);
                music.downloadSong(song);
                return;
            }
            return;
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
        // this class is run on the server, which does not have a help menu
        // we send a message back to the client telling it to print the usage
        print("help");
    }




    private void getNumberOfSongs(){
        int songCount = music.getNumberOfSongs();
        String songCountString = Integer.toString(songCount);
        print("--------------------------------------------------");
        print("Number of songs available for download: " + songCountString);
        print("--------------------------------------------------");
    }

    private void getListOfArtists(){
        String[] artists = music.getArtistList();
        print("--------------------------------------------------");
        for(int i=0; i<artists.length; i++){
            print(artists[i]);
        }
        print("--------------------------------------------------");
    }

    private void getSongsByArtist(String artist){
        String[] songs = music.getSongsByArtist(artist);
        print("--------------------------------------------------");
        for(int i=0; i<songs.length; i++){
            print(songs[i]);
        }
        print("--------------------------------------------------");
    }

    private void getSongList(){
        String[] songs = music.getFileNames();
        print("--------------------------------------------------");
        for(int i=0; i<songs.length; i++){
            print(songs[i]);
        }
        print("--------------------------------------------------");
    }


    private void downloadArtist(String artist){
        File[] songs = music.downloadSongsByArtist(artist);

        for(int i=0; i<songs.length; i++){
            //print(songs[i]);

            // send flag of number of files


            // start upload thread with file array
            // thread(Socket, File[])

        }
        Util.printMsg(Integer.toString(songs.length));
    }
}
