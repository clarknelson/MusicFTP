package main;

import util.Util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.OutputStream;
import java.io.DataOutputStream;

public class ConsoleListener implements Runnable {
    String text;
    InputStream stream;
    DataOutputStream output;
    BufferedReader br;

    public ConsoleListener( InputStream is, OutputStream os ){
        this.stream = is;
        try{
            this.output = new DataOutputStream(os);
        } catch (Exception e){
            Util.catchException("Could not open output stream", e);
        }
    }

    public void run(){
        try( // try-with-resources
        // safely closes stream when try block closes
        // for us, it closes when the input stream is closed
        BufferedReader in = new BufferedReader(
        new InputStreamReader(this.stream))){
            while((text = in.readLine()) != null){
                //Util.printMsg("New message from console: " + text);
                if(text.startsWith("download")){
                    //Util.printMsg("Download");
                    this.output.writeLong(2);
                    this.output.writeUTF(text);
                } else {
                    //Util.printMsg("message trying to be sent: " + text);
                    this.output.writeLong(1);
                    this.output.writeUTF(text);
                }
                //this.output.writeUTF(text);
            }
        } catch (Exception e) {
            Util.catchException("Problem reading from console: ", e);
        }
    }
}
