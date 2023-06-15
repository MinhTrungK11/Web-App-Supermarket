/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Chitietdonhang;
import Entity.Danhgia;
import Entity.Loaihang;
import Entity.Nhomhang;
import java.util.List;
import Entity.Sanpham;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author letua
 */
public class SanPhamDAO {

    public List<Sanpham> getTatCaSanPham() {
        List<Sanpham> list = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "Select s FROM Sanpham s";
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

    public List<Nhomhang> getNhomHang() {
        List<Nhomhang> list = new ArrayList<>();
        String query = "Select n from Nhomhang n";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Nhomhang> q = em.createQuery(query, Nhomhang.class);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Loaihang> getLoaiHang() {
        List<Loaihang> list = new ArrayList<>();
        String query = "Select l from Loaihang l";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Loaihang> q = em.createQuery(query, Loaihang.class);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

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

    public Sanpham get1SP(int idSanPham) {
        String query = "SELECT s FROM Sanpham s "
                + "WHERE s.idSanPham=:idSanPham";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setParameter("idSanPham", idSanPham);
        try {
            Sanpham item = q.getSingleResult();
            return item;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Sanpham> getSPTuongTu(Loaihang idlh) {
        List<Sanpham> list = new ArrayList<>();
        String query = "Select s From Sanpham s Where s.idLoaiHoang=:idlh";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setParameter("idlh", idlh);
        try {
            list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void danhGiaSanPham(Danhgia dg) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(dg);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public List<Danhgia> getDanhGia(Sanpham idSanPham) {
        List<Danhgia> list = new ArrayList<>();
        String query = "Select d from Danhgia d Where d.idSanPham=:idSanPham";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Danhgia> q = em.createQuery(query, Danhgia.class);
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

    public void capNhatSLSanPham(int idSP, int soLuong) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "UPDATE Sanpham s set s.soLuong = s.soLuong-:soLuong WHERE s.idSanPham = :idSP";
        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
        q.setParameter("soLuong", soLuong);
        q.setParameter("idSP", idSP);
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
    
    public void insertSanPham(Sanpham sanpham) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(sanpham);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void updateSanPham(Sanpham sanpham) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(sanpham);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void deleteSanPham(int idSanpham) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            Sanpham s = em.find(Sanpham.class, idSanpham);
            em.remove(s);
            trans.commit();
        } catch (Exception e) {
            System.out.println("e");
            trans.rollback();
        } finally {
            em.close();
        }
    }

//    public List<Sanpham> getSanPhambyIdLoaiHang(String idLoaiHoang) {
//        List<Sanpham> list = new ArrayList<>();
//        String query = "SELECT s FROM Sanpham s WHERE s.idLoaiHoang = :idLoaiHoang";
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
//        q.setParameter("idLoaiHoang", idLoaiHoang);
//        try {
//            list = q.getResultList();
//            return list;
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }
//
//    public List<Sanpham> getSanPhambyIdNhomHang(String idNhomHang) {
//        List<Sanpham> list = new ArrayList<>();
//        String query = "SELECT s FROM Sanpham s WHERE s.idNhomHang = :idNhomHang";
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        TypedQuery<Sanpham> q = em.createQuery(query, Sanpham.class);
//        q.setParameter("idNhomHang", idNhomHang);
//        try {
//            list = q.getResultList();
//            return list;
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }

    public void insertNhomHang(Nhomhang nhomhang) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(nhomhang);
            trans.commit();

        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void updateNhomHang(Nhomhang nhomhang) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(nhomhang);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public Nhomhang getNhomHangById(String idNhomHang) {
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

    public void deleteNhomHang(String idNhomHang) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            Nhomhang s = em.find(Nhomhang.class, idNhomHang);
            em.remove(s);
            trans.commit();
        } catch (Exception e) {
            System.out.println("e");
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void insertLoaiHang(Loaihang loaihang) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(loaihang);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void updateLoaiHang(Loaihang loaihang) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(loaihang);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void deleteLoaiHang(String idLoaiHang) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            Loaihang s = em.find(Loaihang.class, idLoaiHang);
            em.remove(s);
            trans.commit();
        } catch (Exception e) {
            System.out.println("e");
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public Loaihang getLoaiHangById(String idLoaiHang) {
        String query = "SELECT n FROM Loaihang n "
                + "WHERE n.idLoaiHang=:idLoaiHang";
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
    public List<Loaihang> getLoaiHangByIdNhomHang(String idNhomHang){
        List<Loaihang> list = new ArrayList<>();
        String query = "SELECT p FROM Nhomhang n JOIN n.loaihangCollection p WHERE n.idNhomHang=:idNhomHang";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Loaihang> q = em.createQuery(query,Loaihang.class);
        q.setParameter("idNhomHang", idNhomHang);
        try {
            list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<Sanpham> getSanPhambyIdLoaiHang(String idLoaiHoang) {
        List<Sanpham> list = new ArrayList<>();
        String query = "SELECT s FROM Loaihang l JOIN l.sanphamCollection s WHERE l.idLoaiHang=:idLoaiHoang";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
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

    public List<Sanpham> getSanPhambyIdNhomHang(String idNhomHang) {
        List<Sanpham> list = new ArrayList<>();
        String query = "SELECT s FROM Nhomhang n JOIN n.sanphamCollection s WHERE n.idNhomHang=:idNhomHang";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
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
}
