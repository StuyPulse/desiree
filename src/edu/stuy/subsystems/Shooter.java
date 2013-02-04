/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
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
    
    public void setShooterSpeed(double speed) {
        shooter.set(speed);
    }
    
    public void shoot() {
        shooter.set(1);
    }
    
    public void stop() {
        shooter.set(0);
    }
}
