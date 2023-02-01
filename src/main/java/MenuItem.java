/**
 * @author Bara'ah Nasser
 */
/**
 * Class to represent a menu item
 */
public class MenuItem {

    /**
     * Constructor for the MenuItem class
     * 
     * @param id          the id of the menu item
     * @param description the description of the menu item
     */
    public MenuItem(int id, String description) {
        this.id = id;
        this.description = description;
    }

    // Instance variable to store the id of the menu item
    int id;
    // Instance variable to store the description of the menu item
    String description;
    // Instance variable to store a reference to the sub-menu (if any)
    Menu menu = null;

    /**
     * Check if the menu item is a menu
     * 
     * @return true if the menu item is a menu, false otherwise
     */
    Boolean isAMenu() {
        return menu != null;
    }

    /**
     * Print the menu item and its sub-menu (if any)
     */
    void printItem() {
        System.out.println(this.id + " > " + this.description);
        if (this.isAMenu()) {
            menu.printMenuItem();
        }
    }

    /**
     * Mark the menu item as a menu
     * 
     * @param menu the sub-menu of the menu item
     */
    public void markTheItemAsMenu(Menu menu) {
        this.menu = menu;
    }
}
