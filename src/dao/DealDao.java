package dao;

import entity.Deal;
import entity.PageDeal;

import java.util.List;
public interface DealDao {
    public Deal findByCommodityId(int commodityId);
    public List<Deal> findBySellId(PageDeal pageDeal);
    public List<Deal> findByBuyId(PageDeal pageDeal);
    public List<Deal> findByDealStatus(PageDeal pageDeal);
    public List<Deal> findAllDeal(PageDeal pageDeal);
    public boolean addDeal(Deal deal);
    public boolean updateDeal(Deal deal);
    public boolean deleteByCommodityId(int commodityId);
    public boolean deleteBySellId(String sellId);
    public boolean deleteByBuyId(String buyId);
    public boolean deleteByDealStatus(int dealStatus);
}
