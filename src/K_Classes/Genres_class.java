/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package K_Classes;

import K_Form.Genres;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Justin
 */
public class Genres_class {
        
    private int id;
    private String name;

    public Genres_class(){};
    
    public Genres_class(int _id, String _name)
    {
        this.id = _id;
        this.name = _name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    K_Classes.Functions func = new Functions();
    
    //Insert Function for Genres Form
    public void addGenres(String _name)
    {
        String insertQuery = "INSERT INTO `paint_genres`(`name`) VALUES (?)";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            
            ps.setString(1, _name);
            
            if(ps.executeUpdate() == 1)
            {
                JOptionPane.showMessageDialog(null, "Successfully","New Genres",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","New Genres",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genres_class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Edit
    public void editGenres(int _id, String _name)
    {
        String editQuery = "UPDATE `paint_genres` SET `name` = ? WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setString(1,_name);
            ps.setInt(2,_id);
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Updated Genres",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Updated Genres",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genres_class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        //Delete
    public void deleteGenres(int _id)
    {
        String deleteQuery = "DELETE FROM `paint_genres` WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(deleteQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setInt(1,_id);
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Deleted Genres",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Deleted Genres",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genres_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //For jTable arrayList. GET from DB to jTable
    public static ArrayList<Genres_class> genresList()
    {
       ArrayList<Genres_class> glist = new ArrayList<>();
        K_Classes.Functions func = new Functions();
        try {
            ResultSet rs = func.getData("SELECT * FROM `paint_genres`");
            
            Genres_class genrc;
            
            while (rs.next())
            {
                genrc = new Genres_class(rs.getInt("id"), rs.getString("name"));
                glist.add(genrc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Genres_class.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      return glist;
    }
    
    public HashMap<String, Integer> getGenresMap()
    {
        HashMap<String, Integer> map = new HashMap<>();
        
        try {
            ResultSet rs = func.getData("SELECT * FROM `paint_genres`");
            
            Genres_class genrc;
            
            while (rs.next())
            {
                genrc = new Genres_class(rs.getInt("id"), rs.getString("name"));
                map.put(genrc.getName(), genrc.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Genres_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
    }
    
    //Function  to get author by id
    public Genres_class getGenresbyID(Integer id)
    {
        K_Classes.Functions func = new Functions();
        ResultSet rs = func.getData("SELECT * FROM `paint_genres` WHERE id = "+id);
            
            Genres_class genre = null;
            
        try {
            if (rs.next())
            {
               genre = new Genres_class(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Genres_class.class.getName()).log(Level.SEVERE, null, ex);
        }
            return genre;
    }
    
}//End O F
