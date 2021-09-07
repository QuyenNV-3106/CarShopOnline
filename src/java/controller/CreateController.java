/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CategoryDAO;
import daos.ProductDAO;
import dtos.ErrorAdmin;
import dtos.ProductDTO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
@MultipartConfig
public class CreateController extends HttpServlet {

    private static final String ERROR = "productFormCreate.jsp";
    private static final String SUCCESS = "adminView.jsp";
    private static final String ADMIN = "adminView.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        ProductDAO dao = new ProductDAO();
        CategoryDAO cateDAO = new CategoryDAO();
        try {
            String proID = request.getParameter("id");
            String name = request.getParameter("name");
            String detail = request.getParameter("detail");
            String image = uploadFile(request);

            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String sale = request.getParameter("sale");
            String cateID = request.getParameter("cateID");
            boolean check = true;
            ErrorAdmin errorAD = new ErrorAdmin();

            if (!proID.matches("[A-Z]{2}[0-9]{3,10}")) {
                errorAD.setErrorID("Id's length [5-10], format: 2 letter [A-Z], at least 3-10 number [0-9]");
                check = false;
            }
            if (name.trim().length() < 5 || name.trim().length() > 150) {
                errorAD.setErrorName("Name must in [5, 150]");
                check = false;
            }
            if (detail.trim().length() < 1) {
                errorAD.setErrorDetail("Description can not be empty");
                check = false;
            }
            if (image.trim().length() < 1) {
                errorAD.setErrorImage("Image can not be empty");
                check = false;
            }

            if (quantity < 0) {
                errorAD.setErrorQuantity("Quantity higher or equals 0");
                check = false;
            }
            if (price < 0) {
                errorAD.setErrorPrice("Price higher or equals 0");
                check = false;
            }
            if (!sale.equals("true")) {
                if (!sale.equals("false")) {
                    errorAD.setErrorSale("Please chooose one.");
                    check = false;
                }
            }
            if (image.equals("Accecpt jpg/png file")) {
                errorAD.setErrorImage("Accecpt jpg/png file");
                check = false;
            }
            if (cateDAO.getCategory(cateID) == null) {
                errorAD.setErrorCateID("Please choose one.");
                check = false;
            }
            if (check) {
                ProductDTO pro = dao.getProductByID(proID);

                if (pro == null) {
                    pro = new ProductDTO(proID, name, detail, image, quantity, price,
                            sale.equals("true"), cateDAO.getCategory(cateID));

                    if (dao.inseartPro(pro)) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Insert Fail");
                        url = ADMIN;
                    }
                } else {
                    errorAD.setErrorID("Duplicate productID");
                    request.setAttribute("ERROR_ADMIN", errorAD);
                }
            } else {
                request.setAttribute("ERROR_ADMIN", errorAD);
            }
        } catch (Exception e) {
            log("ERROR at CreateController: " + e.toString());
        } finally {
            session.setAttribute("PRODUCT", dao.getListPro());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";

        try {
            Part part = request.getPart("photo");
            String realPath = request.getServletContext().getRealPath("/images");
            fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String tmp[] = fileName.split("\\.");
            int numTemp = tmp.length - 1;
            if (!tmp[numTemp].equals("png")) {
                if (!tmp[numTemp].equals("jpg")) {
                    fileName = "Accecpt jpg/png file";
                }
            } else {
                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectory(Paths.get(realPath));
                }
                part.write(realPath + "/" + fileName);
            }
        } catch (IOException | ServletException e) {
        }
        return fileName;
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
