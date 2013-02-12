/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;


/**
 *
 * @author Arfan
 */
public class Shooter {

    private static Shooter instance;
    private Victor shooter;
    private DigitalInput hopperSensor;
    private Solenoid shooterIn;
    private Solenoid shooterOut;
    private double lastExtendTime = 0.0;
    private double lastRetractTime = 0.0;
    private boolean firstShot;
    private boolean isShooting;
    private boolean lastSemiAutoShootButtonState;
    private boolean pistonExtended;
    
    // Time in seconds to allow piston to extend and retract before changing its state again
    public static final double PISTON_EXTEND_TIME = .4;
    public static final double PISTON_RETRACT_TIME = .1;
    
    private Shooter() {
        shooter = new Victor(Constants.SHOOTER_CHANNEL);
        hopperSensor = new DigitalInput(Constants.HOPPER_SENSOR);
        shooterIn = new Solenoid(Constants.SHOOTER_PLUNGER_IN_CHANNEL);
        shooterOut = new Solenoid(Constants.SHOOTER_PLUNGER_OUT_CHANNEL);
        firstShot = true;
        isShooting = false;
        pistonExtended = false;
        lastSemiAutoShootButtonState = false;
    }
    
    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }
    
    public void setSpeed(double speed) {
        shooter.set(speed);
    }
    
    public void runShooterOut() {
        shooter.set(1);
    }
    
    public void runShooterIn() {
        shooter.set(-1);
    }
    
    public void stop() {
        shooter.set(0);
    }
    
    public boolean isHopperFull() {
        return hopperSensor.get();
    }
    
    private void pistonExtend() {
            shooterIn.set(false);
            shooterOut.set(true);
    }
    
    private void pistonRetract() {
            shooterOut.set(false);
            shooterIn.set(true);
    }
    
    public void runPistonLogic() {
        double time = Timer.getFPGATimestamp();
        if (!readyToExtendPiston()) { // Piston is retracting; let it finish
            pistonRetract();
        }
        else if (time - lastExtendTime < .4) { // Piston recently extended; stay extended until the 400 ms has passed
            firstShot = false;
            pistonExtend();
        }
        else if (pistonExtended) { // Has been extended for more than 400 ms
            lastRetractTime = time;
            pistonRetract();
            pistonExtended = false;
        }
        else { // Nothing has happened for a while; stay retracted
            pistonRetract();
        }
    }
    
    private boolean readyToExtendPiston() {
        return Timer.getFPGATimestamp() - lastRetractTime >= .1 && (lastRetractTime > lastExtendTime || firstShot);
    }
    
    public void manualShooterControl(Gamepad gamepad) {
        /* Shooting and stopping commands persist */
        if (gamepad.getDPadRight()) {
            isShooting = true;
        } else if (gamepad.getDPadLeft()) {
            isShooting = false;
        }
        
        if (gamepad.getDPadDown()) { // Reverse only when button is held
            runShooterIn();
        } else if (isShooting) {
            runShooterOut();
        } else {
            stop();
        }
        
        // Make sure that the piston has been retracted 
        if(gamepad.getTopButton() && readyToExtendPiston()) { //full-auto
            //feed continuously
            pistonExtended = true;
            lastExtendTime = Timer.getFPGATimestamp();
            
        }
        if(gamepad.getRightBumper() && !lastSemiAutoShootButtonState && readyToExtendPiston()) { //semi-auto
            //feed if it's been enough time (0.4 seconds)
            pistonExtended = true;
            lastExtendTime = Timer.getFPGATimestamp();
            
        }
        lastSemiAutoShootButtonState = gamepad.getRightBumper();
    }
}
