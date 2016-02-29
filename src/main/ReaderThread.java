package main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ReaderThread implements Runnable {
    String text;
    InputStream stream;
    String owner = "";
    PrintWriter output;

    public ReaderThread( String o, InputStream is, OutputStream os ){
        this.owner = o;
        this.stream = is;
        try{
            this.output = new PrintWriter(os, true);
        } catch (Exception e){
            Util.catchException("Could not open output stream", e);
        }

    }

    public void run(){
        try(BufferedReader in = new BufferedReader(
            new InputStreamReader(this.stream)
        )){
            // wait for a new line from the client
            while((text = in.readLine()) != null){

                if(this.owner.equals("server")){
                    Util.printMsg("Message from server: "+ text);
                    //this.output.println(text);
                }
                if(this.owner.equals("terminal")){
                    // if the message is sent from the terminal
                    // we dont want to print anything out
                    // but send the message to output
                    this.output.println(text);
                    //Util.printMsg("Owner is terminal for message "+ text);
                }
                if(this.owner.equals("client")){
                    Util.printMsg("Message from client: "+ text);
                    //this.output.println(text);
                }
                //if(this.owner.equals("terminal"))

            }
        } catch (Exception e) {
            if(this.owner != null || !this.owner.isEmpty()){
                Util.catchException("Problem reading "+this.owner+": ", e);
            } else {
                Util.catchException("Problem reading thread: ", e);
            }
        }
    }
}
