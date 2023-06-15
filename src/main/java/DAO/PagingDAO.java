/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Chitietdonhang;
import Entity.Loaihang;
import Entity.Nhomhang;
import java.util.List;
import Entity.Sanpham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


/**
 *
 * @author Trung
 */
public class PagingDAO {
    public int getCount()
    {
        String query = "SELECT s FROM Sanpham s  ";
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        list = q.getResultList();
        int total = list.size();
        if (total%12==0) {
            total = total/12;
        } else {
            total=total/12 +1;
        }
        try {
            return total;
        } catch (Exception e) {
            return 0;
        }
        finally {
            em.close();
        }
    }
    public List<Sanpham> getSanPham(int index)
    {
        String query = "SELECT s FROM Sanpham s  ";
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setMaxResults(12);
        q.setFirstResult((index-1)*12);
        list = q.getResultList();
        try {
            return list;
        } catch (Exception e) {
            return null;
        }
        finally {
            em.close();
        }
    }
    public List<Sanpham> getSanPhamindex1()
    {
        String query = "SELECT s FROM Sanpham s  ";
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setMaxResults(12);
        q.setFirstResult(0);
        list = q.getResultList();
        try {
            return list;
        } catch (Exception e) {
            return null;
        }
        finally {
            em.close();
        }
    }
    
}
