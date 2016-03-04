package main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.net.Socket;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import java.io.DataOutputStream;
import java.io.DataInputStream;

public class ReaderThread implements Runnable {
    String text;
    Socket socket;

    public ReaderThread( Socket s ){
        this.socket = s;
        try{
            //this.output = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e){
            Util.catchException("Could not open output stream", e);
        }
    }

    public void run(){
        try(DataInputStream in = new DataInputStream(this.socket.getInputStream())){
            while((text = in.readUTF()) != null){
                Util.printMsg(text);
                //this.output.writeUTF(text);
            }
        } catch (Exception e) {
            Util.catchException("Problem reading thread: ", e);
        }
    }
}
