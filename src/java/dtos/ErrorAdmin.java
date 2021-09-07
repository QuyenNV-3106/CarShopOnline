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
public class ErrorAdmin {

    private String errorID;
    private String errorName;
    private String errorDetail;
    private String errorImage;
    private String errorQuantity;
    private String errorPrice;
    private String errorSale;
    private String errorCateID;

    public ErrorAdmin() {
        this.errorID = "";
        this.errorName = "";
        this.errorDetail = "";
        this.errorImage = "";
        this.errorQuantity = "";
        this.errorPrice = "";
        this.errorSale = "";
        this.errorCateID = "";
    }

    public ErrorAdmin(String errorID, String errorName, String errorDetail, String errorImage, String errorQuantity, String errorPrice, String errorSale, String errorCateID) {
        this.errorID = errorID;
        this.errorName = errorName;
        this.errorDetail = errorDetail;
        this.errorImage = errorImage;
        this.errorQuantity = errorQuantity;
        this.errorPrice = errorPrice;
        this.errorSale = errorSale;
        this.errorCateID = errorCateID;
    }

    public String getErrorID() {
        return errorID;
    }

    public void setErrorID(String errorID) {
        this.errorID = errorID;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrorImage() {
        return errorImage;
    }

    public void setErrorImage(String errorImage) {
        this.errorImage = errorImage;
    }

    public String getErrorQuantity() {
        return errorQuantity;
    }

    public void setErrorQuantity(String errorQuantity) {
        this.errorQuantity = errorQuantity;
    }

    public String getErrorPrice() {
        return errorPrice;
    }

    public void setErrorPrice(String errorPrice) {
        this.errorPrice = errorPrice;
    }

    public String getErrorSale() {
        return errorSale;
    }

    public void setErrorSale(String errorSale) {
        this.errorSale = errorSale;
    }

    public String getErrorCateID() {
        return errorCateID;
    }

    public void setErrorCateID(String errorCateID) {
        this.errorCateID = errorCateID;
    }

}
