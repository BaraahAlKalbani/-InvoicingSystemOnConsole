/**
 * 
 */


/**
 * Class to represent a menu item
 */
public class MenuItem {

    public MenuItem(int id, String description) {
        this.id = id;
        this.description = description;
    }

    // instance variable to store the id of the menu item
    int id;
    // instance variable to store the description of the menu item
    String description;
    Menu menu = null;

    Boolean isAMenu() {
        return menu != null;
    }

    void printItem() {
        System.out.println(this.id + " : " + this.description);
        if (this.isAMenu()) {
            menu.printMenuItem();
        }
    }

    public void markTheItemAsMenu(Menu menu) {
        this.menu = menu;
    }
}