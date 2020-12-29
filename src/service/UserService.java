/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Product;
import model.User;
import model.UserLogin;

/**
 *
 * @author User
 */
public interface UserService {
    
     UserLogin findByUser(String username);
    
     UserLogin checkUser(String username , String password);
    
     User register(User user);
     
     void  showProduct(Long id);
     
     void allProduct();

    public void getEmail(String username);

    public void checkEmail(String email , String username);
    
    

}

