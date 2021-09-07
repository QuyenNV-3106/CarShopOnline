/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoryDTO;
import dtos.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }
    }

    public List<ProductDTO> getListPro() {
        List<ProductDTO> list = new ArrayList<>();
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " SELECT productID, productName, detail, img, quantity, price, categoryID, sale "
                        + " FROM Products ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String proID = rs.getString("productID");
                    String name = rs.getString("productName");
                    String detail = rs.getString("detail");
                    String img = rs.getString("img");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String cateID = rs.getString("categoryID");
                    boolean sale = rs.getBoolean("sale");

                    CategoryDAO dao = new CategoryDAO();
                    CategoryDTO cate = dao.getCategory(cateID);
                    ProductDTO pro = new ProductDTO(proID, name, detail, img, quantity, price, sale, cate);
                    list.add(pro);
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }

        return list;
    }

    public ProductDTO getProductByID(String id) {
        ProductDTO pro = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " SELECT productID, productName, detail, img, quantity, price, categoryID, sale "
                        + " FROM Products "
                        + " WHERE productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String proID = rs.getString("productID");
                    String name = rs.getString("productName");
                    String detail = rs.getString("detail");
                    String img = rs.getString("img");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String cateID = rs.getString("categoryID");
                    boolean sale = rs.getBoolean("sale");

                    CategoryDAO dao = new CategoryDAO();
                    CategoryDTO cate = dao.getCategory(cateID);
                    pro = new ProductDTO(proID, name, detail, img, quantity, price, sale, cate);
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }

        return pro;
    }

    public boolean inseartPro(ProductDTO pro) {
        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " INSERT INTO Products(productID, productName, detail, img, quantity, price, categoryID, sale) "
                        + " VALUES(?,?,?,?,?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, pro.getProductID());
                stm.setString(2, pro.getProductName());
                stm.setString(3, pro.getDetail());
                stm.setString(4, pro.getImage());
                stm.setInt(5, pro.getQuantity());
                stm.setDouble(6, pro.getPrice());
                stm.setString(7, pro.getCategory().getCategoryID());
                stm.setBoolean(8, pro.isSale());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateProduct(ProductDTO pro) throws SQLException {
        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " UPDATE Products"
                        + " SET productName = ?, detail = ?, img = ?, quantity = ?, price = ?, categoryID = ?, sale = ? "
                        + " WHERE productID = ? ";
                stm = con.prepareStatement(sql);

                stm.setString(1, pro.getProductName());
                stm.setString(2, pro.getDetail());
                stm.setString(3, pro.getImage());
                stm.setInt(4, pro.getQuantity());
                stm.setDouble(5, pro.getPrice());
                stm.setString(6, pro.getCategory().getCategoryID());
                stm.setBoolean(7, pro.isSale());
                stm.setString(8, pro.getProductID());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deletePro(String id) {
        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " DELETE Products "
                        + " WHERE productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ProductDTO> searchProductByName(String name) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            for (ProductDTO p : getListPro()) {
                if (p.getProductName().contains(name)) {
                    list.add(p);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<ProductDTO> searchProductByCate(String cate) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            for (ProductDTO p : getListPro()) {
                if (p.getCategory().getCategoryID().contains(cate)) {
                    list.add(p);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }
}
