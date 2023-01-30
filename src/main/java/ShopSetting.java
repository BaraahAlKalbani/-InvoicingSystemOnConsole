import java.io.*;

import com.google.gson.Gson;

public class ShopSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    private String shopName;
    private String tel;
    private String fax;
    private String email;
    private String website;

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
                System.out.println("shopSetting_File created successfully");
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

    public void saveData() {
        Gson gson = new Gson();
        final String shopSetting_File = "data/shopSettingData.json";
        try {
            File settingsFile = new File(shopSetting_File);
            if (settingsFile.createNewFile()) {
                System.out.println("shopSetting_File created successfully");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        try (FileWriter writer = new FileWriter(shopSetting_File)) {
            gson.toJson(this, writer);
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
