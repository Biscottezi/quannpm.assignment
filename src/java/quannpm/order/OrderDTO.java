/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannpm.order;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class OrderDTO implements Serializable{
    private int orderId;
    private String customerName;
    private String customerAddress;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String customerName, String customerAddress) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    
}
