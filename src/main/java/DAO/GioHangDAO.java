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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author letua
 */
public class GioHangDAO {
    public List<Giohang> getSPGioHang(String taiKhoan) {
        List<Giohang> list = new ArrayList<>();
        String query = "SELECT g FROM Giohang g WHERE g.giohangPK.tenTaiKhoan = :taiKhoan";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Giohang> q = em.createQuery(query, Giohang.class);
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
    public Giohang get1SP(String tenTaiKhoan, int idSanPham) {
        String query = "SELECT g FROM Giohang g "
                + "WHERE g.giohangPK.tenTaiKhoan=:tenTaiKhoan AND g.giohangPK.idSanPham=:idSanPham";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Giohang> q = em.createQuery(query, Giohang.class);
        q.setParameter("tenTaiKhoan", tenTaiKhoan);
        q.setParameter("idSanPham", idSanPham);
        try {
            Giohang item = q.getSingleResult();
            return item;
        } catch (Exception e) {
            return null;
        }
        finally {
            em.close();
        }
    }
    
    public boolean checkSanPhamGioHang(String tenTaiKhoan, int idSanPham)
    {
        Giohang gh = get1SP(tenTaiKhoan, idSanPham);
        return gh != null;
    }
    
    public void themSPVaoGio(Giohang gh)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(gh);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public void capNhatSPGioHang(Giohang gh)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(gh);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void xoaSPGioHang(String tenTaiKhoan, int idSanPham)
    {
        List<Giohang> list = getSPGioHang(tenTaiKhoan);
        Giohang sp = null;
        for(Giohang g:list)
        {
            if(g.getGiohangPK().getTenTaiKhoan().equals(tenTaiKhoan) && g.getGiohangPK().getIdSanPham()==idSanPham)
            {
                sp=g;
                break;
            }
        }
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(sp));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    public Voucher getVoucher(String voucher)
    {
        String query = "Select v From Voucher v Where v.maVoucher=:voucher";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Voucher> q = em.createQuery(query, Voucher.class);
        q.setParameter("voucher", voucher);
        try {
            Voucher percent = q.getSingleResult();
            if(percent != null)
                return percent;
        } catch (NoResultException e) {
        } finally {
            em.close();
        }
        return null;
    }
    
    public void capNhatSLVoucher(String maVoucher)
    {
        List<Voucher> list = getTatCaVoucher();
        Voucher voucher = new Voucher();
        for(Voucher v:list)
        {
            if(v.getMaVoucher().equals(maVoucher))
            {
                int sl;
                if(v.getSoLuong()==1)
                    sl = v.getSoLuong();
                else
                    sl = v.getSoLuong()-1;
                voucher.setMaVoucher(maVoucher);
                voucher.setPhanTram(v.getPhanTram());
                voucher.setSoLuong(sl);
                break;
            }
        }
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            if(voucher.getSoLuong()>1)
                em.merge(voucher);
            else
                em.remove(em.merge(voucher));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }

    }
    public List<Voucher> getTatCaVoucher() {
        List<Voucher> list = new ArrayList<>();
        String query = "SELECT v FROM Voucher v";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
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
    public int getSoLuongTrongGio(String taiKhoan)
    {
        List<Giohang> listSP = getSPGioHang(taiKhoan);
        return listSP.size();
    }
    public void datHang(String taiKhoan, String hoVaTen, String diaChi, String sdt, String email, float tongThanhToan, String thanhToan)
    {
        //Thêm vào chi tiết đơn hàng
        List<Giohang> listSP = getSPGioHang(taiKhoan);
        SanPhamDAO daosp = new SanPhamDAO();
        List<Chitietdonhang> listDT = daosp.getChiTietDonHang();
        int maDonHang;
        if(listDT.size()>=1)
            maDonHang=2;
        else
            maDonHang=1;
        for(int i=1;i<listDT.size();i++)
        {
            if(listDT.get(i).getChitietdonhangPK().getMaDonHang()!=listDT.get(i-1).getChitietdonhangPK().getMaDonHang())
                maDonHang=maDonHang+1;
        }
        for(int i=0;i<listSP.size();i++)
        {
            Chitietdonhang cdh = new Chitietdonhang();
            ChitietdonhangPK cdhpk = new ChitietdonhangPK(maDonHang,listSP.get(i).getGiohangPK().getIdSanPham());
            cdh.setChitietdonhangPK(cdhpk);
            cdh.setSoLuong(listSP.get(i).getSoLuong());
            cdh.setThanhTien(listSP.get(i).getGia()*listSP.get(i).getSoLuong());
            themChiTietDH(cdh);
        }
        //thêm vào đơn hàng
        Date date = new Date();
        Donhang dh = new Donhang(maDonHang,taiKhoan,hoVaTen,diaChi,sdt,email,tongThanhToan,thanhToan, "Đang giao",date);
        themDonHang(dh);
    }
    
    public void themChiTietDH(Chitietdonhang cdh)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(cdh);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void themDonHang(Donhang dh)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(dh);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public String EncodingString(String text)
    {
        byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
        text = new String(bytes, StandardCharsets.UTF_8);
        return text;
    }
}
