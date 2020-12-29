/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author User
 */
public class NewClass {

    public static void main(String[] args) {

        String email = "hesenovv55@gmail.com";

        StringBuilder sb = new StringBuilder(email);
        for (int i = 3; i < sb.length() && sb.charAt(i) != '@'; ++i) {
            sb.setCharAt(i, '*');
        }
        email = sb.toString();
        System.out.println(email);
    }

}
