/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package K_Classes;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Justin
 */
public class Functions {
    
    //Quick Display Img
    public void displayImage(int width, int height, byte[] imagebyte, String imgPath, JLabel label)
    {
        //get img
        ImageIcon imgIco = new ImageIcon(getClass().getResource(imgPath));
        //set true scale
        Image img = imgIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        label.setIcon(new ImageIcon(img));

    }
    //create a function to return a resultSet
    public ResultSet getData(String query)
    {
        PreparedStatement ps;
        ResultSet rs = null;
        
        try {
            ps = DB.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            } 
        catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
