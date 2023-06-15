/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Account;
import Entity.Donhang;
import com.google.gson.Gson;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ACER
 */
public class AdminDashBoardDAO {
    
    public double getTotalSale() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Query query = em.createQuery("SELECT SUM(d.tongThanhToan) FROM Donhang d");
        try {
            Number result = (Number)query.getSingleResult();
            return result.doubleValue();
        } catch (NoResultException e) {
            return 0;
        } finally {
            em.close();
        }
    }
    
    public double getProductCost() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Query query = em.createQuery("SELECT SUM(cd.thanhTien) FROM Chitietdonhang cd");
        try {
            Number result = (Number)query.getSingleResult();
            return result.doubleValue();
        } catch (NoResultException e) {
            return 0;
        } finally {
            em.close();
        }
    }
    
    public String dataForPieChart() {
        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        DecimalFormat df = new DecimalFormat("#.##");
        
        double totalSale = getTotalSale();
        double productCost = getProductCost();
        double depreciationPercentage = 20;
        double productCostPercentage = Double.valueOf(df.format(productCost / totalSale * 100));
        double profitPercentage = 100 - (depreciationPercentage + productCostPercentage);
        
        map = new HashMap<Object, Object>();
        map.put("label", "Product Cost");
        map.put("y", productCostPercentage);
        list.add(map);
        
        map = new HashMap<Object, Object>();
        map.put("label", "Depreciation");
        map.put("y", depreciationPercentage);
        list.add(map);
        
        map = new HashMap<Object, Object>();
        map.put("label", "Profit");
        map.put("y", profitPercentage);
        map.put("exploded", true);
        list.add(map);
        
        return gsonObj.toJson(list);
    }
    
    public List<Donhang> getDonhang() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Query query = em.createQuery("SELECT d FROM Donhang d");  
        try {
            List<Donhang> list = query.getResultList();
            return list;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
} 
