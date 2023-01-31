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

    private ShopSetting header;
    private String customerName;
    private String phoneNumber;
    private String invoiceDate;
    private int numberOfItems;
    private double totalAmount;
    private double balance;
    private ArrayList<Item> invoiceItems;
    ArrayList<Item> AllShopitems;
    ArrayList<Invoice> invoices;

    public Invoice(ShopSetting header, String customerName, String phoneNumber, String invoiceDate,
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
        public void addNewInvoice(Invoice newInvoice) {
            String invoicesFile = "data/Invoices.json";
            String shopItemsFile = "data/Items.json";
            String shopSettingDataFile = "data/shopSettingData.json";

            Gson gson = new Gson();
            ArrayList<Invoice> invoices = new ArrayList<>();
            ArrayList<Item> invoiceItems = new ArrayList<>();
            ShopSetting header = null;

            try (FileReader reader = new FileReader(shopItemsFile)) {
                ArrayList<Item> allShopItems = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {}.getType());
                if (allShopItems == null || allShopItems.isEmpty()) {
                    System.out.println("Error reading file: Shop item list is empty");
                    return;
                }

                Scanner userInput = new Scanner(System.in);
                System.out.print("Enter customer name: ");
                String customerName = userInput.nextLine();
                System.out.print("Enter customer phone number: ");
                String phoneNumber = userInput.nextLine();
                System.out.print("Enter date: ");
                String dateString = userInput.nextLine();
                System.out.print("Enter number of items: ");
                int numberOfItems = Integer.parseInt(userInput.nextLine());

                for (int i = 0; i < numberOfItems; i++) {
                    System.out.print("Enter item id: ");
                    int itemId = Integer.parseInt(userInput.nextLine());
                    Item item = allShopItems.stream().filter(it -> it.getItemID() == itemId).findFirst().orElse(null);
                    if (item != null) {
                        invoiceItems.add(item);
                        System.out.println("Item added successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                }

                try (FileReader reader2 = new FileReader(shopSettingDataFile)) {
                    header = gson.fromJson(reader2, ShopSetting.class);
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                    return;
                }

                newInvoice = new Invoice(header, customerName, phoneNumber, dateString, invoiceItems);

                try (FileReader reader3 = new FileReader(invoicesFile)) {
                    invoices = gson.fromJson(reader3, new TypeToken<ArrayList<Invoice>>() {}.getType());
                } catch (FileNotFoundException e) {
                    System.out.println("File not found: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                    return;
                }
                if(invoices == null)
                    invoices = new ArrayList<>();
                invoices.add(newInvoice);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(invoicesFile))) {
                    gson.toJson(invoices, bw);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
        if (invoices != null) {
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
        } else {
            System.out.println("Number of invoices: " + 0);
            System.out.println("Number of items: " + 0);
            System.out.println("Total sales: " + 0);
        }

    }

    public ShopSetting getHeader() {
        return header;
    }

    public void setHeader(ShopSetting header) {
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
