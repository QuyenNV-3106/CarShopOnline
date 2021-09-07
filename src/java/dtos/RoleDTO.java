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
public class RoleDTO implements Serializable{
    private String roleID;

    public RoleDTO() {
    }

    public RoleDTO(String roleID) {
        this.roleID = roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleID() {
        return roleID;
    }
    
}
