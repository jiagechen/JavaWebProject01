package entity;

import java.sql.Timestamp;

/**
 * Created by Windows10.0 on 2020/9/7.
 */
public class Deal {
    private int commodityId = -1;
    private String commodityName = null;
    private String sellId = null;
    private String buyId = null;
    private Timestamp dealTime = null;
    private int dealStatus = -1;

    public Deal(int commodityId, String commodityName, String sellId,
			String buyId, Timestamp dealTime, int dealStatus) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.sellId = sellId;
		this.buyId = buyId;
		this.dealTime = dealTime;
		this.dealStatus = dealStatus;
	}

	public Deal() {
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

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }

    public int getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(int dealStatus) {
        this.dealStatus = dealStatus;
    }
}
