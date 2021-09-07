/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UserDTO implements Serializable {

    private String userID;
    private String password;
    private String userName;
    private String email;
    private String code;
    private String address;
    private RoleDTO role;

    public UserDTO(String userID, String password, String userName, RoleDTO role) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }
    
    public UserDTO(String userName, String email, RoleDTO role, String code, String address) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.code = code;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserDTO() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

}
