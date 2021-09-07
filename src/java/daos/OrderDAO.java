/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    public boolean add(OrderDTO order) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " INSERT INTO orders(orderID, userID, orderDate, address, userName, email) "
                        + " VALUES(?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, order.getOrderID());
                stm.setString(2, order.getUser().getUserID());
                stm.setDate(3, order.getTime());
                stm.setString(4, order.getAddress());
                stm.setString(5, order.getUser().getUserName());
                stm.setString(6, order.getUser().getEmail());

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
    public String getOrder(String id) throws SQLException {
        String orderID = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " SELECT orderID "
                        + " FROM orders "
                        + " WHERE userID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    orderID = rs.getString("orderID");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderID;
    }
}
