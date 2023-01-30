import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;

public class Serialization {
    
    private Gson gson = new Gson();
    private ArrayList<Item> items;
    private ArrayList<Invoice> invoices;
    
    public void saveData(Object data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Error in saving data to file: " + e.getMessage());
        }
    }
    public static void saveShopSettings(ShopSetting settings) {
        try (FileWriter writer = new FileWriter(SETTINGS_FILE)) {
          Gson gson = new Gson();
          gson.toJson(settings, writer);
        } catch (IOException e) {
          System.out.println("Error writing shop settings file: " + e.getMessage());
        }
      }

    public Object loadData(String fileName, Class className) {
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, className);
        } catch (IOException e) {
            System.out.println("Error in loading data from file: " + e.getMessage());
        }
        return null;
    }
}
