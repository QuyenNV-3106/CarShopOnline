/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin
 */
public class ErrorUser {

    private String userIDError;
    private String nameError;
    private String roleError;
    private String passwordError;
    private String confirmError;

    public ErrorUser() {
        this.userIDError = "";
        this.nameError = "";
        this.roleError = "";
        this.passwordError = "";
        this.confirmError = "";
    }

    public ErrorUser(String userIDError, String nameError, String roleError, String passwordError, String confirmError) {
        this.userIDError = userIDError;
        this.nameError = nameError;
        this.roleError = roleError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

}
