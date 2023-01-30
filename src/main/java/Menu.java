import java.util.*;


public class Menu {
    ArrayList<MenuItem> listOfMenuItems = new ArrayList<MenuItem>();

    public Menu() {

    }

    public void addMenuItem(MenuItem menuItem) {
        listOfMenuItems.add(menuItem);
    }

    public void printMenuItem() {
        for (MenuItem currItem : listOfMenuItems) {
            System.out.println(currItem.id + " - " + currItem.description);
        }

    }

    public MenuItem getMenuItem(int id) {
        return listOfMenuItems.get(id - 1);
    }
}