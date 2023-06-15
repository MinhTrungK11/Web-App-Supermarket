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
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 *
 * @author Trung
 */
public class FilterHomeDAO {
    public List<Sanpham> getSearchSanPham(String tenSanPham) {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.tenSanPham LIKE CONCAT('%',:tenSanPham,'%') ";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setParameter("tenSanPham", tenSanPham);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getFilterCategory(Loaihang idLoaiHoang) {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idLoaiHoang=:idLoaiHoang";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setParameter("idLoaiHoang", idLoaiHoang);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getGroupSP(Nhomhang idNhomHang) {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idNhomHang=:idNhomHang";
        
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setParameter("idNhomHang", idNhomHang);
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
    public List<Sanpham> getSanPhamOrange() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.tenSanPham LIKE CONCAT('%','cam','%') ";
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
    
    public Nhomhang get1NH(String idNhomHang) {
        String query = "SELECT n FROM Nhomhang n "
                + "WHERE n.idNhomHang=:idNhomHang";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Nhomhang> q = em.createQuery(query, Nhomhang.class);
        q.setParameter("idNhomHang", idNhomHang);
        try {
            Nhomhang item = q.getSingleResult();
            return item;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public Loaihang get1LH(String idLoaiHang) {
        String query = "SELECT l FROM Loaihang l "
                + "WHERE l.idLoaiHang=:idLoaiHang";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Loaihang> q = em.createQuery(query, Loaihang.class);
        q.setParameter("idLoaiHang", idLoaiHang);
        try {
            Loaihang item = q.getSingleResult();
            return item;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
