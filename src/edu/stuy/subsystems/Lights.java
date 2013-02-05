/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Arfan
 */
public class Lights {
    
    private static Lights instance;
    private Relay cameraLight;
    private Relay whiteSignal;
    private Relay colorSignal;
    
    private Lights() {
        cameraLight = new Relay(Constants.CAMERA_LIGHT);
        whiteSignal = new Relay(Constants.SIGNAL_LIGHT_A);
        colorSignal = new Relay(Constants.SIGNAL_LIGHT_B);
    }
    
    public static Lights getInstance() {
        if (instance == null) {
            instance = new Lights();
        }
        return instance;
    }
    
    public Relay getCameraLight() {
        return cameraLight;
    }
    
    public Relay signalWhiteLight() {
        return whiteSignal;
    }
    
    public Relay signalColorLight() {
        return colorSignal;
    }
    
    public void enableCameraLight(boolean on) {
        if(on)
            cameraLight.set(Relay.Value.kOn);
        else
            cameraLight.set(Relay.Value.kOff);
    }
    
    public void enableWhiteLight(boolean on) {
        if(on)
            whiteSignal.set(Relay.Value.kOn);
        else
            whiteSignal.set(Relay.Value.kOff);
    }
    
    public void enableColorLight(boolean on) {
        if(on)
            colorSignal.set(Relay.Value.kOn);
        else
            colorSignal.set(Relay.Value.kOff);
    }
    
    /*
     * run this in both tele and auton
     * flash when disc is picked up from ground or feeder
     * flash on color to signal human player for feed white discs as we approach
     * flash different color for colored discs
     * flash pickup frequency with shooting
     */
}
