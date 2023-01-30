import java.util.Scanner;

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
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(2, "Delete Items."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(3, "Change Item Price."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(4, "Report All Items."));
        ManageShopItemsSubMenu.addMenuItem(new MenuItem(5, "Go Back."));

        MenuItem ManageShopMenuItem = new MenuItem(2, "Manage Shop Items.");
        ManageShopMenuItem.markTheItemAsMenu(ManageShopItemsSubMenu);

        Menu parentMenu = new Menu();
        parentMenu.addMenuItem(ShopSettingMenuItem);
        parentMenu.addMenuItem(ManageShopMenuItem);

        parentMenu.addMenuItem(new MenuItem(3, "Create New Invoice (Invoices should be save/serialized)"));
        parentMenu.addMenuItem(new MenuItem(4, "Report - Statistics (No Of Items, No of Invoices, Total Sales)."));
        parentMenu.addMenuItem(new MenuItem(5, "Report - All Invoices."));
        parentMenu.addMenuItem(new MenuItem(6, "Search Invoice."));
        parentMenu.addMenuItem(new MenuItem(7, "Program Statistics."));
        parentMenu.addMenuItem(new MenuItem(8, " Exit."));


        Scanner userInput = new Scanner(System.in);
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
                    if (subMenuChoice == 1) {

                    } else if (subMenuChoice == 2) {

                    } else if (subMenuChoice == 3) {

                    } else if (subMenuChoice != 4) {
                        System.out.println("Invalid Input!!!");
                    }

                } while (subMenuChoice != 4);
                break;
            case 2:
                do {
                    System.out.println("<<Shop Settings>>");
                    parentMenu.getMenuItem(2).menu.printMenuItem();
                    System.out.print("Enter your Choice : ");
                    subMenuChoice = Integer.parseInt(userInput.nextLine());
                    if (subMenuChoice == 1) {

                    } else if (subMenuChoice == 2) {

                    } else if (subMenuChoice == 3) {

                    } else if (subMenuChoice == 4) {

                    }
                    else if (subMenuChoice != 5) {
                        System.out.println("Invalid Input!!!");
                    }

                } while (subMenuChoice != 5);

                break;
            case 3:
                System.out.println("<<Create New Invoice>>");

                break;
            case 4:
                System.out.println("<<Report - Statistics>>");

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
