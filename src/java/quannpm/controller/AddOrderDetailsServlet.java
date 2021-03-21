package quannpm.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quannpm.cart.CartObject;
import quannpm.cart.ItemObject;
import quannpm.order.OrderDTO;
import quannpm.orderDetails.OrderDetailsDAO;

/**
 *
 * @author nguye
 */
public class AddOrderDetailsServlet extends HttpServlet {
    private final String VIEW_CART_PAGE = "viewCart";
    private final String BOOKSTORE = "bookStore";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = VIEW_CART_PAGE;
        OrderDTO dto = (OrderDTO) request.getAttribute("LASTEST_ORDER");
        try{
            HttpSession session = request.getSession(false);
            if(session !=  null){
                CartObject cart = (CartObject) session.getAttribute("CART");
                if(cart != null){
                    Map<String, ItemObject> items = cart.getItems();
                    OrderDetailsDAO dao = new OrderDetailsDAO();
                    boolean result = false;
                    for(String bookId : items.keySet()){
                        result = dao.addOrderDetails(dto.getOrderId(), bookId, items.get(bookId).getQuantity());
                    }
                    if(result){
                        url = BOOKSTORE;
                        session.invalidate();
                    }
                }
            }
        }
        catch(SQLException ex){
            log("AddOrderDetailsServlet_SQL: " + ex.getMessage());
        }
        catch(NamingException ex){
            log("AddOrderDetailsServlet_Naming: " + ex.getMessage());
        }
        finally{
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
