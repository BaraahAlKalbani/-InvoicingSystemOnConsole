import java.io.*;

class Item implements Serializable {
    private int itemID;
    private String itemName;
    private int unitPrice;
    private int quantity;
    private int qtyAmount;

    public Item(int itemID, String itemName, int unitPrice, int quantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.qtyAmount = unitPrice * quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQtyAmount() {
        return qtyAmount;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQtyAmount(int qtyAmount) {
        this.qtyAmount = qtyAmount;
    }
}
