package com.ncr.snackbar.model;

import java.util.List;

public class Item {
    private long itemId;
    
    private String itemName;
    
    private long itemQuantity;
    public long getItemId() {
        return itemId;
    }
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public long getItemQuantity() {
        return itemQuantity;
    }
    public void setItemQuantity(long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

        
}
