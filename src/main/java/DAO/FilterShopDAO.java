/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import java.util.List;
import Entity.Sanpham;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 *
 * @author Trung
 */
public class FilterShopDAO {
    public List<Sanpham> getFilterGia(double min,double max) {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.giaBan > :min AND s.giaBan < :max ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setParameter("min", min);
        q.setParameter("max", max);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getVegettable() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idLoaiHoang.idLoaiHang = 'lh1' or s.idLoaiHoang.idLoaiHang = 'lh2' ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getFreshMeat() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.tenSanPham LIKE CONCAT('%','thit','%') and s.idNhomHang.idNhomHang = 'nh2' ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getFastFood() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idNhomHang.idNhomHang = 'nh3'  ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getFruit() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idLoaiHoang.idLoaiHang = 'lh3'  ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getOatmeal() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idLoaiHoang.idLoaiHang = 'lh10'  ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getFreshOnion() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idLoaiHoang.idLoaiHang = 'lh2' and s.tenSanPham LIKE CONCAT('%','hanh','%')  ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
