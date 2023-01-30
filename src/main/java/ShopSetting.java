import java.io.*;
import java.util.jar.Attributes.Name;

import com.google.gson.Gson;

public class ShopSetting implements Serializable {

    private static final long serialVersionUID = 1L;
     String shopName;
     String tel;
     String fax;
     String email;
     String website;

    public ShopSetting() {
        // default constructor
    }

    public ShopSetting(String shopName, String tel, String fax, String email, String website) {
        this.shopName = shopName;
        this.tel = tel;
        this.fax = fax;
        this.email = email;
        this.website = website;
    }


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
