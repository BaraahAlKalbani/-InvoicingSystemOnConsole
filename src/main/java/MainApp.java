/**
 * @author Bara'ah Nasser
 */
import java.util.*;

/**
 * Main class for the program, which displays the main menu and handles user
 * input
 */
public class MainApp {
    /**
     * Main method which runs the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); // Scanner for user input
        Menu MenuShopSettingSubMenu = new Menu(); // Submenu for Shop Settings

        // Initialize a map to store the program statistics
        HashMap<String, Integer> programStatistics = new HashMap<>();

        // Adding menu items to Shop Settings submenu
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(1, "Load Data."));
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(2, "Set Shop Name."));
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(3, "Set Invoice Header (Tel / Fax / Email / Website)."));
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(4, "Go Back."));

        MenuItem ShopSettingMenuItem = new MenuItem(1, "Shop Settings."); // Main menu item for Shop Settings
        ShopSettingMenuItem.markTheItemAsMenu(MenuShopSettingSubMenu); // Marking Shop Settings as a submenu

        Menu ManageShopItemsSubMenu = new Menu(); // Submenu for Manage Shop Items
        // Adding menu items to Manage Shop Items submenu
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(1, "Add Items."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(2, "Delete Item."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(3, "Change Item Price."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(4, "Report All Items."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(5, "Go Back."));

        MenuItem ManageShopMenuItem = new MenuItem(2, "Manage Shop Items."); // Main menu item for Manage Shop Items
        ManageShopMenuItem.markTheItemAsMenu(ManageShopItemsSubMenu); // Marking Manage Shop Items as a submenu

        Menu parentMenu = new Menu(); // Main menu
        // Adding main menu items
        parentMenu.addMenuItem(ShopSettingMenuItem);
        parentMenu.addMenuItem(ManageShopMenuItem);
        parentMenu.addMenuItem(new MenuItem(3, "Create New Invoice"));
        parentMenu.addMenuItem(new MenuItem(4, "Report - Statistics."));
        parentMenu.addMenuItem(new MenuItem(5, "Report - All Invoices."));
        parentMenu.addMenuItem(new MenuItem(6, "Search Invoice."));
        parentMenu.addMenuItem(new MenuItem(7, "Program Statistics."));
        parentMenu.addMenuItem(new MenuItem(8, " Exit."));

        // Instantiating classes for use in the program
        ManageShopItems manageShopItems = new ManageShopItems();
        ShopSetting shopSettings = new ShopSetting();
        InvoiceManager invoiceManager = new InvoiceManager();

        Integer choiceStr = 0, subMenuChoice = 0; // Variables for user choices in the menu
        do {
            System.out.println("\n<<Main Menu>>");
            // Display the parent menu and get user's choice
            parentMenu.printMenuItem();
            System.out.print("Enter your Choice : ");
            // Try to parse the user's input as an integer
            try {
                choiceStr = Integer.parseInt(userInput.nextLine());
                MenuItem selectedItem = parentMenu.getMenuItem(choiceStr);
                String description = "MainMenu item  --> " + selectedItem.id + " : " + selectedItem.description;
                // Update the program statistics
                if (programStatistics.containsKey(description)) {
                    programStatistics.put(description, programStatistics.get(description) + 1);
                } else {
                    programStatistics.put(description, 1);
                }
            } catch (NumberFormatException e) {
                // If the input is not an integer, show an error message
                System.out.println("Invalid input. Please enter a valid integer.");
            }

            // Check user's choice in the parent menu
            switch (choiceStr) {
            case 1:
                // Loop until the user selects option 4 (Going Back)
                do {
                    System.out.println("<<Shop Settings>>");
                    parentMenu.getMenuItem(1).menu.printMenuItem();
                    System.out.print("Enter your Choice : ");

                    // Try to parse the user's input as an integer
                    try {
                        subMenuChoice = Integer.parseInt(userInput.nextLine());
                        MenuItem selectedItem = parentMenu.getMenuItem(1).menu.getMenuItem(subMenuChoice);
                        String description = "ShopSettingsMenu item  --> " + selectedItem.id + " : " + selectedItem.description;
                        // Update the program statistics
                        if (programStatistics.containsKey(description)) {
                            programStatistics.put(description, programStatistics.get(description) + 1);
                        } else {
                            programStatistics.put(description, 1);
                        }
                    } catch (NumberFormatException e) {
                        // If the input is not an integer, show an error message
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }

                    // Check user's choice in the Shop Settings sub-menu
                    switch (subMenuChoice) {
                    case 1:
                        System.out.println("<<Loading Data>>");
                        // load shopSettings Data From File and save it into shopSettings custom object
                        shopSettings = shopSettings.loadData();
                        // load Invoices list From File and save it into an array list
                        ArrayList<Invoice> invoices = invoiceManager.loadInvoices();
                        break;
                    case 2:
                        System.out.println("<<Seting the Shop Name>>");
                        System.out.print("Enter the shop name: ");
                        String nameString = userInput.nextLine();
                        shopSettings = shopSettings.loadData();
                        shopSettings.setShopName(nameString);
                        shopSettings.saveData(shopSettings);
                        break;
                    case 3:
                        System.out.println("<<Setting Invoice Header>>");
                        shopSettings = shopSettings.loadData();
                        System.out.print("Enter the shop Tel: ");

                        // Try to parse the user's input as an integer
                        try {
                            shopSettings.setTel(userInput.nextLine());
                        } catch (NumberFormatException e) {
                            // If the input is not an integer, show an error message
                            System.out.println("Invalid input. Please enter a valid integer.");
                            break;
                        }

                        System.out.print("Enter the shop Fax: ");

                        // Try to parse the user's input as an integer
                        try {
                            shopSettings.setFax(userInput.nextLine());
                        } catch (NumberFormatException e) {
                            // If the input is not an integer, show an error message
                            System.out.println("Invalid input. Please enter a valid integer.");
                            break;
                        }

                        System.out.print("Enter the shop Email: ");
                        shopSettings.setEmail(userInput.nextLine());
                        System.out.print("Enter the shop Website: ");
                        shopSettings.setWebsite(userInput.nextLine());

                        shopSettings.saveData(shopSettings);
                        break;
                    case 4:
                        System.out.println("Going Back...");
                        break;
                    default:

                        System.out.println("Invalid Input!!!");
                        break;
                    }

                } while (subMenuChoice != 4);
                break;
            // case 2 represents the sub-menu for Shop Settings
            case 2:
                do {
                    // Prints the Manage Shop Items sub-menu
                    System.out.println("<<Manage Shop Items>>");
                    parentMenu.getMenuItem(2).menu.printMenuItem();

                    // Prompts the user to enter their choice
                    System.out.print("Enter your Choice : ");

                    try {
                        // Parses the user's input into an integer
                        subMenuChoice = Integer.parseInt(userInput.nextLine());
                        MenuItem selectedItem = parentMenu.getMenuItem(2).menu.getMenuItem(subMenuChoice);
                        String description = "ManageShopItemsMenu item --> " + selectedItem.id + " : " + selectedItem.description;
                        // Update the program statistics
                        if (programStatistics.containsKey(description)) {
                            programStatistics.put(description, programStatistics.get(description) + 1);
                        } else {
                            programStatistics.put(description, 1);
                        }
                    } catch (NumberFormatException e) {
                        // If the input is not an integer, prints an error message
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }

                    // switch case for the user's choice from the sub-menu
                    switch (subMenuChoice) {
                    case 1:
                        // Option to add an item to the shop
                        System.out.println("<<Add Item>>");
                        manageShopItems.addItem();
                        break;
                    case 2:
                        // Option to delete an item from the shop
                        System.out.println("<<Delete Item>>");

                        // Prompts the user to enter the item ID
                        System.out.print("Enter Item ID: ");

                        try {
                            // Parses the user's input into an integer
                            Integer ItemID = Integer.parseInt(userInput.nextLine());

                            // Deletes the item by its ID
                            manageShopItems.deleteItemById(ItemID);
                        } catch (NumberFormatException e) {
                            // If the input is not an integer, prints an error message
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }

                        break;
                    case 3:
                        // Option to change the price of an item
                        System.out.println("<<Change Item Price.>>");

                        try {
                            // Prompts the user to enter the item ID
                            System.out.print("Enter Item ID: ");
                            Integer ItemID2 = Integer.parseInt(userInput.nextLine());

                            // Prompts the user to enter the new price
                            System.out.print("Enter the New Unit Price: ");
                            Double newPrice = Double.parseDouble(userInput.nextLine());

                            // Changes the price of the item by its ID
                            manageShopItems.changeItemPrice(ItemID2, newPrice);
                        } catch (NumberFormatException e) {
                            // If the input is not a valid number, prints an error message
                            System.out.println("Invalid input. Please enter a valid number.");
                        }

                        break;
                    case 4:
                        // Option to report all items in the shop
                        System.out.println("<<Report All Items>>");
                        manageShopItems.showAllItems();
                        break;
                    case 5:
                        // Option to go back to the previous menu
                        System.out.println("Going Back...");
                        break;

                    default:
                        System.out.println("Invalid Input!!!");
                        break;
                    }
                } while (subMenuChoice != 5);

                break;
            case 3:
                System.out.println("<<Create New Invoice>>");
                // Call the addInvoice method of the InvoiceManager class
                InvoiceManager.addInvoice(shopSettings, manageShopItems.loadData());
                break;
            case 4:
                System.out.println("<<Report - Statistics>>");
                // Call the Report method of the InvoiceManager class
                InvoiceManager.Report();
                break;
            case 5:
                System.out.println("<<Report - All Invoices>>");
                // Call the showAllInvoices method of the InvoiceManager class
                InvoiceManager.showAllInvoices();
                break;
            case 6:
                System.out.println("<<Search Invoice>>");
                // Call the searchInvoice method of the InvoiceManager class
                InvoiceManager.searchInvoice();
                break;
            case 7:
                System.out.println("<<Program Statistics>>");
                // display program statistics
                parentMenu.printProgramStatistics(programStatistics);

                break;
            case 8:
                System.out.println("<<EXITING PROGRAM!!!>>");
                break;
            default:
                System.out.println("Invalid Input!!!");
                break;
            }
        } while (choiceStr != 8);
    }

}
