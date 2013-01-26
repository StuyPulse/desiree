/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Arfan
 */
public class Shooter {
// dummy code
    private static Shooter instance;
    private Talon shooter;
    
    private Shooter() {
        shooter = new Talon(Constants.SHOOTER_CHANNEL);
    }
    
    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }
   
}
