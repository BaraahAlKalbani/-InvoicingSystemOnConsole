/**
 * @author Bara'ah Nasser
 */
import java.util.*;



public class Invoice {

    private int invoiceID;
    private String customerName;
    private String phoneNumber;
    private String invoiceDate;
    private ArrayList<Item> items;
    private ShopSetting header;
    private double totalAmount;

    /**
     * Constructor for creating a new Invoice object.
     *
     * @param header       The header information for the shop or business.
     * @param customerName Name of the customer who made the purchase.
     * @param phoneNumber  Phone number of the customer.
     * @param invoiceDate  Date when the purchase was made.
     * @param items        List of items purchased.
     * @param totalAmount  Total amount of the purchase.
     */
    public Invoice(ShopSetting header, String customerName, String phoneNumber, String invoiceDate,
            ArrayList<Item> items, double totalAmount) {
        this.header = header;
        setInvoiceID();
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.invoiceDate = invoiceDate;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Invoice() {
        // TODO Auto-generated constructor stub
        // Empty constructor

    }
    
    //setters and getters
    public int getInvoiceID() {
        return invoiceID;
    }

    // Set the invoice ID randomly
    public void setInvoiceID() {
        Random random = new Random();
        this.invoiceID = random.nextInt(99999);
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ShopSetting getHeader() {
        return header;
    }

    public void setHeader(ShopSetting header) {
        this.header = header;
    }

}
