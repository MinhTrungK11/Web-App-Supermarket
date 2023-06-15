/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Voucher;
import java.util.List;
import Entity.Sanpham;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 *
 * @author mrtru
 */
public class VoucherDAO {
    public List<Voucher>  getVoucher(){
        List<Voucher> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "Select v FROM Voucher v";
        TypedQuery<Voucher> q = em.createQuery(query, Voucher.class);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public void insertVoucher(Voucher voucher){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(voucher);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public void updateVoucher(Voucher voucher){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(voucher);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public void  deleteVoucher(String idVoucher){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            Voucher s = em.find(Voucher.class, idVoucher);
            em.remove(s);
            trans.commit();
        } catch (Exception e) {
            System.out.println("e");
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public Voucher getVoucherById(String maVoucher){
        String query = "SELECT v FROM Voucher v "
                + "WHERE v.maVoucher=:maVoucher";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Voucher> q = em.createQuery(query, Voucher.class);
        q.setParameter("maVoucher", maVoucher);
        try {
            Voucher item = q.getSingleResult();
            return item;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
