import java.io.*;
import java.util.*;

class Item implements Serializable {
    private int itemID;
    private String itemName;
    private double unitPrice;
    private int quantity;
    private double qtyAmount;

    // Constructor
    public Item(int itemID, String itemName, double unitPrice, int quantity) {
        
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.qtyAmount = unitPrice * quantity;
    }
    public Item(int itemID, String itemName, double unitPrice, int quantity,Double priceByQty) {
        
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.qtyAmount = priceByQty;
    }
    // Getters and Setters

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getQtyAmount() {
        return qtyAmount;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUnitPrice(double newPrice) {
        this.unitPrice = newPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQtyAmount(int qtyAmount) {
        this.qtyAmount = qtyAmount;
    }
    public void setQtyAmount() {
        this.qtyAmount = getUnitPrice()* getQuantity();
    }
}
