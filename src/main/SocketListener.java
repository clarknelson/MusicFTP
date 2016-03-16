package main;

import util.Util;
import util.MessageQueue;

import java.net.Socket;
import java.io.DataInputStream;

public class SocketListener implements Runnable {
    String text;
    Socket socket;
    MessageQueue messages;

    public SocketListener( Socket s, MessageQueue mq){
        this.socket = s;
        this.messages = mq;
    }

    public void run(){
        try(DataInputStream in = new DataInputStream(this.socket.getInputStream())){
            while((text = in.readUTF()) != null){
                if(text.startsWith("download filename ")){
                    // start
                } else {
                    this.messages.add(text);
                }


            }
        } catch (Exception e) {
            Util.catchException("Problem reading thread: ", e);
        }
    }
}
