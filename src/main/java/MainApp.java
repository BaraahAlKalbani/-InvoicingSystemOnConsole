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

    parentMenu.addMenuItem(new MenuItem(3, "Report: Statistics (No Of Items, No of Invoices, Total Sales)."));
    parentMenu.addMenuItem(new MenuItem(4, "Report: All Invoices."));

  
  }
}
