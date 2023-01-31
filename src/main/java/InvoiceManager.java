import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class InvoiceManager {
    // GSON object for JSON serialization/deserialization
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final static String INVOICE_FILE = "data/invoice.json";
    static Scanner userInput = new Scanner(System.in);

    /**
     * Add an invoice to the invoice list
     * 
     * @param shopSetting The ShopSetting object
     * @param AllItems    The list of all items
     */
    public static void addInvoice(ShopSetting shopSetting, ArrayList<Item> AllItems) {

        try {
            File file = new File(INVOICE_FILE);
            // create a new file if it doesn't exist
            if (file.createNewFile()) {
            }

        } catch (Exception e) {
            // handle any exception that may occur during file creation
        }
        ArrayList<Invoice> invoices = loadInvoices(); // load existing invoices
        Invoice invoice = new Invoice();

        // Take inputs from the user for the fields

        System.out.print("Enter Customer Full Name: ");
        invoice.setCustomerName(userInput.nextLine()); // set the customer name
        System.out.print("Enter Phone Number: ");
        invoice.setPhoneNumber(userInput.nextLine()); // set the phone number
        System.out.print("Enter Invoice Date (yyyy-mm-dd): ");
        invoice.setInvoiceDate(userInput.nextLine()); // set the invoice date
        invoice.setInvoiceID(); // set the invoice ID
        invoice.setHeader(shopSetting.loadData()); // set the header information
        invoice.setItems(new ArrayList<>());
        invoice.setItems(AllItems); // set the items
        Double total = 0.0;
        for (Item item : AllItems) {
            total += item.getQtyAmount(); // calculate the total amount
        }
        invoice.setTotalAmount(total); // set the total amount
        invoices.add(invoice); // add the new invoice to the existing list
        saveInvoices(invoices); // save the updated list of invoices
    }

    /**
     * Save the list of invoices to the INVOICE_FILE
     * 
     * @param invoices The list of invoices to save
     */
    private static void saveInvoices(ArrayList<Invoice> invoices) {
        try (FileWriter writer = new FileWriter(INVOICE_FILE)) {
            if (invoices.isEmpty()) { // return if the list is empty
                return;
            }
            gson.toJson(invoices, writer); // write the list of invoices to the file
        } catch (IOException e) {
            e.printStackTrace(); // handle any exception that may occur during writing to file
        }
    }

    /**
     * Load the invoices from the INVOICE_FILE
     * 
     * @return The list of invoices
     */
    private static ArrayList<Invoice> loadInvoices() {
        File file = new File(INVOICE_FILE);

        ArrayList<Invoice> invoices = new ArrayList<>();
        try {
            if (!file.exists() || file.length() == 0) { // return an empty list if file doesn't exist or is empty
                invoices = new ArrayList<>();
            } else {
                invoices = gson.fromJson(new FileReader(INVOICE_FILE), new TypeToken<List<Invoice>>() {
                }.getType());
            }
            return invoices;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    /**
     * Displays all invoices stored in the system.
     */
    public static void showAllInvoices() {
        // Load all invoices from the system
        ArrayList<Invoice> invoices = loadInvoices();
        // Check if there are any invoices available
        if (invoices.isEmpty()) {
            System.out.println("No invoices found.");
            return;
        }
        // Print each invoice
        for (Invoice invoice : invoices) {
            printInvoice(invoice);
        }
    }

    /**
     * Generates a report of the shop's sales.
     */
    public static void Report() {
        // Total sales variable
        double totalSales = 0.0;
        // Load all invoices from the system
        ArrayList<Invoice> Allinvoices = loadInvoices();
        // Load all items from the shop
        ManageShopItems items = new ManageShopItems();
        ArrayList<Item> AllItems = items.loadData();
        // Check if there are any items available
        if (AllItems == null) {
            System.out.println("Number Of Items: " + 0);
        } else {
            System.out.println("Number Of Items: " + AllItems.size());
        }
        // Check if there are any invoices available
        if (Allinvoices == null) {
            System.out.println("Number Of Invoices: " + 0);
        } else {
            System.out.println("Number Of Invoices: " + Allinvoices.size());
            // Calculate the total sales
            for (Invoice invoice : Allinvoices) {
                totalSales += invoice.getTotalAmount();
            }
        }
        // Print the total sales
        System.out.println("Total Sales: " + totalSales);
    }

    /**
     * Searches for an invoice by its ID number.
     */
    public static void searchInvoice() {
        // Load all invoices from the system
        ArrayList<Invoice> Allinvoices = loadInvoices();
        // Check if there are any invoices available
        if (Allinvoices == null) {
            System.out.println("<<No Invoices Found !!>> \n\t Empty List");
            return;
        } else {
            try {
                // Get the ID number from the user
                System.out.print("Enter Invoices ID Number : ");
                Integer id = Integer.parseInt(userInput.nextLine());
                // Flag for whether the invoice was found
                Boolean foundBoolean = false;
                // Search for the invoice
                for (Invoice invoice : Allinvoices) {
                    if (invoice.getInvoiceID() == id) {
                        printInvoice(invoice);
                    }
                }
                // Print a message if the invoice was not found
                if (foundBoolean = false) {
                    System.out.println("--- Invoice ID Dose not Exist ---");
                }
            } catch (NumberFormatException e) {
                // Print an error message if the input is not a valid integer
                System.out.println("Invalid input. Please enter a valid integer.");
                return;
            }

        }
    }

    /**
     * Prints the invoice details to the console.
     * 
     * @param invoice The invoice to be printed.
     */
    public static void printInvoice(Invoice invoice) {
        System.out.println("\t\t<<" + invoice.getHeader().getShopName() + ">>");
        System.out.print("<<Shop Email : " + invoice.getHeader().getEmail());
        System.out.print(">> | <<Shop Tel Number : " + invoice.getHeader().getTel());
        System.out.print(">> | <<Shop Fax : " + invoice.getHeader().getFax());
        System.out.println(">> | <<Shop WebSite : " + invoice.getHeader().getWebsite() + ">>");
        System.out.println("--- ---- ---");
        System.out.println("Invoice ID: " + invoice.getInvoiceID());
        System.out.println("Invoice for Customer: " + invoice.getCustomerName());
        System.out.println("Phone Number: " + invoice.getPhoneNumber());
        System.out.println("Invoice Date: " + invoice.getInvoiceDate());
        System.out.println("Total Amount: " + invoice.getTotalAmount());
        System.out.println("--- Items ---");
        System.out.println("ItemNo\t|Item ID\t|Item Name\tUnit Price\t|Quantity\t|qtyAmount");
        int ItemNumber = 1;
        for (Item item : invoice.getItems()) {
            // Print the item details
            System.out.println(ItemNumber + "\t|" + item.getItemID() + "\t\t|" + item.getItemName() + "\t\t|"
                    + item.getUnitPrice() + "\t\t|" + item.getQuantity() + "\t\t|" + item.getQtyAmount());
            ItemNumber++;
        }
        System.out.println("--- End of Invoice ---\n\n");
    }
}
