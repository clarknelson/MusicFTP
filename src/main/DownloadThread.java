package main;

import util.Util;

import java.net.Socket;
import java.io.DataInputStream;

public class DownloadThread implements Runnable {
    String text;
    Socket socket;

    public DownloadThread( Socket s ){
        this.socket = s;
    }

    public void run(){
        synchronized(this){


            try(DataInputStream in = new DataInputStream(this.socket.getInputStream())){
                while((text = in.readUTF()) != null){
                    Util.printMsg(text);
                    if(text.startsWith("download")){
                        Util.printMsg("We are going to download something");
                    }
                }
            } catch (Exception e) {
                Util.catchException("Problem reading thread: ", e);
            }
        }
    }
}
