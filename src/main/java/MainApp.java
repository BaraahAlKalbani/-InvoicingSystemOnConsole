import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class MainApp {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();

        Menu MenuShopSettingSubMenu = new Menu();
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(1, "Load Data (Items and invoices)."));
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(2, "Set Shop Name."));
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(3, "Set Invoice Header (Tel / Fax / Email / Website)."));
        MenuShopSettingSubMenu.addMenuItem(new MenuItem(4, "Go Back."));

        MenuItem ShopSettingMenuItem = new MenuItem(1, "Shop Settings.");
        ShopSettingMenuItem.markTheItemAsMenu(MenuShopSettingSubMenu);

        Menu ManageShopItemsSubMenu = new Menu();

        ManageShopItemsSubMenu.addMenuItem(new MenuItem(1, "Add Items."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(2, "Delete Item."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(3, "Change Item Price."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(4, "Report All Items."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(5, "Go Back."));

        MenuItem ManageShopMenuItem = new MenuItem(2, "Manage Shop Items.");
        ManageShopMenuItem.markTheItemAsMenu(ManageShopItemsSubMenu);

        Menu parentMenu = new Menu();
        parentMenu.addMenuItem(ShopSettingMenuItem);
        parentMenu.addMenuItem(ManageShopMenuItem);

        parentMenu.addMenuItem(new MenuItem(3, "Create New Invoice"));
        parentMenu.addMenuItem(new MenuItem(4, "Report - Statistics (No Of Items, No of Invoices, Total Sales)."));
        parentMenu.addMenuItem(new MenuItem(5, "Report - All Invoices."));
        parentMenu.addMenuItem(new MenuItem(6, "Search Invoice."));
        parentMenu.addMenuItem(new MenuItem(7, "Program Statistics."));
        parentMenu.addMenuItem(new MenuItem(8, " Exit."));

        Scanner userInput = new Scanner(System.in);

        ManageShopItems manageShopItems = new ManageShopItems();
        ShopSetting shopSettings = new ShopSetting();
        Invoice invoice = new Invoice();

        Integer choiceStr, subMenuChoice = 0;
        do {
            parentMenu.printMenuItem();
            System.out.print("Enter your Choice : ");
            choiceStr = Integer.parseInt(userInput.nextLine());
            switch (choiceStr) {
            case 1:
                do {
                    System.out.println("<<Shop Settings>>");
                    parentMenu.getMenuItem(1).menu.printMenuItem();
                    System.out.print("Enter your Choice : ");
                    subMenuChoice = Integer.parseInt(userInput.nextLine());
                    switch (subMenuChoice) {
                    case 1:
                        System.out.println("<<Loading Data>>");
                        shopSettings = shopSettings.loadData();
                        manageShopItems.loadData();
                        break;
                    case 2:
                        System.out.println("<<Seting the Shop Name>>");
                        System.out.print("Enter the shop name: ");
                        String nameString = userInput.nextLine();
                        shopSettings.setShopName(nameString);
                        shopSettings.saveData(shopSettings);
                        break;
                    case 3:
                        System.out.println("<<Setting Invoice Header>>");
                        System.out.print("Enter the shop Tel: ");
                        shopSettings.setTel(userInput.nextLine());
                        System.out.print("Enter the shop Fax: ");
                        shopSettings.setFax(userInput.nextLine());
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
            case 2:
                do {
                    System.out.println("<<Shop Settings>>");
                    parentMenu.getMenuItem(2).menu.printMenuItem();
                    System.out.print("Enter your Choice : ");
                    subMenuChoice = Integer.parseInt(userInput.nextLine());
                    switch (subMenuChoice) {
                    case 1:
                        System.out.println("<<Add Item>>");
                        manageShopItems.addItem();
                        break;
                    case 2:
                        System.out.println("<<Delete Item>>");
                        System.out.print("Enter Item ID: ");
                        Integer ItemID = Integer.parseInt(userInput.nextLine());
                        manageShopItems.deleteItemById(ItemID);
                        break;
                    case 3:
                        System.out.println("<<Change Item Price.>>");
                        System.out.print("Enter Item ID: ");
                        Integer ItemID2 = Integer.parseInt(userInput.nextLine());
                        System.out.print("Enter the New Unit Price: ");
                        Double newPrice = Double.parseDouble(userInput.nextLine());
                        manageShopItems.changeItemPrice(ItemID2, newPrice);
                        break;
                    case 4:
                        System.out.println("<<Report All Items>>");
                        manageShopItems.showAllItems();
                        break;
                    case 5:
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
                invoice.addInvoice();
                break;
            case 4:
                System.out.println("<<Report - Statistics>>");
                invoice.generateStatistics();
                break;
            case 5:
                System.out.println("<<Report - All Invoices>>");

                break;
            case 6:
                System.out.println("<<Search Invoice>>");

                break;
            case 7:
                System.out.println("<<Program Statistics>>");

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
