/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author kevin
 */
public abstract class Gamepad extends Joystick {
    public Gamepad(int port) {
        super(port);
    }
    
    public abstract double getLeftX();
    public abstract double getLeftY();
    public abstract double getRightX();
    public abstract double getRightY();
    public abstract double getDPadX();
    public abstract double getDPadY();

    public abstract boolean getLeftBumper();
    public abstract boolean getRightBumper();
    public abstract boolean getLeftTrigger();
    public abstract boolean getRightTrigger();

    public abstract boolean getLeftButton();
    public abstract boolean getBottomButton();
    public abstract boolean getRightButton();
    public abstract boolean getTopButton();

    public abstract boolean getBackButton();
    public abstract boolean getStartButton();

    public abstract boolean getLeftAnalogButton();
    public abstract boolean getRightAnalogButton();
}
