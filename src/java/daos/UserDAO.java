/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

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

    public UserDTO checkLogin(String id, String password) {
        UserDTO user = null;

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " SELECT name, role "
                        + " FROM users "
                        + " WHERE userID = ? AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    String userName = rs.getString("name");
                    String role = rs.getString("role");
                    RoleDAO dao = new RoleDAO();
                    user = new UserDTO(id, "***", userName, dao.getRole(role));
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return user;
    }
    
    public boolean checkDuplicate(String id) {
        UserDTO user = null;
        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = " SELECT userID "
                        + " FROM users "
                        + " WHERE userID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean createUser(UserDTO user){
        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if(con != null){
                String sql = " INSERT INTO users(userID, password, name, role) "
                        + " VALUES(?,?,?,?) ";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getUserName());
                stm.setString(4, user.getRole().getRoleID());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        }finally{
            closeConnection();
        }
        return check;
    }
}
