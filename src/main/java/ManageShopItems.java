import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

class ManageShopItems {
    private ArrayList<Item> items = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String itemsListFile = "data/Items.json";

    /**
     * Constructor to initialize ManageShopItems object. Loads the data of items
     * from file.
     */
    public ManageShopItems() {
        loadData();
    }

    /**
     * Loads the data of items from file.
     * 
     * @return ArrayList<Item> The list of items.
     */
    public ArrayList<Item> loadData() {
        try (FileReader reader = new FileReader(itemsListFile)) {
            items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {
            }.getType());
            // If there is no data in the file, return an empty list
            if (items == null)
                return items = new ArrayList<>();
            return items;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return items;
    }

    /**
     * Get the list of items.
     * 
     * @return ArrayList<Item> The list of items.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Add a new item to the list of items.
     */
    public void addItem() {
        String name;
        double unitPrice;
        int quantity;
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Item name: ");
            name = userInput.next();
            System.out.print("Unit price: ");
            unitPrice = Double.parseDouble(userInput.nextLine());
            System.out.print("Quantity: ");
            quantity = userInput.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return;
        }
        // Create a new item and add it to the list
        Item newItem = new Item(name, unitPrice, quantity);
        items.add(newItem);
        saveData();
        System.out.println("Item added successfully.");
    }

    /**
     * Method to delete an item from the item list by ID
     * 
     * @param id the ID of the item to be deleted
     */
    public void deleteItemById(int id) {
        // Create an iterator for the item list
        Iterator<Item> iterator = items.iterator();
        // Loop through the item list
        while (iterator.hasNext()) {
            Item item = iterator.next();
            // Check if the item ID matches the input ID
            if (item.getItemID() == id) {
                // Remove the item from the list
                iterator.remove();
                // Print success message
                System.out.println("Item removed successfully.");
                break;
            }
        }
        // Save the updated item list to the file
        saveData();
    }

    /**
     * Method to save the updated item list to the file
     */
    private void saveData() {
        // Try to write the item list to the file using BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemsListFile))) {
            // Use Gson to convert the item list to a json string
            gson.toJson(items, bw);
        } catch (IOException e) {
            // Print error message if the writing failed
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Method to change the price of an item in the item list by ID
     * 
     * @param id       the ID of the item to be changed
     * @param newPrice the new price for the item
     */
    public void changeItemPrice(int id, double newPrice) {
        // File path for the items list
        final String itemsListFile = "data/Items.json";
        // Create a File object for the items list
        File itemsFile = new File(itemsListFile);

        // Try to read the item list from the file using FileReader
        try (FileReader reader = new FileReader(itemsListFile)) {
            // Use Gson to convert the json string to the item list
            items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {
            }.getType());
            // Check if the item list is empty
            if (items == null) {
                System.out.println("Item list is empty");
                return;
            }
        } catch (IOException e) {
            // Print error message if the reading failed
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Flag to keep track if the item was found
        boolean itemFound = false;
        // Loop through the item list
        for (Item item : items) {
            // Check if the item ID matches the input ID
            if (item.getItemID() == id) {
                // Set the new price for the item
                item.setUnitPrice(newPrice);
                // Update the total amount for the item
                item.setQtyAmount(item.getUnitPrice(), item.getQuantity());
                // Set the flag to true
                itemFound = true;
                // Print success message
                System.out.println("Item price changed successfully.");
                break;
            }
        }
        // Check if the item was not found
        if (!itemFound) {
            System.out.println("Item not found.");
            return;
        }
        // save the Updated item list into the .json File
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemsListFile))) {
            gson.toJson(items, bw);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }
    }

    /**
     * Display a list of all items stored in a file
     * 
     * @param itemsListFile the file path of the items list
     */
    public void showAllItems() {

        // Create a File object using the file path
        File itemsFile = new File(itemsListFile);

        try (FileReader reader = new FileReader(itemsListFile)) {
            // Convert the contents of the file into a list of items using Gson library
            items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {
            }.getType());

            // If the item list is empty, print a message and return
            if (items == null) {
                System.out.println("Item list is empty");
                return;
            }
        } catch (IOException e) {
            // If there is an error reading the file, print an error message and return
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Print the header for the items table
        System.out.println("ID\t|Name\t\t|Unit Price\t|Quantity\t|qtyAmount");

        // Loop through each item in the list and print its details
        for (Item item : items) {
            System.out.println(item.getItemID() + "\t|" + item.getItemName() + "\t\t|" + item.getUnitPrice() + "\t\t|"
                    + item.getQuantity() + "\t\t|" + item.getQtyAmount());
        }
    }
}
