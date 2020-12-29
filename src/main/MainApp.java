/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.UserLoginDao;
import daoimpl.UserLoginDaoImpl;
import enums.LoginAction;
import exception.BaseException;
import exception.SystemException;
import java.util.Scanner;
import model.User;
import model.UserLogin;
import service.UserService;
import service.UserServiceImpl;
import utils.PasswordEncoder;

/**
 *
 * @author User
 */
public class MainApp {
    
    static Scanner sc = new Scanner(System.in);
    UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        System.out.println("***Systeme Xos Gelmissiniz***");
        System.out.println("");
        
        try{
            MainApp mainApp = new MainApp();
            LoginAction loginAction = mainApp.loginAction();
            
            switch(loginAction){
                case LOGIN:
                    mainApp.login();
                    break;
                
                case REGISTER:
                    mainApp.register();
                    break;
                    
                case FORGOT_PASSWORD:
                    mainApp.forgotPassword();
            }
        }catch(Exception e){
            if(e instanceof SystemException){
                System.err.println(e.getMessage());
            }
            
            if(e instanceof BaseException){
                System.err.println(e.getMessage());
            }
            else{
                System.err.println("Bilinmeyen Bir Xeta Bas Verdi"+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    private User register(){
        System.out.println("***Qeydiyyat Bolmesine Xos Gelmissiniz***");
        System.out.println("");
        
        System.out.print("please input username :");
        String username = sc.next();
     
        System.out.print("please input password :");
        String password = sc.next();
        
        System.out.print("please input confirmPassword :");
        String comfirmPass = sc.next();
        
        System.out.print("please input name :");
        String firstName = sc.next();
        
        System.out.print("please input surname :");
        String LastName = sc.next();
        
        System.out.print("please input email :");
        String email = sc.next();
        
        System.out.print("please input phoneNumber :");
        String phoneNumber = sc.next();
        
        if(!password.equals(comfirmPass)){
            throw new BaseException("password is not confirmed!");
        }
        
        UserLogin userLogin = new UserLogin(username,password);
        User user = new User(firstName,LastName,email,phoneNumber,userLogin);
        
        userService.register(user);
        System.out.println("Istifadeci Elave Olundu");
        
        return user;
    }
    
    
    private LoginAction loginAction(){
       
        System.out.println(LoginAction.LOGIN+" -- "+LoginAction.LOGIN.getValue()); 
        System.out.println(LoginAction.REGISTER+" -- "+LoginAction.REGISTER.getValue());
        System.out.println(LoginAction.FORGOT_PASSWORD+" -- "+LoginAction.FORGOT_PASSWORD.getValue());
        
        System.out.print("Zehmet Olmasa LoginAction Secin :");
        int loginAction = sc.nextInt();
        return LoginAction.getAction(loginAction);
    }
    
    private void login(){
        System.out.println("***Giris Bolmesine Xos Gelmissiniz***");
        System.out.println("");
        
        System.out.print("please input username :");
        String username = sc.next();
        
        System.out.print("please input password :");
        String password = sc.next();
        
        userService.checkUser(username, password);
        System.out.println("Systeme Daxil Oldunuz");
        System.out.println("");
        
        MainApp mainApp = new MainApp();
        mainApp.ProductCategory();
        
    }
    
    public void ProductCategory(){
        System.out.println("***CATEGORY***");
        userService.allProduct();
        System.out.print("Zehmet Olmasa Praduct Secin :");
        Long secim = sc.nextLong();
        System.out.println("---------------------------------------");
        userService.showProduct(secim);
        
    }
    

    private void forgotPassword() {
        System.out.println("***FORGOT PASSWORD***");
        System.out.print("Zehmet Olmasa username Daxil Edin :");
        String username = sc.next();
        System.out.println("----------------------------------");
        userService.getEmail(username);
        System.out.println("----------------------------------");
        System.out.print("Zehmet Olmasa Emaili Daxi Edin :");
        String email = sc.next();
        userService.checkEmail(email,username);
        System.out.println("Emaile Parol Gonderildi");
        
    
    }
}
