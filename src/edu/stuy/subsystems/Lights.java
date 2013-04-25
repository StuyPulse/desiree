/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 * Runs this in both tele and auton.
 * Flashes when disc is picked up from ground or feeder.
 * Flashes on color to signal human player for feed white discs as we approach.
 * Flashes different color for colored discs.
 * Flashes pickup frequency with shooting.
 * @author R4D4
 */
public class Lights {
    
    private static Lights instance;
    
    /**
     * Lights must be wired as follows:
     * M+: Camera light
     * M-: Direction light
     */
    private Relay cameraAndDirectionLightRelay;
    private Victor directionLight;
    
    /**
     * Lights must be wired as follows:
     * M+: White signal light
     * M-: Red signal light
     */
    private Relay signalLightRelay;
    private int WHITE_FLASH_FREQUENCY = 7;
    private int RED_FLASH_FREQUENCY = 7;
    
    public final static double DIRECTION_LIGHT_INTENSITY = 0.8;
    
    private double lastTimeWhite = 0;
    private double lastTimeRed = 0;
    private boolean isWhiteOn, isRedOn;
    
    private Lights() {
        cameraAndDirectionLightRelay = new Relay(Constants.CAMERA_AND_DIRECTION_RELAY_CHANNEL);
        signalLightRelay = new Relay(Constants.SIGNAL_LIGHT_RELAY_CHANNEL);
        directionLight = new Victor(Constants.DIRECTION_LIGHT_CHANNEL);
        isWhiteOn = false;
        isRedOn = false;
    }
    
    public static Lights getInstance() {
        if (instance == null) {
            instance = new Lights();
        }
        return instance;
    }

    private void setCameraLight(boolean on) {
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
    
    public void setDirectionLight(double intensity) {
        if(intensity > 0.4) {
            directionLight.set(intensity);
            setDirectionLight(true);
        }
        else {
            directionLight.set(0);
            setDirectionLight(false);
        }
    }
    
    private void setDirectionLight(boolean on) {
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
    
    private void setWhiteSignalLight(boolean on) {
        Relay.Value currentVal = signalLightRelay.get();
        if (on) { // Turn white light on
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kForward) { // Red light is off
                signalLightRelay.set(Relay.Value.kForward);
            }
            else { // Red light is on
                signalLightRelay.set(Relay.Value.kOn);
            }
            isWhiteOn = true;
        }
        else { // Turn white light off
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kForward) { // Red light is off
                signalLightRelay.set(Relay.Value.kOff);
            }
            else { // Red light is on
                signalLightRelay.set(Relay.Value.kReverse);
            }
            isWhiteOn = false;
        }
    }
    
    private void setRedSignalLight(boolean on) {
        Relay.Value currentVal = signalLightRelay.get();
        if (on) { // Turn red light on
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kReverse) { // White light is off
                signalLightRelay.set(Relay.Value.kReverse);
            }
            else { // White light is on
                signalLightRelay.set(Relay.Value.kOn);
            }
            isRedOn = true;
        }
        else { // Turn red light off
            if (currentVal == Relay.Value.kOff || currentVal == Relay.Value.kReverse) { // White light is off
                signalLightRelay.set(Relay.Value.kOff);
            }
            else { // White light is on
                signalLightRelay.set(Relay.Value.kForward);
            }
            isRedOn = false;
        }
    }
    
    private void flashWhiteSignalLight() {
        double time = Timer.getFPGATimestamp();
        if (time - lastTimeWhite > (1.0 / WHITE_FLASH_FREQUENCY)) { // Flashes (on and off) white light every 1/7 second
            setWhiteSignalLight(!isWhiteOn);
            lastTimeWhite = time;
        }
    }
    
    public void flashRedSignalLight() {
        double time = Timer.getFPGATimestamp();
        if (time - lastTimeRed > (1.0 / RED_FLASH_FREQUENCY)) { // Flashes (on and off) red light every 1/7 second
            setRedSignalLight(!isRedOn);
            lastTimeRed = time;
        }
    }
    
    private void manualLightsControl(Gamepad gamepad) {
        if (gamepad.getLeftButton()) {
            flashWhiteSignalLight();
        }
        else {
            setWhiteSignalLight(false);
        }
        if (gamepad.getRightButton()) {
            flashRedSignalLight();
        }
        else {
            setRedSignalLight(false);
        }
    }
    
    public void reset() {
        setCameraLight(false);
        setDirectionLight(false);
        setDirectionLight(0);
        setWhiteSignalLight(false);
        setRedSignalLight(false);
        lastTimeWhite = 0.0;
        lastTimeRed = 0.0;
    }
    
    /**
     * Go through all the logic for the lights.
     * @param gamepad Gamepad to do manual lights control with
     */
    public void runLogic(Gamepad gamepad) {
        // Manual control takes precedence over all other logic
        manualLightsControl(gamepad);
        /*if (gamepad.getLeftBumper() || gamepad.getLeftButton() || gamepad.getRightButton()) {
            manualLightsControl(gamepad);
        }*/
        // Various cases for lights logic that are not manual control
//        else if (Conveyor.getInstance().isBottomDiscDetected()) {
//            flashWhiteSignalLight();
//        }
//        else if (Shooter.getInstance().isHopperNotEmpty()) {
//            flashRedSignalLight();
//        }
        /*else {
            setWhiteSignalLight(false);
            setRedSignalLight(false);
        }*/
        
        // Turns on direction light only if shooter is running and tilter is not CV aiming.
        setDirectionLight(Shooter.getInstance().isShooterRunning() && !Tilter.getInstance().isCVAiming());
        if(Shooter.getInstance().isShooterRunning() && !Tilter.getInstance().isCVAiming()) {
            setDirectionLight(DIRECTION_LIGHT_INTENSITY);
        }
        else {
            setDirectionLight(0);
        }
        
        // Turns on camera light only when CV aiming.
        setCameraLight(Tilter.getInstance().isCVAiming());
    }
}
