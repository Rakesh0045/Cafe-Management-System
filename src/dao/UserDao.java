/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;
import cafe.management.DB_Operations;
import model.User;
import java.sql.*;
import java.util.*;

/**
 *
 * @author rakes
 */
public class UserDao {
    
        public static void save(User user){
        String query = "insert into user(name,email,mobileNumber,address,password,securityQuestion,answer,status) values('"+user.getName()+"','"+user.getEmail()+"','"+user.getMobileNumber()+"','"+user.getAddress()+"','"+user.getPassword()+"','"+user.getSecurityQuestion()+"','"+user.getAnswer()+"','false') ";
        DB_Operations.setDataOrDelete(query, "Registered Successfully! Wait for Admin approval");
    }
        
        public static User login(String email,String password){
            User user = null;
            try{
                ResultSet rs = DB_Operations.getData("select * from user where email='"+email+"' and password='"+password+"' ");
                while(rs.next()){
                    user = new User();
                    user.setStatus(rs.getString("status"));
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return user;
        }
        
        public static User getSecurityQuestion(String email){
            User user = null;
            try{
                ResultSet rs = DB_Operations.getData("select * from user where email='"+email+"' ");
                while(rs.next()){
                    user = new User();
                    user.setSecurityQuestion(rs.getString("securityQuestion"));
                    user.setAnswer(rs.getString("answer"));
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return user;
        }
        
        public static void update(String email, String newPassword){
            String query = "update user set password='"+newPassword+"' where email='"+email+"' ";
            DB_Operations.setDataOrDelete(query, "Password Changed Successfully");
        }
        
        public static ArrayList<User> getAllRecords(String email){
            ArrayList<User> arrayList = new ArrayList<>();
            try{
                ResultSet rs = DB_Operations.getData("select * from user where email like '%"+email+"%'");
                while(rs.next()){
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setMobileNumber(rs.getString("mobileNumber"));
                    user.setAddress(rs.getString("address"));
                    user.setSecurityQuestion(rs.getString("securityQuestion"));
                    //user.setAnswer(rs.getString("answer"));
                    user.setStatus(rs.getString("status"));
                   arrayList.add(user);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return arrayList;
        }
        
        public static void changeStatus(String email, String status){
            String query = "update user set status='"+status+"' where email='"+email+"'";
            DB_Operations.setDataOrDelete(query, "Status changed Successfully");
        }
        
        public static void changePassword(String email, String oldPassword,String newPassword){
            try{
                ResultSet rs = DB_Operations.getData("select * from user where email='"+email+"' and password='"+oldPassword+"'");
                if(rs.next()){
                    update(email, newPassword);
                }else{
                    JOptionPane.showMessageDialog(null, "Old Password is Wrong");
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        
        public static void changeSecurityQuestion(String email,String password,String securityQuestion,String answer){
            try{
                ResultSet rs = DB_Operations.getData("select * from user where email='"+email+"' and password='"+password+"'");
                if(rs.next()){
                    update(email, securityQuestion, answer);
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong Password");
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        
        public static void update(String email, String securityQuestion, String answer){
            String query = "update user set securityQuestion='"+securityQuestion+"',answer='"+answer+"' where email='"+email+"'";
            DB_Operations.setDataOrDelete(query, "Security Question changed Successfully");
        }
}
