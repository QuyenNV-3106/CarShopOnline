/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.ProductDAO;
import dtos.CartDTO;
import dtos.ProductDTO;
import dtos.UserDTO;
import java.io.IOException;
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
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "home.jsp";
    private static final String SUCCESS = "shopView.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            UserDTO user = (UserDTO) session.getAttribute("USER_DATA");
            if (user == null) {
                request.setAttribute("ERROR", "Login to use this function");
            } else if (user.getRole().getRoleID().equals("AD")) {
                request.setAttribute("ERROR", "Your access is denied");
            } else {
                ProductDAO dao = new ProductDAO();
                String id = request.getParameter("id");
                String name = dao.getProductByID(id).getProductName();
                double price = dao.getProductByID(id).getPrice();
                int quantity = 1;
                ProductDTO pro = new ProductDTO(id, name, quantity, price);
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartDTO();
                }

                boolean check = cart.add(pro);
                if (check) {
                    session.setAttribute("CART", cart);
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Add " + name + " success");
                }
            }
        } catch (Exception e) {
            log("ERROR at AddToCartController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
