/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package K_Classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String insertQuery = "INSERT INTO `users_table`(`firstName`, `lastName`, `usename`, `password`, `user_type`) VALUES (?,?,?,?,?)";
        
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
    
    
}
