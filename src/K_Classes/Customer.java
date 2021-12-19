/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package K_Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author phuvu
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String gender;
    private String email;
    private byte[] picture;
    
    public Customer(){}
    public Customer(int _id, String _fname, String _lname, String _phone, String _address, String _gender, String _email, byte[] _picture){
        this.id = _id;
        this.firstName = _fname;
        this.lastName = _lname;
        this.phone = _phone;
        this.address = _address;
        this.gender = _gender;
        this.email = _email;
        this.picture = _picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPicture() {
        return picture;
    }
    
    //Insert Function for Author Form
    public void addCustomer(String _fname,String _lname, String _phone, String _address, String _gender, String _email, byte[] _picture)
    {
        String insertQuery = "INSERT INTO `customer`(`firstName`, `lastName`, `phone`, `address`, `gender`, `email`, `picture`) VALUES (?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _phone);
            ps.setString(4, _address);
            ps.setString(5, _gender);
            ps.setString(6, _email);
            ps.setBytes(7, _picture);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","New Customer",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","New Customer",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Edit
    public void editCustomer(Integer _id, String _fname,String _lname, String _phone, String _address, String _gender, String _email, byte[] _picture)
    {
        String editQuery = "UPDATE `customer` SET `firstName`=?,`lastName`=?',`phone`=?,`address`=?,`gender`=?,`email`=?,`picture`=? WHERE `id` =?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _phone);
            ps.setString(4, _address);
            ps.setString(5, _gender);
            ps.setString(6, _email);
            ps.setBytes(7, _picture);
            ps.setInt(8, _id);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Updated Customer",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Updated Customer",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Delete
    public void deleteCustomer(int _id)
    {
        String deleteQuery = "DELETE FROM `customer` WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(deleteQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setInt(1,_id);
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Deleted Customer",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Deleted Customer",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // get customer by id
    public Customer getCustomerbyId(Integer _id) throws SQLException
    {
        Functions func = new Functions();
        String query = "SELECT * FROM `customer` WHERE `id`="+_id;
        ResultSet rs = func.getData(query);
        if(rs.next())
        {
            return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBytes(8));
        }
        else{
            return null;
        }
    }
}
