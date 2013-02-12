/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Class for both the Logitech Dual Action 2 Gamepad and the Logitech Gamepad
 * F310. The Logitech Gamepad F310 must have the switch on the back set to "D"
 * for this class to work. This class probably also works with the Logitech
 * Wireless Gamepad F710 (untested, but it has the exact same layout as the
 * F310).
 * @author kevin
 */
public class Gamepad extends Joystick {
    public Gamepad(int port) {
        super(port);
    }
    
    public double getLeftX() {
        return getRawAxis(1);
    }
    
    public double getLeftY() {
        return getRawAxis(2);
    }
    
    public double getRightX() {
        return getRawAxis(3);
    }
    
    public double getRightY() {
        return getRawAxis(4);
    }
    
    public double getDPadX() {
        return getRawAxis(5);
    }
    
    public double getDPadY() {
        return getRawAxis(6);
    }
    
    public boolean getDPadUp() {
        return getDPadY() < -.5;
    }
    
    public boolean getDPadDown() {
        return getDPadY() > .5;
    }
    
    public boolean getDPadLeft() {
        return getDPadX() < -.5;
    }
    
    public boolean getDPadRight() {
        return getDPadX() > .5;
    }
    
    public boolean getLeftBumper() {
        return getRawButton(5);
    }
    
    public boolean getRightBumper() {
        return getRawButton(6);
    }
    
    public boolean getLeftTrigger() {
        return getRawButton(7);
    }
    
    public boolean getRightTrigger() {
        return getRawButton(8);
    }

    public boolean getLeftButton() {
        return getRawButton(1);
    }

    public boolean getBottomButton() {
        return getRawButton(2);
    }

    public boolean getRightButton() {
        return getRawButton(3);
    }

    public boolean getTopButton() {
        return getRawButton(4);
    }

    public boolean getBackButton() {
        return getRawButton(9);
    }

    public boolean getStartButton() {
        return getRawButton(10);
    }

    public boolean getLeftAnalogButton() {
        return getRawButton(11);
    }

    public boolean getRightAnalogButton() {
        return getRawButton(12);
    }
}
