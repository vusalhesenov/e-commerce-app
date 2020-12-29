/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import exception.SystemException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DbProperties;

/**
 *
 * @author User
 */
public class DbConnetion {
    
    
    public static DbProperties getDbProperties(){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("db.properties");
            properties.load(fileInputStream);
            DbProperties dbProperties = new DbProperties();
            dbProperties.setUrl(properties.getProperty("db.url"));
            dbProperties.setUsername(properties.getProperty("db.username"));
            dbProperties.setPassword(properties.getProperty("db.password"));
            return dbProperties;
        } catch (IOException ex) {
            throw new IllegalArgumentException("system error");
        }
    }
    
    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(getDbProperties().getUrl(),getDbProperties().getUsername(),getDbProperties().getPassword());
            return connection;
        } catch (SQLException ex) {
            throw new IllegalStateException("Mysql'e Baglanan Zaman Xeta");
        }
    }
    
    public static void close(Connection connection){
        if(connection!= null){
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new SystemException("Connection Baglanan Zaman Xeta Bas Verdi !",ex);
            }
        }
    }
    
    public static void rollBack(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException ex) {
            printStackTrace();
            throw new SystemException("Connection Rollbak Olan Zaman Xeta Bas Verdi", ex);
        }
    }
}
