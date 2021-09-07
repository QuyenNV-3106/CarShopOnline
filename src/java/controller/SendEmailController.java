/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.SendEmail;
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
@WebServlet(name = "SendEmailController", urlPatterns = {"/SendEmailController"})
public class SendEmailController extends HttpServlet {

    private static final String ERROR = "checkOut_form.jsp";
    private static final String SUCCESS = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String address = request.getParameter("address");
            String email = request.getParameter("mail");
            if (address.trim().length() == 0 || email.trim().length() == 0) {
                request.setAttribute("ERROR", "Fill all blanks");
            } else {
                HttpSession session = request.getSession();
                UserDTO user = (UserDTO) session.getAttribute("USER_DATA");
                //feth form value
                if (user != null && user.getRole().getRoleID().equals("US")) {
                    String name = user.getUserName();

                    //create instance object of the SendEmail Class
                    SendEmail sm = new SendEmail();
                    //get the 6-digit code
                    String code = sm.getRandom();

                    //craete new user using all information
                    user = new UserDTO(name, email, user.getRole(), code, address);

                    //call the send email method
                    boolean test = sm.sendEmail(user);

                    //check if the email send successfully
                    if (test) {
                        session.setAttribute("AUTHCODE", user);
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at SendEmailController: " + e.toString());
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
