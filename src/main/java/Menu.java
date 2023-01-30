import java.util.*;

/**
 * 
 */

/**
 * @author LAP-6
 *
 */
public class Menu {
    String title;
    ArrayList<MenuItem> listOfMenuItems = new ArrayList<MenuItem>();

    public Menu() {

    }

    public void addMenuItem(MenuItem menuItem) {
        listOfMenuItems.add(menuItem);
    }

    public void printMenuItem() {

        if (title != null) {
            System.out.println(title);
        }
        for (MenuItem currItem : listOfMenuItems) {
            System.out.println(currItem.id + " : " + currItem.description);
        }

    }

    public MenuItem getMenuItem(int id) {
        return listOfMenuItems.get(id - 1);
    }
}