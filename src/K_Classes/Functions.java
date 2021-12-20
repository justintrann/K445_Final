/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package K_Classes;

import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Justin
 */
public class Functions {
    
    //Quick Display Img
    public void displayImage(int width, int height, byte[] imagebyte, String imgPath, JLabel label)
    {
        //get img
        ImageIcon imgIco = null;
        if(imagebyte!= null) // get image using bytes
        {
            imgIco = new ImageIcon(imagebyte);
        }
        else // get image using path
        {
            try 
            {
                // get the image from the project resources
                imgIco = new ImageIcon(getClass().getResource(imgPath));
            } 
            catch (Exception e) 
            {
                // get the image from the project resources
                imgIco = new ImageIcon(imgPath);
            }
            
        }      
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
    
    //Show img path
    public String selectImage()
    {
         //select picture from the computer
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Profile Picture");
        fileChooser.setCurrentDirectory(new File("C:\\Image"));
        
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image", ".png", ".jpg", ".jpeg");
        fileChooser.addChoosableFileFilter(extensionFilter);
        
        int fileState = fileChooser.showSaveDialog(null);
        String path = "";
        
        if(fileState == JFileChooser.APPROVE_OPTION)
        {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return path;
    }
}
