/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserLoginDao;
import daoimpl.UserLoginDaoImpl;
import enums.Status;
import exception.BaseException;
import exception.SystemException;
import java.util.List;
import model.Product;
import model.ProductType;
import model.Store;
import model.User;
import model.UserLogin;
import static sun.security.jgss.GSSUtil.login;
import utils.AuthencationUtils;
import utils.PasswordEncoder;

/**
 *
 * @author User
 */
public class UserServiceImpl implements UserService {

    UserLoginDao userLoginDao = new UserLoginDaoImpl();

    @Override
    public UserLogin findByUser(String username) {
        return userLoginDao.findByUsername(username);
    }

    @Override
    public UserLogin checkUser(String username, String password) {
        UserLogin userLogin = userLoginDao.findByUsername(username);

        if (userLogin == null) {
            throw new BaseException("username veya password yanlisdir!");
        }
        boolean exist = AuthencationUtils.checkPassword(userLogin.getPassword(), PasswordEncoder.getMd5Password(password));

        if (!exist) {
            throw new BaseException("username veya password yanlisdir!");
        }

        if (!userLogin.getIsActive().equals(Status.ENABLED.getValue())) {
            throw new BaseException("İstifadeci Aktiv Deyil!");
        }

        return userLogin;
    }

    @Override
    public User register(User user) {
        UserLogin userLogin = user.getUserLogin();
        if (findByUser(userLogin.getUsername()) != null) {
            throw new BaseException("username already exist");
        }
        String encyriptedPass = PasswordEncoder.getMd5Password(userLogin.getPassword());
        userLogin.setPassword(encyriptedPass);
        user = userLoginDao.saveUser(user);
        return user;

    }

    @Override
    public void allProduct() {
        List<ProductType> productType = userLoginDao.allProduct();
        if(productType==null){
            throw new BaseException("PRODUCT MOVCUD DEYIl!");
        }
          System.out.println("ID   NAME");
        for (ProductType allProduct : productType) {
            System.out.println(allProduct.getId()+" "+allProduct.getName());
        }
    }
    
    

    @Override
    public void showProduct(Long id) {
        List<Product> product = userLoginDao.productList(id);
        
        if(product==null){
            throw new BaseException("PRODUCT MOVCUD DEYIL!");
        }
        for (Product products : product) {
            ProductType productType = products.getProductType();
            Store store = products.getStore();
            System.out.println(products.getId()+"--"+products.getName()+" -- "+products.getPrice()+" AZN -- "+store.getQuantity()+" Quantity");
        }
    }

    @Override
    public void getEmail(String username) {
      String email = userLoginDao.getEmail(username);
      
       StringBuilder sb = new StringBuilder(email);
        for (int i = 3; i < sb.length() && sb.charAt(i) != '@'; ++i) {
            sb.setCharAt(i, '*');
        }
        email =  sb.toString();
        System.out.println(email);
    }

    @Override
    public void checkEmail(String email, String username) {
        String getEmail  = userLoginDao.getEmail(username);
        if(!email.equals(getEmail)){
            throw  new BaseException("Email Duzgun Deyil!");
        }
    }

    
}
