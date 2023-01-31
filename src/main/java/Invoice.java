import java.awt.Window.Type;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Invoice {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

    public Invoice(ArrayList<ShopSetting> header, String customerName, String phoneNumber, String invoiceDate,
            ArrayList<Item> items) {
        this.header = header;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceItems = items;
        calculateNumberOfItems();
        calculateTotalAmount();
    }

    public Invoice() {
        // TODO Auto-generated constructor stub
    }

    public void addNewInvoice(Invoice newInvoice) {
        final String shopSetting_File = "data/Invoices.json";
        try {
            File settingsFile = new File(shopSetting_File);
            if (settingsFile.createNewFile()) {
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
            Boolean idFound = false;
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getItemID() == itemId) {
                    invoiceItems.add(item);
                    idFound = true;
                    System.out.println("Item added successfully.");
                    break;
                }
            }
            if (!idFound) {
                System.out.println("Item Not Found!!.");
            }
        }

        newInvoice = new Invoice(header, customerName, phoneNumber, dateString, invoiceItems);
        invoices.add(newInvoice);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/Invoices.json"))) {
            gson.toJson(invoices, bw);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }
    }

    public void generateStatistics() {
        java.lang.reflect.Type type = new TypeToken<ArrayList<Invoice>>() {
        }.getType();
        ArrayList<Invoice> invoices = new ArrayList<>();
        final String shopSetting_File = "data/Invoices.json";
        try {
            File settingsFile = new File(shopSetting_File);
            if (settingsFile.createNewFile()) {
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            invoices = gson.fromJson(new FileReader("data/Invoices.json"), type);
        } catch (JsonIOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int numberOfInvoices = invoices.size();
        int numberOfItems = 0;
        double totalSales = 0;
        for (Invoice invoice : invoices) {
            numberOfItems += invoice.getItems().size();
            totalSales += invoice.getTotalAmount();
        }

        System.out.println("Number of invoices: " + numberOfInvoices);
        System.out.println("Number of items: " + numberOfItems);
        System.out.println("Total sales: " + totalSales);
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
