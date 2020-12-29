/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author User
 */
public enum Status {
    DISABLED(0),
    ENABLED(1);
    
    final int value;

    private Status(int value) {
        this.value = value;
    }

    public static Status getDISABLED() {
        return DISABLED;
    }

    public static Status getENABLED() {
        return ENABLED;
    }

    public int getValue() {
        return value;
    }
    
}
