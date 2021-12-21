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
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String field_Of_Expertise;
    private String about;
    
    public Author(){}
    public Author(int _id, String _fname, String _lname, String _expertise, String _about){
        this.id = _id;
        this.firstName = _fname;
        this.lastName = _lname;
        this.field_Of_Expertise = _expertise;
        this.about = _about;
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

    public String getField_Of_Expertise() {
        return field_Of_Expertise;
    }

    public String getAbout() {
        return about;
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

    public void setField_Of_Expertise(String field_Of_Expertise) {
        this.field_Of_Expertise = field_Of_Expertise;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    
    //Insert Function for Author Form
    public void addAuthor(String _fname,String _lname,String _expertise,String _about)
    {
        String insertQuery = "INSERT INTO `author`(`firstName`, `lastName`, `expertise`, `about`) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _expertise);
            ps.setString(4, _about);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","New Author",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","New Author",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Edit
    public void editAuthor(int _id, String _fname,String _lname,String _expertise,String _about)
    {
        String editQuery = "UPDATE `author` SET `firstName`=?,`lastName`=?,`expertise`=?,`about`=? WHERE `id` =?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _expertise);
            ps.setString(4, _about);
            ps.setInt(5, _id);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Updated Author",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Updated Author",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        //Delete
    public void deleteAuthor(int _id)
    {
        String deleteQuery = "DELETE FROM `author` WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(deleteQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setInt(1,_id);
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Deleted Author",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Deleted Author",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //For jTable arrayList. GET from DB to jTable
    public static ArrayList<Author> authorsList()
    {
       ArrayList<Author> aList = new ArrayList<>();
       
       K_Classes.Functions func = new Functions();
        
        try {
            ResultSet rs = func.getData("SELECT * FROM `author`");
            
            Author author;
            
            while (rs.next())
            {
                author = new Author(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("expertise"), rs.getString("about"));
                aList.add(author);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      return aList;
    }
    
    //Function  to get author by id
    public Author getAuthorbyID(Integer id)
    {
        K_Classes.Functions func = new Functions();
        ResultSet rs = func.getData("SELECT * FROM `author` WHERE id = "+id);
            
            Author author = null;
            
        try {
            if (rs.next())
            {
                author = new Author(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("expertise"), rs.getString("about"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
            return author;
    }
    
} //EOF
