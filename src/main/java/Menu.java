import java.util.*;

public class Menu {
    private LinkedList<String> menuItems;
    private Scanner input;

    public Menu() {
        menuItems = new LinkedList<>();
        input = new Scanner(System.in);
    }

    public void addMenuItem(String item) {
        menuItems.add(item);
    }

    public void showMenu() {
        System.out.println("Main Menu:");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + "-" + menuItems.get(i));
        }
        System.out.print("Enter your choice: ");
    }

    public int getChoice() {
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }
}
