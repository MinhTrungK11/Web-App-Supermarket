/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Chitietdonhang;
import Entity.Donhang;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author letua
 */
public class OrderDAO {
    public List<Donhang> getDonHang(String tinhTrang, String taiKhoan){
        List<Donhang> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "Select d FROM Donhang d WHERE d.tinhTrang=:tinhTrang AND d.taiKhoan=:taiKhoan";
        TypedQuery<Donhang> q = em.createQuery(query, Donhang.class);
        q.setParameter("tinhTrang", tinhTrang);
        q.setParameter("taiKhoan", taiKhoan);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Chitietdonhang> getChiTietDonHang(int maDonHang){
        List<Chitietdonhang> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "Select c FROM Chitietdonhang c WHERE c.chitietdonhangPK.maDonHang=:maDonHang";
        TypedQuery<Chitietdonhang> q = em.createQuery(query, Chitietdonhang.class);
        q.setParameter("maDonHang", maDonHang);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public void xacNhanDonHang(int maDonHang){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "UPDATE Donhang o set o.tinhTrang = 'Đã nhận' where o.maDonHang = :maDonHang";
        TypedQuery<Donhang> q = em.createQuery(query, Donhang.class);
        q.setParameter("maDonHang", maDonHang);
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            q.executeUpdate();
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
