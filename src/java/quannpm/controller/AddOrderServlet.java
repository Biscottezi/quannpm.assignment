/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quannpm.order.OrderDAO;
import quannpm.order.OrderDTO;

/**
 *
 * @author nguye
 */
public class AddOrderServlet extends HttpServlet {
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String ADD_ORDER_DETAILS_CONTROLLER = "AddOrderDetailsServlet";

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
        String customerName = request.getParameter("txtCustomerName");
        String customerAddress = request.getParameter("txtCustomerAddress");
        String url = VIEW_CART_PAGE;
        try{
            OrderDAO dao = new OrderDAO();
            boolean result = dao.addOrder(customerName, customerAddress);
            if(result){
                url = ADD_ORDER_DETAILS_CONTROLLER;
                OrderDTO dto = dao.getLastOrder(customerName, customerAddress);
                request.setAttribute("LASTEST_ORDER", dto);
            }
        }
        catch(SQLException ex){
            log("AddOrderServlet_SQL: " + ex.getMessage());
        }
        catch(NamingException ex){
            log("AddOrderServlet_Naming: " + ex.getMessage());
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
