/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.RoleDAO;
import daos.UserDAO;
import dtos.ErrorUser;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

    private static final String ERROR = "sign_up_form.jsp";
    private static final String SUCCESS = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        try {
            UserDTO user;
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String confirm = request.getParameter("confirm");
            String roleID = "US";
            ErrorUser errorUser = new ErrorUser();

            if (id.trim().length() < 5 || id.trim().length() > 20) {
                check = false;
                errorUser.setUserIDError("ID must in [5,20]");
            }
            if (password.trim().length() < 5 || password.trim().length() > 20) {
                check = false;
                errorUser.setPasswordError("Password must in [5,20]");
            }
            if(!password.equals(confirm)){
                check = false;
                errorUser.setConfirmError("Password does not match");
            }
            if (name.trim().length() < 5 || name.trim().length() > 20) {
                check = false;
                errorUser.setNameError("Name must in [5,50]");
            }
            if (check) {
                UserDAO dao = new UserDAO();
                boolean checkDuplicateID = dao.checkDuplicate(id);
                if (checkDuplicateID) {
                    errorUser.setUserIDError("Duplicate ID");
                    request.setAttribute("ERROR", errorUser);
                } else {
                    RoleDAO roDAO = new RoleDAO();
                    user = new UserDTO(id, password, name, roDAO.getRole(roleID));
                    boolean checkCreate = dao.createUser(user);
                    if (checkCreate) {
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("ERROR", errorUser);
            }

        } catch (Exception e) {
            log("ERROR at SignUpController: " + e.toString());
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
