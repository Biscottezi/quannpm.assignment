/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannpm.book;

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
public class BookDAO implements Serializable{
    private List<BookDTO> bookList;

    public List<BookDTO> getBookList() {
        return bookList;
    }
    
    public void showAllBooks() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select ID, Title, Price "
                        + "From Book ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String bookId = rs.getString("ID");
                    String title = rs.getString("Title");
                    int price = rs.getInt("Price");
                    BookDTO dto = new BookDTO(bookId, title, price);
                    if(this.bookList == null){
                        this.bookList = new ArrayList<>();
                    }
                    this.bookList.add(dto);
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
    }
    
    public BookDTO getOneBook(String id) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select ID, Title, Price "
                        + "From Book "
                        + "Where ID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    String bookId = rs.getString("ID");
                    String title = rs.getString("Title");
                    int price = rs.getInt("Price");
                    BookDTO dto = new BookDTO(bookId, title, price);
                    return dto;
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
}
