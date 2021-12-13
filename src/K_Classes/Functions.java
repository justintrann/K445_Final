/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package K_Classes;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Justin
 */
public class Functions {
    
    //Quick Display Img
    public void displayImage(int width, int height, String imgPath, JLabel label)
    {
        //get img
        ImageIcon imgIco = new ImageIcon(getClass().getResource(imgPath));
        //set true scale
        Image img = imgIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        label.setIcon(new ImageIcon(img));

    }
    
}
