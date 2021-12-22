/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K_Classes;

import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Issue_Painting {
    private int painting_id;
    private int customer_id;
    private String status; // issued- returned -lost
    private String issue_date;
    private String Return_date;
    private String note;
    
    Paintings_class painting = new Paintings_class();
    //K_Classes.Paintings_class paint = new K_Classes.Paintings_class();
    //add a new issue
    public void addIssue(int _issue_id, int _customer_id, String _status, String _issue_date, String _return_date, String _note)
    {
         String insertQuery ="INSERT INTO `issue_painting`(`painting_id`, `customer_id`, `status`, `issue_date`, `return_date`, `note`) VALUES (?,?,?,?,?,?)";
         
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            
            ps.setInt(1, _issue_id);
            ps.setInt(2, _customer_id);
            ps.setString(3, _status);
            ps.setString(4, _issue_date);
            ps.setString(5, _return_date);
            ps.setString(6, _note);
           
            
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Issue Added","add issue",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Issue Not Added","add issue",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Issue_Painting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean checkPaintingAvailability(int _painting_id)
    {
        boolean availability = false;
        try {
            // get the paint quantity
            Paintings_class selectedPainting = painting.getPaintbyId(_painting_id);
            int quantity= selectedPainting.getQty();
            
            //get number of paint issue 
            int issued_painting_count = countData(_painting_id);
            
            if(quantity > issued_painting_count)
            {
                availability = true;
            }
            else
            {
                availability = false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Issue_Painting.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return availability;
    }
    
    //create func to count issued paint
      public int countData(int _painting_id)
    {
        int total = 0;
        ResultSet rs;
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement("SELECT COUNT(*) as total FROM `issue_painting` WHERE painting_id = ?  and `status` = 'issued'");
            ps.setInt(1,_painting_id);
            rs = ps.executeQuery();
            
            
            if(rs.next())
            {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Issue_Painting.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
