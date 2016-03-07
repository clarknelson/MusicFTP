package main;

import util.Util;

import java.net.Socket;
import java.io.DataInputStream;

public class SocketListener implements Runnable {
    String text;
    Socket socket;
    MessageQueue messages;

    public SocketListener( Socket s ){
        this.socket = s;
    }
    public SocketListener( Socket s, MessageQueue mq){
        this.socket = s;
        this.messages = mq;
    }

    public void run(){
        try(DataInputStream in = new DataInputStream(this.socket.getInputStream())){
            while((text = in.readUTF()) != null){
                if(this.messages != null){
                    this.messages.add(text);
                } else {
                    Util.printMsg(text);
                }
            }
        } catch (Exception e) {
            Util.catchException("Problem reading thread: ", e);
        }
    }
}
