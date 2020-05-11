/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JayJomJohn
 */
public class JavaConnector {
    Connection conn = null;
   public static Connection ConnectDb(){
      try{
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","root","");
          System.out.println("You Are Connected to Database!");
	  return conn;
          
      }
      catch(ClassNotFoundException | SQLException e){
          JOptionPane.showMessageDialog(null,e);
          return null;
      }
  
          
}
}
