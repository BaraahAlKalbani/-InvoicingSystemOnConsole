import java.io.*;
import java.util.jar.Attributes.Name;

import com.google.gson.Gson;

public class ShopSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    // instance variable to store the shop name
    String shopName;
    // instance variable to store the telephone number
    String tel;
    // instance variable to store the fax number
    String fax;
    // instance variable to store the email address
    String email;
    // instance variable to store the website
    String website;

    /**
     * default constructor for the ShopSetting class
     */
    public ShopSetting() {
        // default constructor
    }

    /**
     * Constructor for the ShopSetting class
     * 
     * @param shopName name of the shop
     * @param tel      telephone number of the shop
     * @param fax      fax number of the shop
     * @param email    email address of the shop
     * @param website  website of the shop
     */
    public ShopSetting(String shopName, String tel, String fax, String email, String website) {
        this.shopName = shopName;
        this.tel = tel;
        this.fax = fax;
        this.email = email;
        this.website = website;
    }

    /**
     * Loads the shop setting data from a JSON file
     * 
     * @return ShopSetting object with the loaded data
     */
    public ShopSetting loadData() {
        final String shopSetting_File = "data/shopSettingData.json";
        try {
            File settingsFile = new File(shopSetting_File);
            if (settingsFile.createNewFile()) {
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(shopSetting_File)) {
            return gson.fromJson(reader, ShopSetting.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Saves the shop setting data to a JSON file
     * 
     * @param shopSettings the ShopSetting object to be saved
     */
    public void saveData(ShopSetting shopSettings) {
        Gson gson = new Gson();
        String json = gson.toJson(shopSettings);
        final String shopSetting_File = "data/shopSettingData.json";
        try {
            File settingsFile = new File(shopSetting_File);
            if (settingsFile.createNewFile()) {
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error creating file: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(shopSetting_File)) {
            gson.toJson(shopSettings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Setters and getters
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
