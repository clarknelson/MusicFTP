package server;

import main.MessageQueue;
import main.Util;
import main.MusicManager;

import java.net.Socket;

import java.io.DataOutputStream;

public class ServerQueue extends MessageQueue {

    Socket socket;
    DataOutputStream client;

    public ServerQueue(Socket s){
        this.socket = s;
        try{
            this.client = new DataOutputStream(this.socket.getOutputStream());
            this.client.writeUTF("hello client from server");
        } catch (Exception e) {
            Util.catchException("Could not open output to server in server queue", e);
        }
    }
    public void add(String message){
        switch(message){
            case("shutdown"):
                closeConnection();
                break;
            case("list"):
                MusicManager.getSongs();
                break;
            default:
                Util.printMsg(message);
                break;
        }
    }

    private void closeConnection(){
        try{
            this.socket.close();
        } catch (Exception e){
            Util.catchException("Could not close socket in server", e);
        }
    }
}
