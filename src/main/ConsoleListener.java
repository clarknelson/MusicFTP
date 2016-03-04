package main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import java.io.DataOutputStream;
import java.io.DataInputStream;

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
        try(
        BufferedReader in = new BufferedReader(
        new InputStreamReader(this.stream))){
            while((text = in.readLine()) != null){
                this.output.writeUTF(text);
            }
        } catch (Exception e) {
            Util.catchException("Problem reading from console: ", e);
        }
    }
}
