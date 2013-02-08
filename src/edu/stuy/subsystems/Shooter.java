/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.Victor;


/**
 *
 * @author Arfan
 */
public class Shooter {

    private static Shooter instance;
    private Victor shooter;
    
    private Shooter() {
        shooter = new Victor(Constants.SHOOTER_CHANNEL);
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
    
    public void shoot() {
        shooter.set(1);
    }
    
    public void shootReverse() {
        shooter.set(-1);
    }
    
    public void stop() {
        shooter.set(0);
    }
    
    public void manualShooterControl(Gamepad gamepad) {
        if(gamepad.getRightTrigger()) {
            shoot();
        }
        else if(gamepad.getRightBumper()) {
            shootReverse();
        }
        else {
            stop();
        }
    }
    
}
