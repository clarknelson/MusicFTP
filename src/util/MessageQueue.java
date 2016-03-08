package util;

import java.net.Socket;
import java.io.DataOutputStream;

// client.ClientQueue and server.ServerQueue both inherit this functionality
public class MessageQueue{

    protected Socket socket;
    protected DataOutputStream output;

    protected MessageQueue(Socket s){
        this.socket = s;
        try{
            this.output = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e) {
            Util.catchException("Could not open output to server in client queue", e);
        }
    }

    public void add(String message){
        switch(message){
            case("shutdown"):
                closeConnection();
                break;
            default:
                Util.printMsg(message);
        }
    }

    protected void closeConnection(){
        try{
            this.socket.close();
        } catch (Exception e){
            Util.catchException("Could not close socket in client", e);
        }
    }

    protected void print(String message){
        try{
            this.output.writeUTF(message);
        } catch (Exception e){
            Util.catchException("Could not print message", e);
        }
    }
}
