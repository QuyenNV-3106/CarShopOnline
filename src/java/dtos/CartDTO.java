/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartDTO {

    private Map<String, ProductDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean add(ProductDTO pro) {
        boolean check = false;
        try {
            if (cart == null) {
                cart = new HashMap<>();
            }
            if (cart.containsKey(pro.getProductID())) {
                int quantity = cart.get(pro.getProductID()).getQuantity();
                pro.setQuantity(quantity + pro.getQuantity());
            }
            cart.put(pro.getProductID(), pro);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    public void delete(String id) {
        try {
            if (cart != null) {
                if (cart.containsKey(id)) {
                    cart.remove(id);
                }
            }
        } catch (Exception e) {
        }
    }
}
