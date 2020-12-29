/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author User
 */
public class AuthencationUtils {
 
    public static boolean checkPassword(String orginalPass , String inputPass){
        
        if(orginalPass.equals(inputPass)){
            return true;
        }
        return false;
    }

}
