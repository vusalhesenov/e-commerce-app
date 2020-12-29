/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductType;
import model.User;
import model.UserLogin;

/**
 *
 * @author User
 */
public interface UserLoginDao {
    
     UserLogin findByUsername(String username);
    
     User saveUser(User user);
    
     List<Product> productList(Long id);
     
     List<Product> buyProduct(Long id);
     
     List<ProductType> allProduct();

    public String getEmail(String username);
        
}
