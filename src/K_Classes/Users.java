/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package K_Classes;

import K_Form.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Users {
    private int id;
    private String firstname;
    private String lastname;
    private String usename;
    private String password;
    private String userType; //admin or simple user



    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsename() {
        return usename;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUserType() {
        return userType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public Users() {
    }

    public Users(int _id, String _firstname, String _lastname, String _usename, String _password,String _userType) {
        this.id = _id;
        this.firstname = _firstname;
        this.lastname = _lastname;
        this.usename = _usename;
        this.password = _password;
        this.userType = _userType;
        
    }
    //Insert Function for user Form
    public void addUsers(String _fname, String _lname, String _usename, String _password, String _user_type)
    {
        //add
        String insertQuery = "INSERT INTO users_table`(firstName`, lastName, usename, password, user_type) VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _usename);
            ps.setString(4, _password);
            ps.setString(5, _user_type);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","New User",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","New User",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //edit
    public void editUser(int _id, String _fname, String _lname, String _usename, String _password, String _user_type)
    {
        String editQuery = "UPDATE users_table SET firstName`=?,lastName`=?,`usename`=?,`password`=?,`user_type`=? WHERE `id`=?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _usename);
            ps.setString(4, _password);
            ps.setString(5, _user_type);
            ps.setInt(6, _id);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Updated User",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Updated User",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //For jTable userList. GET from DB to jTable
    public static ArrayList<Users> userList()
    {
       ArrayList<Users> uList = new ArrayList<>();
       
       K_Classes.Functions func = new Functions();
        
        try {
            ResultSet rs = func.getData("SELECT * FROM users_table");
            
            Users user;
            
            while (rs.next())
            {
                user = new Users(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), 
                        rs.getString("usename"), rs.getString("password"),rs.getString("user_type"));
                uList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      return uList;
    }
    
}   
/*
        public Users trylogin(String _username, String _password)
    {
        //Users user = new Users();
        K_Classes.Functions func = new Functions();
        ResultSet rs = func.getData("SELECT * FROM `user_type` WHERE username = '"+_username+"' and password = '"+_password+"'");
            
            Users user = null;
            
        try {
            if (rs.next())
            {
                user = new Users(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("usename"), rs.getString("password"), rs.getString("user_type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
}
*/
