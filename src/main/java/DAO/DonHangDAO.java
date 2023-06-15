/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Chitietdonhang;
import Entity.ChitietdonhangPK;
import Entity.Donhang;
import Entity.Giohang;
import Entity.Voucher;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author mrtru
 */
public class DonHangDAO {

    public List<Chitietdonhang> getChiTietDonHang() {
        List<Chitietdonhang> list = new ArrayList<>();
        String query = "Select d From Chitietdonhang d";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Chitietdonhang> q = em.createQuery(query, Chitietdonhang.class);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Donhang> getDonHang(){
        List<Donhang> list = new ArrayList<>();
        String query = "SELECT d FROM Donhang d";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Donhang> q = em.createQuery(query, Donhang.class);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public Donhang getDonHangById(int maDonHang) {
        String query = "SELECT d FROM Donhang d WHERE d.maDonHang = :maDonHang";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Donhang> q = em.createQuery(query, Donhang.class);
        q.setParameter("maDonHang", maDonHang);
        try {
            Donhang item = q.getSingleResult();
            return item;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Chitietdonhang> getChiTietDonHangById(int maDonHang){
        List<Chitietdonhang> list = new ArrayList<>();
        String query = "SELECT c FROM Chitietdonhang c WHERE c.chitietdonhangPK.maDonHang = :maDonHang";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
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
    public  List<Chitietdonhang> getChiTietDonHangByIdSp(int idSanPham){
         List<Chitietdonhang> list = new ArrayList<>();
        String query = "SELECT c FROM Chitietdonhang c WHERE c.chitietdonhangPK.idSanPham = :idSanPham";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Chitietdonhang> q = em.createQuery(query, Chitietdonhang.class);
        q.setParameter("idSanPham", idSanPham);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Donhang> getDonHangBetween2Day(Date ngayBatDau, Date ngayKetThuc){
        List<Donhang> list = new ArrayList<>();
        String query = "SELECT d FROM Donhang d "
                + "WHERE d.ngayDat BETWEEN :ngayBatDau AND :ngayKetThuc";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Donhang> q = em.createQuery(query, Donhang.class);
        q.setParameter("ngayBatDau", ngayBatDau);
        q.setParameter("ngayKetThuc", ngayKetThuc);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public void updateDonHang(Donhang donhang) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(donhang);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
