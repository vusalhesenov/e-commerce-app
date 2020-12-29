/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DbConnetion;
import dao.UserLoginDao;
import exception.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductType;
import model.Store;
import model.User;
import model.UserLogin;

public class UserLoginDaoImpl implements UserLoginDao {

    @Override
    public UserLogin findByUsername(String username) {

        String sql = "SELECT ID , USERNAME , PASSWORD, IS_ACTIVE , USER_TYPE FROM USER_LOGIN WHERE USERNAME=?";

        try (Connection connection = DbConnetion.getConnection();
                PreparedStatement preparedStatement = connection.prepareCall(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                UserLogin userLogin = new UserLogin();
                userLogin.setId(resultSet.getLong("ID"));
                userLogin.setUsername(resultSet.getString("USERNAME"));
                userLogin.setPassword(resultSet.getString("PASSWORD"));
                userLogin.setIsActive(resultSet.getInt("IS_ACTIVE"));
                userLogin.setUserType(resultSet.getInt("USER_TYPE"));
                return userLogin;
            }
            return null;
        } catch (SQLException ex) {
            throw new SystemException("System Xetasi", ex);
        }
    }

    @Override
    public User saveUser(User user) {
        UserLogin userLogin = user.getUserLogin();

        String saveUserLogin = "INSERT INTO USER_LOGIN(USERNAME,PASSWORD) VALUES (?,?)";
        String saveUser = "INSERT INTO USER(FIRST_NAME,LAST_NAME,EMAIL ,PHONE_NUMBER,USER_LOGIN_ID) VALUES (?,?,?,?,?)";

        Connection connection = DbConnetion.getConnection();

        try (PreparedStatement preparedStatementUserLogin = connection.prepareStatement(saveUserLogin, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatementUser = connection.prepareStatement(saveUser)) {

            preparedStatementUserLogin.setString(1, userLogin.getUsername());
            preparedStatementUserLogin.setString(2, userLogin.getPassword());

            int exist = preparedStatementUserLogin.executeUpdate();
            if (exist > 0) {
                ResultSet resultSet = preparedStatementUserLogin.getGeneratedKeys();
                if (resultSet.next()) {
                    preparedStatementUser.setLong(5, resultSet.getLong(1));
                }
            }
            preparedStatementUser.setString(1, user.getFirstName());
            preparedStatementUser.setString(2, user.getLastName());
            preparedStatementUser.setString(3, user.getEmail());
            preparedStatementUser.setString(4, user.getPhoneNumber());
            preparedStatementUser.executeUpdate();
            return user;

        } catch (SQLException ex) {
            DbConnetion.rollBack(connection);
            throw new SystemException("System Xetasi", ex);
        } finally{
            DbConnetion.close(connection);
        }
    }

    @Override
    public List<Product> productList(Long id){
        
        List<Product> productList = new ArrayList<>();
        String Sql = "SELECT * FROM product_type join product on product.product_type_id = product_type.id  join store on store.product_id = product.id where product.product_type_id = ?";
        
        Connection connection = DbConnetion.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(Sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                ProductType productType = new ProductType();
                Store store = new Store();
                product.setId(resultSet.getLong("PRODUCT.ID"));
                product.setName(resultSet.getString("PRODUCT.NAME"));
                product.setPrice(resultSet.getDouble("PRODUCT.PRICE"));
                product.setIsActive(resultSet.getInt("PRODUCT.IS_ACTIVE"));
             
                productType.setId(resultSet.getLong("PRODUCT_TYPE.ID"));
                productType.setName(resultSet.getString("PRODUCT_TYPE.NAME"));
                productType.setIsActive(resultSet.getInt("PRODUCT_TYPE.IS_ACTIVE"));

                store.setId(resultSet.getLong("STORE.ID"));
                store.setQuantity(resultSet.getDouble("STORE.QUANTITY"));
                store.setProductId(resultSet.getLong("STORE.PRODUCT_ID"));
               
                product.setProductType(productType);
                product.setStore(store);
                productList.add(product);
            }
            return productList;
            
        }catch(SQLException e){
            throw new SystemException("System Xetasi", e);
        }
        finally{
            DbConnetion.close(connection);
        }
    }

    @Override
    public List<Product> buyProduct(Long id) {
      return null;
    }

    @Override
    public List<ProductType> allProduct() {
        List<ProductType> productTypesList = new ArrayList<>();
        String sql = "SELECT ID,NAME FROM PRODUCT_TYPE";
        Connection connection = DbConnetion.getConnection();
 
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){            
                ProductType productType = new ProductType();
                productType.setId(resultSet.getLong("ID"));
                productType.setName(resultSet.getString("NAME"));
                productTypesList.add(productType);
            }
            return productTypesList;
            
        }catch(SQLException e){
            throw new SystemException("System Xetasi", e);
        }
    }

    @Override
    public String getEmail(String username) {
      String sql = "SELECT email FROM user where user_login_id = (select id from user_login where username = ?)";
      
      Connection connection = DbConnetion.getConnection();
      
      try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
          preparedStatement.setString(1, username);
          
          ResultSet resultSet = preparedStatement.executeQuery();
          if (resultSet.next()){
              String email = resultSet.getString("EMAIL");
              return email;
          }
          return null;
          
      }catch(SQLException e){
          throw new SystemException("System Xetasi", e);
      }
    }
}