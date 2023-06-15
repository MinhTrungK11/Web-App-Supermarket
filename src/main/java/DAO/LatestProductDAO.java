/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Sanpham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 *
 * @author Trung
 */
public class LatestProductDAO {
    public List<Sanpham> getLatestSP() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "ORDER BY s.idSanPham DESC ";
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
    public List<Sanpham> getTopRatedSanPham() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s inner join Danhgia d where s.idSanPham = d.idSanPham.idSanPham "
                + "GROUP BY s.idSanPham ORDER BY count(s.idSanPham) DESC";
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
    public List<Sanpham> getSPGiamGia() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.giamGia > 0 order by s.giamGia/s.giaBan desc ";
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
   
    public static void main(String[] args) {
        LatestProductDAO dao = new LatestProductDAO();
        List<Sanpham> list = dao.getTopRatedSanPham();
        for(Sanpham i:list)
            System.out.println(i);
    }
   
}
