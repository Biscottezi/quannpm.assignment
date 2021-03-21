/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannpm.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quannpm.utils.DBHelpers;

/**
 *
 * @author nguye
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select username, password "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                
                rs = stm.executeQuery();
                
                if(rs.next()){
                    return true;
                }
            }
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    
    public String getLastName(String username) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select lastname "
                        + "From Registration "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                
                rs = stm.executeQuery();
                
                if(rs.next()){
                    return rs.getString("lastname");
                }
            }
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                rs.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;

    }
    
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    
    public void searchLastName(String searchValue) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    if(this.accountList == null){
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(dto);
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
    }
    
    public boolean deleteAccount(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Delete from Registration "
                        + "Where username like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
    
    public boolean updateAccount(String username, String password, boolean role) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
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
    
    public boolean createAccount(String username, String password, String fullname, boolean role) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Insert into "
                        + "Registration (username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?) ";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                
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
