package main;

import java.net.Socket;
import java.io.DataInputStream;

public class SocketListener implements Runnable {
    String text;
    Socket socket;

    public SocketListener( Socket s ){
        this.socket = s;
    }

    public void run(){
        try(DataInputStream in = new DataInputStream(this.socket.getInputStream())){
            while((text = in.readUTF()) != null){
                Util.printMsg(text);
            }
        } catch (Exception e) {
            Util.catchException("Problem reading thread: ", e);
        }
    }
}
