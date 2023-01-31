import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Invoice {
    

    private ArrayList<ShopSetting> header;
    private String customerName;
    private String phoneNumber;
    private String invoiceDate;
    private int numberOfItems;
    private double totalAmount;
    private double balance;
    private ArrayList<Item> invoiceItems;
    ArrayList<Item> AllShopitems;
    ArrayList<Invoice> invoices;
    public Invoice(ArrayList<ShopSetting> header,String customerName, String phoneNumber, String invoiceDate, ArrayList<Item> items) {
        this.header = header;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceItems = items;
        calculateNumberOfItems();
        calculateTotalAmount();
    }
    
    public void addNewInvoice(Invoice newInvoice) {
        Gson gson = new Gson();
        ArrayList<Item> AllShopitems = new ArrayList<>();
        try (FileReader reader = new FileReader("data/Items.json")) {
            AllShopitems = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {
            }.getType());
            if (AllShopitems == null) {
                System.out.println("Shop Item list is empty");
                
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        try (FileReader reader = new FileReader("data/shopSettingData.json")) {
            header = gson.fromJson(reader, new TypeToken<ArrayList<ShopSetting>>() {
            }.getType());
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        try (FileReader reader = new FileReader("data/Invoices.json")) {
            invoices = gson.fromJson(reader, new TypeToken<ArrayList<ShopSetting>>() {
            }.getType());
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String customerName = userInput.nextLine();
        System.out.print("Enter customer phone number: ");
        String phoneNumber = userInput.nextLine();
        System.out.print("Enter Date: ");
        String dateString = userInput.nextLine();
        // Get item information
        System.out.print("Enter number of items: ");
        Integer numberOfItems = Integer.parseInt(userInput.nextLine());
        invoiceItems = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            System.out.print("Enter item id: ");
            int itemId = Integer.parseInt(userInput.nextLine());
            Iterator<Item> iterator = AllShopitems.iterator();
            Boolean idFound =false;
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getItemID() == itemId) {
                    invoiceItems.add(item);
                    idFound = true;
                    System.out.println("Item added successfully.");
                    break;
                }
            }
            if(!idFound)
            {
                System.out.println("Item Not Found!!.");
            }
        }
        
        newInvoice= new Invoice(header,customerName,phoneNumber,dateString,invoiceItems);
        invoices.add(newInvoice);
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
        this.invoiceItems = items;
    }
    private void calculateNumberOfItems() {
        numberOfItems = invoiceItems.size();
    }
    
    private void calculateTotalAmount() {
        for (Item item : invoiceItems) {
            totalAmount += item.getQtyAmount();
        }
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

    
    public double getBalance() {
        return balance;
    }
    
    public ArrayList<Item> getItems() {
        return invoiceItems;
    }
    
}
