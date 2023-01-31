import java.util.Random;

public class Item {
    private int itemID;
    private String itemName;
    private double unitPrice;
    private int quantity;
    private double qtyAmount;

    /**
     * Constructor with parameters for item class
     * 
     * @param itemID    identifier of the item
     * @param itemName  name of the item
     * @param unitPrice price of the item
     * @param quantity  quantity of the Item
     */
    public Item(String itemName, double unitPrice, int quantity) {
        Random random = new Random();
        this.itemID = (99999 - 10000 + 1) + 10000;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.qtyAmount = unitPrice * quantity;
    }
    
    // Setters and getters
    public int getItemID() {
        return itemID;
    }
    public void setItemID() {
        this.itemID = (99999 - 10000 + 1) + 10000;
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQtyAmount(double unitPrice, int quantity) {
        this.qtyAmount=unitPrice * quantity;
        
    }
    public double getQtyAmount() {
        return this.qtyAmount;
        
    }
}
