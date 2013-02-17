package edu.stuy.util;

import edu.stuy.Constants;
import java.io.*;
import javax.microedition.io.*;

public class NetworkIO {
    
    private long lastTime;
    private SocketConnection requestSocket;
    private DataOutputStream out;
    private DataInputStream in;
    private String message;
    private double mostRecentOut;
    
    public NetworkIO() {
        mostRecentOut = Constants.CV_DEFAULT_VALUE; // return a huge value by default, the caller better know this
        try {
            SocketConnection requestSocket = (SocketConnection) Connector.open("socket://" + Constants.CV_IP + ":" + Constants.CV_PORT);
            requestSocket.setSocketOption(SocketConnection.LINGER, 5);
            in = new DataInputStream(requestSocket.openInputStream());
            out = new DataOutputStream(requestSocket.openOutputStream());
            out.flush();
            lastTime = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        try {
            double output;
            if (in.available() > 0) {
                output  = in.readDouble();
                lastTime = System.currentTimeMillis();
            }
            else {
                if (System.currentTimeMillis() - lastTime > Constants.CV_TIMEOUT) {
                    mostRecentOut = Constants.CV_DEFAULT_VALUE;
                }
                output = mostRecentOut;
            }
            message = "" + output;
            mostRecentOut = Double.parseDouble(message);
        } catch (Exception e) {
            if (Constants.DEBUG)
                e.printStackTrace();
        }
    }
    
    void sendMessage(String msg) {
        try {
            out.write(msg.getBytes());
            out.flush();
        } catch(IOException ioException) {
            if (Constants.DEBUG)
                ioException.printStackTrace();
        }
    }
    
    public double getMostRecent() {
        return mostRecentOut;
    }
    
    public double getCurrent() {
        run();
        return getMostRecent();
    }
}
