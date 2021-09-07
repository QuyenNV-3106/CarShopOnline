/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class OrderDTO {
    private String orderID;
    private UserDTO user;
    private Date time;
    private String address;
    private String email;

    public OrderDTO() {
    }

    public OrderDTO(String orderID) {
        this.orderID = orderID;
    }

    public OrderDTO(String orderID, UserDTO user, Date time, String address, String email) {
        this.orderID = orderID;
        this.user = user;
        this.time = time;
        this.address = address;
        this.email = email;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
}
