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
 * @author Arfan, eric, Seabass
 */
public class Shooter {

    private static Shooter instance;
    private Victor shooter;
    private DigitalInput hopperSensor;
    
    /**
     * Extends the piston.
     */
    private Solenoid pistonResetSolenoid;
    
    /**
     * Retracts the piston.
     */
    private Solenoid pistonLaunchSolenoid;
    
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
        pistonResetSolenoid = new Solenoid(Constants.SHOOTER_PLUNGER_IN_CHANNEL);
        pistonLaunchSolenoid = new Solenoid(Constants.SHOOTER_PLUNGER_OUT_CHANNEL);
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
    
    public boolean isHopperNotEmpty() {
        return hopperSensor.get();
    }
    
    /**
     * Retracts the piston to launch the disc.
     */
    private void pistonLaunch() {
            pistonResetSolenoid.set(false);
            pistonLaunchSolenoid.set(true);
    }
    
    /**
     * Extends the piston to reset.
     */
    private void pistonReset() {
            pistonLaunchSolenoid.set(false);
            pistonResetSolenoid.set(true);
    }
    
    /**
     * Runs continuously to update the state of the piston.
     */
    public void runPistonLogic() {
        double time = Timer.getFPGATimestamp();
        if (!hasPistonFinishedRetracting()) { // Piston is still retracting; let it finish
            pistonReset();
        }
        else if (time - lastExtendTime < PISTON_EXTEND_TIME) { // Piston recently extended or is about to be extended; stay extended until PISTON_EXTEND_TIME has passed
            firstShot = false;
            pistonLaunch();
        }
        else if (pistonExtended) { // Has been extended for more than PISTON_EXTEND_TIME; start retracting
            lastRetractTime = time;
            pistonReset();
            pistonExtended = false;
        }
        else { // Nothing has happened for a while; stay retracted
            pistonReset();
        }
    }
    
    /**
     * Returns whether the piston has had ample time to retract since the last shot.
     * @return Whether the piston is ready to fire
     */
    private boolean hasPistonFinishedRetracting() {
        return Timer.getFPGATimestamp() - lastRetractTime >= PISTON_RETRACT_TIME && (lastRetractTime > lastExtendTime || firstShot);
    }
    
    /**
     * Start extending the piston for PISTON_EXTEND_TIME seconds.
     * runPistonLogic() must be running in teleopPeriodic() for this to actually
     * extend the piston.
     */
    public void firePiston() {
        pistonExtended = true;
        lastExtendTime = Timer.getFPGATimestamp();
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
        if(gamepad.getTopButton() && hasPistonFinishedRetracting()) { //full-auto
            //feed continuously
            firePiston();
            
        }
        if(gamepad.getRightBumper() && !lastSemiAutoShootButtonState && hasPistonFinishedRetracting()) { //semi-auto
            //feed if it's been enough time (0.4 seconds)
            firePiston();
            
        }
        lastSemiAutoShootButtonState = gamepad.getRightBumper();
    }
}
