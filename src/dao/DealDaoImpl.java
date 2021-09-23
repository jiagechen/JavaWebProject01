package dao;

import entity.Deal;
import entity.PageDeal;
import mybatis.Mybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DealDaoImpl implements DealDao{
    public Deal findByCommodityId(int commodityId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.DealMapper.findByCommodityId";
        Deal deal = session.selectOne(statement,commodityId);
        session.close();
        return deal;
    }
    public List<Deal> findBySellId(PageDeal pageDeal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.DealMapper.findBySellId";
        List<Deal> deals = session.selectList(statement,pageDeal);
        session.close();
        return deals;
    }
    public List<Deal> findByBuyId(PageDeal pageDeal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.DealMapper.findByBuyId";
        List<Deal> deals = session.selectList(statement,pageDeal);
        session.close();
        return deals;
    }
    public List<Deal> findByDealStatus(PageDeal pageDeal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.DealMapper.findByDealStatus";
        List<Deal> deals = session.selectList(statement,pageDeal);
        session.close();
        return deals;
    }
    public List<Deal> findAllDeal(PageDeal pageDeal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.DealMapper.findAllDeal";
        List<Deal> deals = session.selectList(statement,pageDeal);
        session.close();
        return deals;
    }
    public boolean addDeal(Deal deal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.DealMapper.addDeal";
        int count = session.insert(statement,deal);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateDeal(Deal deal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.DealMapper.updateDeal";
        int count = session.update(statement,deal);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByCommodityId(int commodityId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.DealMapper.deleteByCommodityId";
        int count = session.delete(statement,commodityId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteBySellId(String sellId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.DealMapper.deleteBySellId";
        int count = session.delete(statement,sellId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByBuyId(String buyId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.DealMapper.deleteByBuyId";
        int count = session.delete(statement,buyId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean deleteByDealStatus(int dealStatus){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.DealMapper.deleteByDealStatus";
        int count = session.delete(statement,dealStatus);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else {
            return true;
        }
    }
}
