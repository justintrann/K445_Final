/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K_Classes;

import com.mysql.jdbc.Statement;
import java.io.ObjectInputFilter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    //GET-SET

    public void setPainting_id(int painting_id) {
        this.painting_id = painting_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public void setReturn_date(String Return_date) {
        this.Return_date = Return_date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPainting(Paintings_class painting) {
        this.painting = painting;
    }

    public int getPainting_id() {
        return painting_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getStatus() {
        return status;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public String getReturn_date() {
        return Return_date;
    }

    public String getNote() {
        return note;
    }

    public Paintings_class getPainting() {
        return painting;
    }
    
    //END GET-SET
    
    //Constructor
    
    public Issue_Painting(){}

    public Issue_Painting(int _painting_id, int _customer_id, String _status, String _issue_date, String _Return_date, String _note) {
        this.painting_id = _painting_id;
        this.customer_id = _customer_id;
        this.status = _status;
        this.issue_date = _issue_date;
        this.Return_date = _Return_date;
        this.note = _note;
    }
    
    Paintings_class paint = new Paintings_class();
    Functions func = new Functions();
    
    //
    
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
                JOptionPane.showMessageDialog(null, "Successfully","add issue",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","add issue",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Issue_Painting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void updateIssue(int _issue_id, int _customer_id, String _status,String _issue_date, String _return_date, String _note)
    {
         String updateQuery ="UPDATE `issue_painting` SET `status`=?,`return_date`=?,`note`=? WHERE `painting_id`=? AND `customer_id`=? AND `issue_date`=?";
         
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(updateQuery);
            
            ps.setString(1, _status);
            ps.setString(2, _return_date);
            ps.setString(3, _note);
           
            ps.setInt(4, _issue_id);
            ps.setInt(5, _customer_id);
            ps.setString(6, _issue_date);
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Status Update",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Status Update",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Issue_Painting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //
    
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
      
    //For jTable arrayList. GET from DB to jTable . AND for SEARCHING in Issue/Return/Lost
    public ArrayList<Issue_Painting> issuePaintingList(String _status)
    {
       ArrayList<Issue_Painting> iList = new ArrayList<>();
       
       K_Classes.Functions func = new Functions();
       String query;
       
       if(_status.equals("") || _status.equals("All"))
       {
           query = "SELECT * FROM `issue_painting`";
       }
       else
       {
           query = "SELECT * FROM issue_painting WHERE status = '"+ _status +"'";
       }
      
           
        try {
            
            ResultSet rs = func.getData(query);
            
            Issue_Painting issPainting;
            
            while (rs.next())
            {
                issPainting = new Issue_Painting(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
                
                iList.add(issPainting);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Issue_Painting.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      return iList;
    }
      
       //Delete
    public void deletePaintingFromIssue(int _paint_id, int _cust_id, String _issue_date)
    {
        String deleteQuery = "DELETE FROM `issue_painting` WHERE `painting_id`=? AND `customer_id`=? AND `issue_date`=?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(deleteQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setInt(1,_paint_id);
            ps.setInt(2,_cust_id);
            ps.setString(3,_issue_date);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Deleted Painting",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Deleted Painting",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Issue_Painting.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
} //EOF
