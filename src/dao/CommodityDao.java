package dao;

import entity.Commodity;
import entity.PageCommodity;

import java.util.List;

public interface CommodityDao {
    public Commodity findByCommodityId(int commodityId);
    public List<Commodity> findByCommodityName(PageCommodity page);
    public List<Commodity> findByCommodityStatus(PageCommodity pageCommodity);
    public List<Commodity> findByCommodityType(PageCommodity pageCommodity);
    public List<Commodity> findBySellId(PageCommodity pageCommodity);
    public List<Commodity> findByPrice(PageCommodity pageCommodity);
    public List<Commodity> findAllCommodity(PageCommodity pageCommodity);
    public Integer findNewCommodityId();
    public boolean addCommodity(Commodity commodity);
    public boolean updateCommodity(Commodity commodity);
    public boolean deleteByCommodityId(int commodityId);
    public boolean deleteBySellId(String sellId);
}
