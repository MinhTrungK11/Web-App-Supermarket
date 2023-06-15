/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Account;
import java.nio.charset.StandardCharsets;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author nguye
 */


public class AccountDAO {
    public Account UsernameIsExist(String username){
        String query = "SELECT a FROM Account a WHERE a.username = :username";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Account> q = em.createQuery(query, Account.class);
        q.setParameter("username", username);
        try {
            Account acc = q.getSingleResult();
            return acc;
                
        } catch (Exception e) {
            return null;
        }
        finally {
            em.close();
        }
    }
    
    public Account EmailIsExist(String email)
    {
        String query = "SELECT a FROM Account a WHERE a.email = :email";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Account> q = em.createQuery(query, Account.class);
        q.setParameter("email",email);
        try {
            Account acc = q.getSingleResult();
            return acc;
        } catch (Exception e) {
            return null;
        }
        finally {
            em.close();
        }
    }
    
    public Account Login(String username, String password){
    String query = "SELECT a FROM Account a WHERE a.username = :username And a.password = :password";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TypedQuery<Account> q = em.createQuery(query, Account.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        try {
            Account acc = q.getSingleResult();
            return acc;
        } catch (Exception e) {
            return null;
        }
        finally {
            em.close();
        }
    }
    
    public void Signup(Account acc){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(acc);
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
    
    public String MD5(String str){
        MessageDigest md;
        String result = "";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            BigInteger bi = new BigInteger(1, md.digest());
            result = bi.toString(16);
	} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
	}
        return result;
    }
    
    public void UpdateAccount(Account acc)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(acc);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
