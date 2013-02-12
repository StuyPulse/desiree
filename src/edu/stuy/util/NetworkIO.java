package edu.stuy.util;

//import java.net.*;
import javax.microedition.io.*;
import java.io.*;
public class NetworkIO{
    private static final int PORT = 6940;
    private static final String HOST = "10.6.95.242";
    private SocketConnection requestSocket;
    private DataOutputStream out;
    private DataInputStream in;
    private String message;
    private double mostRecentOut;
    public NetworkIO(){
        try {
            SocketConnection requestSocket = (SocketConnection) Connector.open("socket://" + HOST + ":" + PORT);
            requestSocket.setSocketOption(SocketConnection.LINGER, 5);
            in = new DataInputStream(requestSocket.openInputStream());
            out = new DataOutputStream(requestSocket.openOutputStream());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run()
    {
        try {
            

            System.out.println("banana1");
            //ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //byte[] netVal = new byte[128];
            //int curr = 0;
            //while ((curr = in.read(netVal)) != -1){
                //baos.write(netVal, 0, curr);
            //}
            //message = new String(baos.toByteArray());
            double output;
            if (in.available() > 0) {
                output  = in.readDouble();
            }
            else {
                output = mostRecentOut;
            }
            message = "" + output;
            System.out.println(message);
            System.out.println("banana2");
            mostRecentOut = Double.parseDouble(message);
            //sendMessage("bye");
            //requestSocket.close();
            //in.close();
            //out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void sendMessage(String msg)
    {
        try{
            out.write(msg.getBytes());
            out.flush();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public double getMostRecent () {
        return mostRecentOut;
    }
    public double getCurrent()
    {
        run();
        return getMostRecent();
    }
}
