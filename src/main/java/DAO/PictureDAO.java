/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.PicSp;
import Entity.Sanpham;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 *
 * @author mrtru
 */
public class PictureDAO {
    public List<PicSp> getPicByIdSanPham(int idSanpham){
        List<PicSp> list = new ArrayList<>();
        String query = "SELECT p FROM Sanpham s JOIN s.picSpCollection p WHERE s.idSanPham=:idSanpham";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<PicSp> q = em.createQuery(query,PicSp.class);
        q.setParameter("idSanpham", idSanpham);
        try {
            list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public PicSp getPicById(int id_pc){
        String query = "SELECT p FROM PicSp p WHERE p.idPc=:id_pc";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<PicSp> q = em.createQuery(query, PicSp.class);
        q.setParameter("id_pc", id_pc);
        try {
            PicSp item = q.getSingleResult();
            return item;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public void addPicture(PicSp pc){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            em.persist(pc);
            trans.commit();
        }catch(Exception e){
            System.out.println(e);
            trans.rollback();
        }finally{
            em.close();
        }
    }
    public void deletePicture(int id_pc){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            PicSp s = em.find(PicSp.class, id_pc);
            em.remove(s);
            trans.commit();
        } catch (Exception e) {
            System.out.println("e");
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public boolean checkNamePic(String picName)
    {
        String query = "SELECT p FROM PicSp p WHERE p.picture=:picName";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<PicSp> q = em.createQuery(query, PicSp.class);
        q.setParameter("picName", picName);
        try {
            PicSp item = q.getSingleResult();
            if(item != null)
                return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
        return false;
    }
}
