/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannpm.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nguye
 */
public class CartObject implements Serializable{
    private Map<String, ItemObject> items;

    public Map<String, ItemObject> getItems() {
        return items;
    }
    
    public void addBookToCart(String id, String title, int price){
        if(this.items == null){
            this.items = new HashMap<>();
        }
        int quantity = 1;
        if(this.items.containsKey(id)){
            quantity = this.items.get(id).getQuantity() + 1;
        }
        ItemObject book = new ItemObject(title, price, quantity);
        this.items.put(id, book);
    }
    
    public void removeBookFromCart(String id){
        if(this.items == null){
            return;
        }
        if(this.items.containsKey(id)){
            this.items.remove(id);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
