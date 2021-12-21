package K_Classes;

import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Justin
 */
public class Paintings_class {
    private Integer id;
    private String codeid;
    private String name;
    private Integer author_id;
    private Integer genre_id;
    private Integer qty;
    private double price;
    private String date_received;
    private String descr;
    private byte[] pic;
    
    //constr
    public Paintings_class(){}
    
    public Paintings_class(Integer _id,String _codeid, String _name, Integer _author_id, Integer _genre_id, Integer _qty, double _price,
            String _date_received, String _descr, byte[] _pic)
    {
        this.id = _id;
        this.codeid = _codeid;
        this.name = _name;
        this.author_id = _author_id;
        this.genre_id = _genre_id;
        this.qty = _qty;
        this.price = _price;
        this.date_received = _date_received;
        this.descr = _descr;
        this.pic = _pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
    //GET
    public String getcodeid() {
        return codeid;
    }

    public String getName() {
        return name;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public Integer getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public String getDate_received() {
        return date_received;
    }

    public String getDescr() {
        return descr;
    }

    public byte[] getPic() {
        return pic;
    }
    
    //SET

    public void setcodeid(String codeid) {
        this.codeid = codeid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate_received(String date_received) {
        this.date_received = date_received;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
    
    //Insert Function for AddPainting Form
    public void addPainting(String _codeid, String _name, Integer _author_id, Integer _genre_id, Integer _qty, double _price,
            String _date_received, String _descr, byte[] _pic)
    {
        String insertQuery = "INSERT INTO painting (codeid, name, author_id, genre_id, qty, price, date_received, descr, pic) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            
            ps.setString(1, _codeid);
            ps.setString(2, _name);
            ps.setInt(3, _author_id);
            ps.setInt(4, _genre_id);
            ps.setInt(5, _qty);
            ps.setDouble(6, _price);
            ps.setString(7, _date_received);
            ps.setString(8, _descr);
            ps.setBytes(9, _pic);
            
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","New Painting",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","New Painting",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Paintings_class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Edit Function for AddPainting Form
    public void editPainting(int _id, String _name, Integer _author_id, Integer _genre_id, Integer _qty, double _price,
            String _date_received, String _descr, byte[] _pic)
    {
        String updateQuery;
        PreparedStatement ps;
        
        try{
        
        if(_pic !=null)
        {
            updateQuery = "UPDATE `painting` SET `name`=?,`author_id`=?,`genre_id`=?,`qty`=?,`price`=?,`date_received`=?,`descr`=?,`pic`=? WHERE id = ?";
            ps = DB.getConnection().prepareStatement(updateQuery);
            
            //Must match index with query above
            ps.setString(1, _name);
            ps.setInt(2, _author_id);
            ps.setInt(3, _genre_id);
            ps.setInt(4, _qty);
            ps.setDouble(5, _price);
            ps.setString(6, _date_received);
            ps.setString(7, _descr);
            ps.setBytes(8, _pic);
            ps.setInt(9, _id);
            
        }
        else
        {
            updateQuery = "UPDATE `painting` SET `name`=?,`author_id`=?,`genre_id`=?,`qty`=?,`price`=?,`date_received`=?,`descr`=? WHERE id = ?";
            
            ps = DB.getConnection().prepareStatement(updateQuery);
            
            //Must match index with query above
            ps.setString(1, _name);
            ps.setInt(2, _author_id);
            ps.setInt(3, _genre_id);
            ps.setInt(4, _qty);
            ps.setDouble(5, _price);
            ps.setString(6, _date_received);
            ps.setString(7, _descr);
            //ps.setBytes(8, _pic);
            ps.setInt(8, _id);
            
        }
           
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Edit Painting",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Edit Painting",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Paintings_class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public boolean uniqueCodeID(String _codeid)
    {
        String query = "SELECT * FROM painting WHERE codeid = '" + _codeid + "'";
        Functions func = new Functions();
        ResultSet rs = func.getData(query);
        try {
            if(rs.next())
            {
                return true;
            }
            else
                return false;
            
                } catch (SQLException ex) {
            Logger.getLogger(Paintings_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
     public Paintings_class searchPaintingbyID(int _id, String _codeID)
     {
        String query="SELECT * FROM painting WHERE id = '" + _id + "' OR codeid = '" + _codeID + "'";
        
        Functions func = new Functions();
        ResultSet rs = func.getData(query);
        
        Paintings_class paints = null;
        
        try {
            if(rs.next())
            {
                paints = new Paintings_class(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), 
                        rs.getInt(6), rs.getDouble(7),
                        rs.getString(8), rs.getString(9), rs.getBytes(10));
            }
            else
            {
                return paints;
            }
                } catch (SQLException ex) {
            Logger.getLogger(Paintings_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paints;
     }
     
     //DELETE---
        //Delete
    public void deletePainting(int _id)
    {
        String deleteQuery = "DELETE FROM `painting` WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(deleteQuery);
            
            //Because SET `SQL is first , so we must set index below of name firstly
            ps.setInt(1,_id);
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully","Deleted Painting",1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed","Deleted Painting",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Paintings_class.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    //For jTable arrayList. GET from DB to jTable . AND for SEARCHING in ListPainting
    public static ArrayList<Paintings_class> paintingList(String query)
    {
       ArrayList<Paintings_class> pList = new ArrayList<>();
       
       K_Classes.Functions func = new Functions();
        
        try {
            
            if (query.equals("")) //If empty , still show all
            {
                query = "SELECT * FROM painting";
            }
            
            ResultSet rs = func.getData(query);
            
            Paintings_class painting;
            
            while (rs.next())
            {
                painting = new Paintings_class(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), 
                        rs.getInt(6), rs.getDouble(7),
                        rs.getString(8), rs.getString(9), rs.getBytes(10));
                
                pList.add(painting);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Paintings_class.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      return pList;
    }
     
     // get customer by id
    public Paintings_class getPaintbyId(Integer _id) throws SQLException
    {
        Functions func = new Functions();
        String query = "SELECT * FROM `painting` WHERE `id`="+_id;
        ResultSet rs = func.getData(query);
        if(rs.next())
        {
            return new Paintings_class(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), 
                        rs.getInt(6), rs.getDouble(7),
                        rs.getString(8), rs.getString(9), rs.getBytes(10));
                    }
        else{
            return null;
        }
    }
    
    //For five lastest paintings
    //array of jlabel like parameter
    public void displayPaintingPicture(JLabel[] labels_tab)
    {
        ResultSet rs;
        Statement st;
        Functions func = new Functions();
        
        try {
            st = (Statement) DB.getConnection().createStatement();
            rs = st.executeQuery("SELECT pic FROM painting LIMIT 5");
            byte[] image;
            int i=0;
            while(rs.next()) //Go all data
            {
                image =rs.getBytes("pic");
                func.displayImage(labels_tab[i].getWidth(), labels_tab[i].getHeight(), image, name, labels_tab[i]);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Paintings_class.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
} // E.O.F
