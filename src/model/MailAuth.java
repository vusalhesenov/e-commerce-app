/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author User
 */
public class MailAuth extends Authenticator{
        private String username;
        private String password;

        public MailAuth(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,password);
        }    
    
}
