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
    /**
     * Prints the program statistics sorted by the keys in ascending order.
     *
     * @param programStatistics a HashMap containing menu items as keys and the number of times each item has been selected as values
     */
    public void printProgramStatistics(HashMap<String, Integer> programStatistics) {
        // Create a list of map entries from the HashMap
        List<Map.Entry<String, Integer>> list = new ArrayList<>(programStatistics.entrySet());
        // Sort the list by the keys in ascending order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
          @Override
          public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
            return entry1.getKey().compareTo(entry2.getKey());
          }
        });
     // Print the header for the program statistics
        System.out.println("\n\t\t<<Program Statistics>>");
     // Print the number of times each menu item has been selected
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " ,has been selected : " + entry.getValue() + " times");
        }

        // Print the footer for the program statistics
        System.out.println("--- End of Program Statistics ---\n");
    }

}
