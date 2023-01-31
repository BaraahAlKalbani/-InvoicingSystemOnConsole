import java.util.*;

public class Menu {
    // A list to store the menu items
    ArrayList<MenuItem> listOfMenuItems = new ArrayList<MenuItem>();

    /**
     * Default constructor for the Menu class
     */
    public Menu() {

    }

    /**
     * Add a new menu item to the list of menu items
     * 
     * @param menuItem the menu item to be added
     */
    public void addMenuItem(MenuItem menuItem) {
        listOfMenuItems.add(menuItem);
    }

    /**
     * Print all the menu items in the list of menu items
     */
    public void printMenuItem() {
        // Loop through each menu item in the list
        for (MenuItem currItem : listOfMenuItems) {
            System.out.println(currItem.id + " - " + currItem.description);
        }
    }

    /**
     * Get the menu item with the specified id
     * 
     * @param id the id of the menu item to be retrieved
     * @return the menu item with the specified id
     */
    public MenuItem getMenuItem(int id) {
        // Return the menu item at the specified index in the list
        return listOfMenuItems.get(id - 1);
    }
}
