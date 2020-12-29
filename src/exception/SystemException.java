/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author User
 */
public class SystemException extends RuntimeException{
    
    public SystemException(String message , Throwable throwable){
        super(message, throwable);
    }
}
