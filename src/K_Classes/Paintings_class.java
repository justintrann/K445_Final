package K_Classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Justin
 */
public class Paintings_class {
    private String codeid;
    private String name;
    private Integer author_id;
    private Integer genre_id;
    private Integer qty;
    private double price;
    private Date date_received;
    private String descr;
    private byte[] pic;
    
    //constr
    public Paintings_class(){}
    
    public Paintings_class(String _codeid, String _name, Integer _author_id, Integer _genre_id, Integer _qty, double _price,
            Date _date_received, String _descr, byte[] _pic)
    {
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

    public Date getDate_received() {
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

    public void setDate_received(Date date_received) {
        this.date_received = date_received;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
    
    //Insert Function for AddPainting Form
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
}
