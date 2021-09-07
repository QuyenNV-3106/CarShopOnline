/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CategoryDAO;
import daos.ProductDAO;
import dtos.CartDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class MainController extends HttpServlet {

    private static final String ERROR = "errorPage.jsp";
    private static final String SIGN_UP_PAGE = "sign_up_form.jsp";
    private static final String CREATE_PAGE = "productFormCreate.jsp";
    private static final String HOME = "home.jsp";
    private static final String ADMIN_VIEW = "adminView.jsp";
    private static final String SHOP_PAGE = "shopView.jsp";
    private static final String CART_PAGE = "cartView.jsp";
    private static final String CHECK_OUT = "checkOut_form.jsp";
    private static final String LOGIN = "LoginController";
    private static final String CREATE_USER = "SignUpController";
    private static final String INSERT = "CreateController";
    private static final String LOGOUT = "LogoutController";
    private static final String UPDATE = "UpdateController";
    private static final String DELETE = "DeleteController";
    private static final String SEARCH = "SearchController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String DELETE_CART = "DeleteCartController";
    private static final String VERIFY_CODE = "SendEmailController";
    private static final String VERIFY = "VerifyController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductDAO proDAO = new ProductDAO();
        CategoryDAO cateDAO = new CategoryDAO();

        HttpSession session = request.getSession();
        CartDTO cart = (CartDTO) session.getAttribute("CART");
        try {
            session.setAttribute("BRAND", cateDAO.getListCategory());
            session.setAttribute("PRODUCT", proDAO.getListPro());
            if (cart != null) {
                session.setAttribute("CART", cart);
            }
            String action = request.getParameter("btnAction");

            if (action == null) {
                url = HOME;
            }
            if ("Signup".equals(action)) {
                url = SIGN_UP_PAGE;
            }
            if ("CreateUser".equals(action)) {
                url = CREATE_USER;
            }
            if ("Login".equals(action)) {
                url = LOGIN;
            }
            if ("Logout".equals(action)) {
                url = LOGOUT;
            }
            if ("Insert".equals(action)) {
                url = INSERT;
            }
            if ("InsertPage".equals(action)) {
                url = CREATE_PAGE;
            }
            if ("ViewAD".equals(action)) {
                url = ADMIN_VIEW;
            }
            if ("Update".equals(action)) {
                url = UPDATE;
            }
            if ("Delete".equals(action)) {
                url = DELETE;
            }
            if ("ViewShop".equals(action)) {
                url = SHOP_PAGE;
            }
            if ("ViewCart".equals(action)) {
                url = CART_PAGE;
            }
            if ("Search".equals(action)) {
                url = SEARCH;
            }
            if ("SearchBrand".equals(action)) {
                url = SEARCH;
            }
            if ("SearchAD".equals(action)) {
                url = SEARCH;
            }
            if ("AddToCart".equals(action)) {
                url = ADD_TO_CART;
            }
            if ("DeleteItems".equals(action)) {
                url = DELETE_CART;
            }
            if ("Checkout".equals(action)) {
                url = CHECK_OUT;
            }
            if ("Received Verify code".equals(action)) {
                url = VERIFY_CODE;
            }
            if ("Verify".equals(action)) {
                url = VERIFY;
            }

        } catch (Exception e) {
            log("ERROR at MainController: " + e.toString());
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
