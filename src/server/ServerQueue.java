package server;

import main.MessageQueue;
import main.Util;
import main.MusicManager;

import java.net.Socket;



public class ServerQueue extends MessageQueue {

    Socket socket;

    public ServerQueue(Socket s){
        this.socket = s;
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
