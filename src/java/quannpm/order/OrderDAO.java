/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannpm.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import quannpm.utils.DBHelpers;

/**
 *
 * @author nguye
 */
public class OrderDAO implements Serializable{
    public boolean addOrder(String customerName, String customerAddress) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Insert into \"Order\" (CustomerName, CustomerAddress) "
                        + "Values (?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, customerName);
                stm.setString(2, customerAddress);
                int rowAffected = stm.executeUpdate();
                if(rowAffected > 0){
                    return true;
                }
            }
        }
        finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return true;
    }
    
    public OrderDTO getLastOrder(String customerName, String customerAddress) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select OrderID, CustomerName, CustomerAddress " 
                        + "From \"Order\" "
                        + "Where OrderID = (Select MAX(OrderID) "
                                            + "From \"Order\" "
                                            + "Where CustomerName = ? and CustomerAddress = ?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, customerName);
                stm.setString(2, customerAddress);
                
                rs = stm.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("OrderID");
                    OrderDTO dto = new OrderDTO(id, customerName, customerAddress);
                    return dto;
                }
            }
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }
}
