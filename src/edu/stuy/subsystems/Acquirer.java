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
 * @author 694
 */
public class Acquirer {

    private static Acquirer instance;
    private Victor acquirer;

    private Acquirer() {
        acquirer = new Victor(Constants.ACQUIRER_CHANNEL);
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

    public void acquire() {
        spin(1);
    }

    public void acquireReverse() {
        spin(-1);
    }

    public void stop() {
        spin(0);
    }

    public double getRollerSpeed() {
        return acquirer.get();
    }

    public boolean isAcquiring() {
        return getRollerSpeed() > 0;
    }

    public void manualAcquirerControl(Gamepad gamepad) {
        if (gamepad.getTopButton()) {
            acquire();
        } else if (gamepad.getBottomButton()) {
            acquireReverse();
        } else {
            stop();
        }

    }
}
