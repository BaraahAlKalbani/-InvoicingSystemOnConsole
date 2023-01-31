import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String customerName;
    private String phoneNumber;
    private String invoiceDate;
    private int numberOfItems;
    private double totalAmount;
    private double paidAmount;
    private double balance;
    private ArrayList<Item> items = new ArrayList<>();

    public Invoice(String customerName, String phoneNumber, String invoiceDate, int numberOfItems, double totalAmount, double paidAmount, double balance, ArrayList<Item> items) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.invoiceDate = invoiceDate;
        this.numberOfItems = numberOfItems;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.balance = balance;
        this.items = items;
    }
    public Invoice() {
        // TODO Auto-generated constructor stub
    }
    public void addInvoice(ArrayList<Item> shopItems ) {
        
        // Take inputs from the user for the fields
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Customer Full Name: ");
        String customerName = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter Invoice Date (yyyy-mm-dd): ");
        String invoiceDate = sc.nextLine();
        ManageShopItems manageShopItems = new ManageShopItems();

        // Add items to the invoice
        ArrayList<Item> invoiceItems = new ArrayList<>();
        while (true) {
            System.out.print("Enter Item ID (Enter 0 to finish): ");
            Integer itemId = Integer.parseInt(sc.nextLine());
            if (itemId == 0) {
                break;
            }
            
            
            boolean itemFound = false;
            for (Item item : shopItems) {
                if (item.getItemID() == itemId) {
                    System.out.print("Enter Quantity: ");
                    Integer quantity = Integer.parseInt(sc.nextLine());
                    item.setQtyAmount();
                    items.add(item);
                    itemFound = true;
                    System.out.println("Item added to the Invoice successfully.");
                    break;
                }
            }

            if (!itemFound) {
                System.out.println("Item not found.");
                
            }
            

        // Set the fields of the invoice
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.invoiceDate = invoiceDate;
        this.items = items;
        // Calculate the total amount and balance
        double totalAmount = 0;
        for (Item item : items) {
            totalAmount += item.getUnitPrice() * item.getQuantity();
        }
        this.totalAmount = totalAmount;
        this.balance = totalAmount;
        ArrayList<Invoice> invoiceArrayList = new ArrayList<>();
        invoiceArrayList.add(this);
        // Serialize the invoice and save it to a .json file
        String jsonString = gson.toJson(invoiceArrayList);
        try {
            FileWriter writer = new FileWriter("data/invoices.json");
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            System.out.print(e);
        }}
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void saveInvoiceToJSON(String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateStatistics() {
        // TODO Auto-generated method stub
        
    }
}
