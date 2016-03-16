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
            //boolean downloading = false;

            while(true){
                long status = in.readLong();
                //Util.printMsg("New message from other side: " + Long.toString(status));
                if(status == 1){
                    String message = in.readUTF();
                    //Util.printMsg("Recieved message: " + message);
                    this.messages.add(message);
                }
                if(status == 2){

                    String message = in.readUTF();
                    Util.printMsg("Recieving download: " + message);
                    //this.messages.add(message);
                }
            }
            /*
            while((text = in.readUTF()) != null && !downloading){
                if(text.startsWith("download")){
                    downloadSomething();
                    downloading = true;
                } else {
                    this.messages.add(text);
                }
            } */
        } catch (Exception e) {
            Util.catchException("Problem reading thread: ", e);
        }
    }

    private void downloadSomething(){
        Util.printMsg("we are going to download something");
    }
}
