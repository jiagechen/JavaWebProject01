package dao;

import entity.Commodity;
import entity.PageCommodity;
import mybatis.Mybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommodityDaoImpl implements CommodityDao{
    public Commodity findByCommodityId(int commodityId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.findByCommodityId";
        Commodity commodity=session.selectOne(statement,commodityId);
        session.close();
        return commodity;
    }
    public List<Commodity> findByCommodityName(PageCommodity page){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();
        String statement="mapper.CommodityMapper.findByCommodityName";
        List<Commodity> commoditys = session.selectList(statement,page);
        session.close();
        return commoditys;
    }
    public List<Commodity> findByCommodityStatus(PageCommodity pageCommodity){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.findByCommodityStatus";
        List<Commodity> commodities=session.selectList(statement,pageCommodity);
        session.close();
        return commodities;
    }
    public List<Commodity> findByCommodityType(PageCommodity pageCommodity){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.findByCommodityType";
        List<Commodity> commodities=session.selectList(statement,pageCommodity);
        session.close();
        return commodities;
    }
    
    public Integer findNewCommodityId(){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.findNewCommodityId";
        Integer recId=session.selectOne(statement);
        if (recId == null)
            return 0;
        session.close();
        return recId;
    }
    public List<Commodity> findBySellId(PageCommodity pageCommodity){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.findBySellId";
        List<Commodity> commodities=session.selectList(statement,pageCommodity);
        session.close();
        return commodities;
    }
    public List<Commodity> findByPrice(PageCommodity pageCommodity){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.findByPrice";
        List<Commodity> commodities=session.selectList(statement,pageCommodity);
        session.close();
        return commodities;
    }
    public List<Commodity> findAllCommodity(PageCommodity pageCommodity){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.findAllCommodity";
        List<Commodity> commodities=session.selectList(statement,pageCommodity);
        session.close();
        return commodities;
    }

    public boolean addCommodity(Commodity commodity){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.addCommodity";
        int count=session.insert(statement,commodity);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateCommodity(Commodity commodity){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommodityMapper.updateCommodity";
        int count = session.update(statement,commodity);
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

        String statement="mapper.CommodityMapper.deleteByCommodityId";
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

        String statement="mapper.CommodityMapper.deleteBySellId";
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

}
