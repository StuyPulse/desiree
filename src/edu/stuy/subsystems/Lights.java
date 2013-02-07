/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.Relay;

/**
 * run this in both tele and auton
 * flash when disc is picked up from ground or feeder
 * flash on color to signal human player for feed white discs as we approach
 * flash different color for colored discs
 * flash pickup frequency with shooting (please clarify)
 * @author Arfan
 */
public class Lights {
    private static Lights instance;
    
    /**
     * Lights must be wired as follows:
     * M+: Camera light
     * M-: Direction light
     */
    private Relay cameraAndDirectionLightRelay;
    
    /**
     * Lights must be wired as follows:
     * M+: White signal light
     * M-: Colored signal light
     */
    private Relay signalLightRelay;
    
    private Lights() {
        cameraAndDirectionLightRelay = new Relay(Constants.CAMERA_AND_DIRECTION_RELAY_CHANNEL);
        signalLightRelay = new Relay(Constants.SIGNAL_LIGHT_RELAY_CHANNEL);
    }
    
    public static Lights getInstance() {
        if (instance == null) {
            instance = new Lights();
        }
        return instance;
    }
    
    public void setCameraLight(boolean on) {
        Relay.Value currentVal = cameraAndDirectionLightRelay.get();
        if (on) { // Turn camera light on
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kForward) { // Direction light is off
                cameraAndDirectionLightRelay.set(Relay.Value.kForward);
            }
            else { // Direction light is on
                cameraAndDirectionLightRelay.set(Relay.Value.kOn);
            }
        }
        else { // Turn camera light off
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kForward) { // Direction light is off
                cameraAndDirectionLightRelay.set(Relay.Value.kOff);
            }
            else { // Direction light is on
                cameraAndDirectionLightRelay.set(Relay.Value.kReverse);
            }
        }
    }
    
    public void setDirectionLight(boolean on) {
        Relay.Value currentVal = cameraAndDirectionLightRelay.get();
        if (on) { // Turn direction light on
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kReverse) { // Camera light is off
                cameraAndDirectionLightRelay.set(Relay.Value.kReverse);
            }
            else { // Camera light is on
                cameraAndDirectionLightRelay.set(Relay.Value.kOn);
            }
        }
        else { // Turn direction light off
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kReverse) { // Camera light is off
                cameraAndDirectionLightRelay.set(Relay.Value.kOff);
            }
            else { // Camera light is on
                cameraAndDirectionLightRelay.set(Relay.Value.kForward);
            }
        }
    }
    
    public void setWhiteSignalLight(boolean on) {
        Relay.Value currentVal = signalLightRelay.get();
        if (on) { // Turn white light on
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kForward) { // Colored light is off
                signalLightRelay.set(Relay.Value.kForward);
            }
            else { // Colored light is on
                signalLightRelay.set(Relay.Value.kOn);
            }
        }
        else { // Turn white light off
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kForward) { // Colored light is off
                signalLightRelay.set(Relay.Value.kOff);
            }
            else { // Colored light is on
                signalLightRelay.set(Relay.Value.kReverse);
            }
        }
    }
    
    public void setColoredSignalLight(boolean on) {
        Relay.Value currentVal = signalLightRelay.get();
        if (on) { // Turn colored light on
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kReverse) { // White light is off
                signalLightRelay.set(Relay.Value.kReverse);
            }
            else { // White light is on
                signalLightRelay.set(Relay.Value.kOn);
            }
        }
        else { // Turn colored light off
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kReverse) { // White light is off
                signalLightRelay.set(Relay.Value.kOff);
            }
            else { // White light is on
                signalLightRelay.set(Relay.Value.kForward);
            }
        }
    }
}
