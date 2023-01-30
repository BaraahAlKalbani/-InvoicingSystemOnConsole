import java.util.ArrayList;

public class Invoice {
    private String customerName;
    private String phoneNumber;
    private String invoiceDate;
    private int numberOfItems;
    private double totalAmount;
    private double paidAmount;
    private double balance;
    private ArrayList<Item> items;
    
    public Invoice(String customerName, String phoneNumber, String invoiceDate, ArrayList<Item> items) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.invoiceDate = invoiceDate;
        this.items = items;
        calculateNumberOfItems();
        calculateTotalAmount();
    }
    
    private void calculateNumberOfItems() {
        numberOfItems = items.size();
    }
    
    private void calculateTotalAmount() {
        for (Item item : items) {
            totalAmount += item.getQtyAmount();
        }
    }
    
    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
        calculateBalance();
    }
    
    private void calculateBalance() {
        balance = totalAmount - paidAmount;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getInvoiceDate() {
        return invoiceDate;
    }
    
    public int getNumberOfItems() {
        return numberOfItems;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public double getPaidAmount() {
        return paidAmount;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
}
