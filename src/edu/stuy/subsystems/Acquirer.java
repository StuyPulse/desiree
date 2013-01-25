/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author 694
 */
public class Acquirer {
    
    private static Acquirer instance;
    private Talon acquirer;
    private boolean isAcquiring;

    private Acquirer() {
        acquirer = new Talon(Constants.ACQUIRER_CHANNEL);
        isAcquiring = false;
    }

    public static Acquirer getInstance() {
        if (instance == null) {
            instance = new Acquirer();
        }
        return instance;
    }
    
    private void spin(double speed) {
        acquirer.set(speed);
    }
    
    public void forwardSpin(){
        spin(1);
        isAcquiring = true;
    }

    public void backwardSpin(){
        spin(-1);
        isAcquiring = false;
    }
    
    public void stop() {
        spin(0);
        isAcquiring = false;
    }
    
    public double getRollerSpeed() {
        return acquirer.get();
    }
    
    public boolean isAcquiring() {
        return isAcquiring;
    }

}

