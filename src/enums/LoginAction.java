/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public enum LoginAction {
    LOGIN(1),
    REGISTER(2),
    FORGOT_PASSWORD(3);
    
    
    private static final Map<Integer, LoginAction> ENUM_VALUES = new HashMap<>();

    static {
        for (LoginAction type : values()) {
            ENUM_VALUES.put(type.value, type);
        }
    }
    
    private final int value;
    
    private LoginAction(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    
    public static LoginAction getAction(int code){
      return ENUM_VALUES.get(code);
    }
}
