/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrderDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO {

    public boolean add(OrderDetailDTO order) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " INSERT INTO orderDetails(orderID, productID, price, quantity) "
                        + " VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, order.getOrder().getOrderID());
                stm.setString(2, order.getProduct().getProductID());
                stm.setDouble(3, order.getPrice());
                stm.setInt(4, order.getQuantity());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
}
