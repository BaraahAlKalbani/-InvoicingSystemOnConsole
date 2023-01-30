import java.io.Serializable;

public class Item implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int itemID;
    private String itemName;
    private double unitPrice;
    private int quantity;

    public Item(int itemID, String itemName, double unitPrice, int quantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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

    public double getAmount() {
        return this.unitPrice * this.quantity;
    }
}
