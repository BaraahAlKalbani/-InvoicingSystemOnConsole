import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.Gson;

public class Invoice {
    

    private ArrayList<ShopSetting> header;
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
    
    public void newInvoice() {
        Gson gson = new Gson();
        FileReader reader = new FileReader("data/Items.json");
        HashMap<Integer, Item> items = gson.fromJson(reader, HashMap.class);
        reader.close();
        
        reader = new FileReader("data/shopSettingData.json");
        ShopSetting invoiceHeader = gson.fromJson(reader, ShopSetting.class);
        reader.close();
        
        
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String customerName = userInput.nextLine();
        System.out.print("Enter customer phone number: ");
        String phoneNumber = userInput.nextLine();

        // Get item information
        System.out.print("Enter number of items: ");
        
        Integer numberOfItems = Integer.parseInt(userInput.nextLine());
        Item[] invoiceItems = new Item[numberOfItems];
        double totalAmount = 0;
        for (int i = 0; i < numberOfItems; i++) {
            System.out.print("Enter item id: ");
            int itemId = Integer.parseInt(userInput.nextLine());
            Iterator<Item> iterator = items.iterator();
            Boolean idFound =false;
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getItemID() == id) {
                    iterator.remove();
                    idFound = true;
                    System.out.println("Item removed successfully.");
                    break;
                }
            }
        }
    }
    public ArrayList<ShopSetting> getHeader() {
        return header;
    }

    public void setHeader(ArrayList<ShopSetting> header) {
        this.header = header;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
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
