package entity;

/**
 * Created by Windows10.0 on 2020/9/7.
 */
public class Commodity {
    private int commodityId = -1;
    private String commodityName = null;
    private String commodityType = null;
    private int commodityStatus = -1;
    private double price = -1;
    private String sellId = null;
    private String description = null;
    private String commodityImageURL = null;

    public Commodity(int commodityId, String commodityName, String commodityType, int commodityStatus, double price, String sellId, String description, String commodityImageURL) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityType = commodityType;
        this.commodityStatus = commodityStatus;
        this.price = price;
        this.sellId = sellId;
        this.description = description;
        this.commodityImageURL = commodityImageURL;
    }
    
    public Commodity(String commodityName, String commodityType, int commodityStatus, double price, String sellId, String description, String commodityImageURL) {
        this.commodityName = commodityName;
        this.commodityType = commodityType;
        this.commodityStatus = commodityStatus;
        this.price = price;
        this.sellId = sellId;
        this.description = description;
        this.commodityImageURL = commodityImageURL;
    }

    public Commodity() {
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public int getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(int commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommodityImageURL() {
        return commodityImageURL;
    }

    public void setCommodityImageURL(String commodityImageURL) {
        this.commodityImageURL = commodityImageURL;
    }
}
