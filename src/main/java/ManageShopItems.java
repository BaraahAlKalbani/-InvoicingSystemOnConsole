import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

class ManageShopItems {
    private ArrayList<Item> items = new ArrayList<>();;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ManageShopItems() {

    }

    public void loadData() {
        final String ItemsList_File = "data/Items.json";
        try {
            File itemsFile = new File(ItemsList_File);
            if (itemsFile.createNewFile()) {
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            items = gson.fromJson(new FileReader(ItemsList_File), new TypeToken<ArrayList<Item>>() {
            }.getType());
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem() {
        final String itemsListFile = "data/Items.json";
        File itemsFile = new File(itemsListFile);

        try {
            if (!itemsFile.exists()) {
                itemsFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Item ID: ");
        int id = input.nextInt();
        System.out.print("Item name: ");
        String name = input.next();
        System.out.print("Unit price: ");
        double unitPrice = input.nextDouble();
        System.out.print("Quantity: ");
        int quantity = input.nextInt();

        try (FileReader reader = new FileReader(itemsListFile)) {
            items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {
            }.getType());
            if (items == null) {
                items = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        for (Item item : items) {
            if (item.getItemID() == id) {
                System.out.println("Item with ID " + id + " already exists.");
                return;
            }
        }
        Item newItem = new Item(id, name, unitPrice, quantity,(unitPrice*quantity));
        items.add(newItem);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemsListFile))) {
            gson.toJson(items, bw);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }

        System.out.println("Item added successfully.");
    }

    public void deleteItemById(int id) {
        final String itemsListFile = "data/Items.json";
        File itemsFile = new File(itemsListFile);

        try (FileReader reader = new FileReader(itemsListFile)) {
            items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {
            }.getType());
            if (items == null) {
                System.out.println("Item list is empty");
                return;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

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
        if(!idFound)
        {
            System.out.println("Item Not Found!!.");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemsListFile))) {
            gson.toJson(items, bw);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }
    }
    
    public void changeItemPrice(int id, double newPrice) {
        final String itemsListFile = "data/Items.json";
        File itemsFile = new File(itemsListFile);

        try (FileReader reader = new FileReader(itemsListFile)) {
            items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {}.getType());
            if (items == null) {
                System.out.println("Item list is empty");
                return;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        boolean itemFound = false;
        for (Item item : items) {
            if (item.getItemID() == id) {
                item.setUnitPrice(newPrice);
                item.setQtyAmount();
                itemFound = true;
                System.out.println("Item price changed successfully.");
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item not found.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemsListFile))) {
            gson.toJson(items, bw);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }
    }
    public void showAllItems() {
        final String itemsListFile = "data/Items.json";
        File itemsFile = new File(itemsListFile);

        try (FileReader reader = new FileReader(itemsListFile)) {
            items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {}.getType());
            if (items == null) {
                System.out.println("Item list is empty");
                return;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("ID\t|Name\t\t|Unit Price\t|Quantity\t|qtyAmount");
        for (Item item : items) {
            System.out.println(item.getItemID() + "\t|" + item.getItemName() + "\t\t|" + item.getUnitPrice() + "\t\t|" + item.getQuantity()+ "\t\t|" +item.getQtyAmount());
        }
    }


}
