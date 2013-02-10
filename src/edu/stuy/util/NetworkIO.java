package edu.stuy.util;

import java.net.*;
import java.io.*;
public class NetworkIO{
    private static final int PORT = 6940;
    private Socket requestSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message;
    private double mostRecentOut;
    public NetworkIO(){}
    public void run()
    {
        try{
            //1. creating a socket to connect to the server
            requestSocket = new Socket("localhost", PORT);
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //3: Communicating with the server
            do{
                try{
                    message = (String)in.readObject();
                    mostRecentOut = Double.parseDouble(message);                //THIS IS WHERE 
                    message = "bye";
                    sendMessage(message);
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
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
        client.run();
        return client.getMostRecent();
    }
}
