/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannpm.orderDetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import quannpm.utils.DBHelpers;

/**
 *
 * @author nguye
 */
public class OrderDetailsDAO implements Serializable{
    public boolean addOrderDetails(int orderId, String bookId, int quantity) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Insert into OrderDetails (OrderID, BookID, Quantity) "
                        + "Values (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                stm.setString(2, bookId);
                stm.setInt(3, quantity);
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
        return false;
    }
}
