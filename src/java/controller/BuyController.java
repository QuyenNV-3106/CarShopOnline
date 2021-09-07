/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.OrderDAO;
import daos.OrderDetailDAO;
import dtos.CartDTO;
import dtos.OrderDTO;
import dtos.OrderDetailDTO;
import dtos.ProductDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.sql.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BuyController", urlPatterns = {"/BuyController"})
public class BuyController extends HttpServlet {

    private static final String ERROR = "checkOut_form.jsp";
    private static final String SUCCESS = "cartView.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER_DATA");
            UserDTO userAuth = (UserDTO) session.getAttribute("AUTHCODE");
            CartDTO cart;
            if (user != null && user.getRole().getRoleID().equals("US")) {
                String email = userAuth.getEmail();
                String address = userAuth.getAddress();
                
                Date time = new Date(System.currentTimeMillis());

                OrderDAO dao = new OrderDAO();
                String orderID = getRandom();
                while (dao.getOrder(user.getUserID()).equals(orderID)) {
                    orderID = getRandom();
                }
                OrderDTO order = new OrderDTO(orderID, userAuth, time, address, email);
                if (dao.add(order)) {
                    OrderDetailDTO orderDetail;
                    OrderDetailDAO detail = new OrderDetailDAO();
                    cart = (CartDTO) session.getAttribute("CART");
                    for (ProductDTO pro : cart.getCart().values()) {
                        orderDetail = new OrderDetailDTO(order, pro, pro.getPrice(), pro.getQuantity());
                        detail.add(orderDetail);
                    }
                    request.setAttribute("MSG", "Thanks you so much");
                    session.removeAttribute("CART");
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("ERROR at BuyController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
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
