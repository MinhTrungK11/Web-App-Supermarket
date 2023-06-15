/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Account;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ACER
 */
public class AdminAccountDAO {

    public List<Account> getAllAccount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT a From Account a";
        TypedQuery<Account> q = em.createQuery(query, Account.class);

        try {
            List<Account> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Account> filterAccountByUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT a FROM Account a WHERE a.username LIKE CONCAT('%',:userName,'%')";
        TypedQuery<Account> q = em.createQuery(query, Account.class);
        q.setParameter("userName", userName);

        try {
            List<Account> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Account findAccountByUsername(String userName) {
        if (userName == null || userName.isEmpty()) {
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT a FROM Account a WHERE a.username = :username";
        TypedQuery<Account> q = em.createQuery(query, Account.class);
        q.setParameter("username", userName);

        try {
            Account account = q.getSingleResult();
            return account;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Account findAccountByUsernamePassRole(String username, String password, String role) {
        Account account = findAccountByUsername(username);
        if (account != null) {
            String passwordAccount = account.getPassword();
            String roleAccount = account.getRole();
            String needCheckPassword = MD5(password);
            if (!passwordAccount.equals(needCheckPassword) || !roleAccount.equals(role)) {
                return null;
            }
        }
        return account;
    }

    public void updateAccount(String username, String email, String address, String phone, String password) {
        String query = "UPDATE Account a SET a.password = :password, a.phonenumber = :phone, a.email = :email, a.address = :address WHERE a.username = :username";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Query q = em.createQuery(query);

        q.setParameter("username", username);
        q.setParameter("password", password);
        q.setParameter("phone", phone);
        q.setParameter("email", email);
        q.setParameter("address", address);

        try {
            trans.begin();
            q.executeUpdate();
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void updatePassword(String username, String password) {
        String query = "UPDATE Account a SET a.password = :password WHERE a.username = :username";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Query q = em.createQuery(query);
        password = MD5(password);

        q.setParameter("password", password);
        q.setParameter("username", username);

        try {
            trans.begin();
            q.executeUpdate();
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void updateInformation(String username, String name, String email, String address, String phone) {
        String query = "UPDATE Account a SET a.name = :name, a.phonenumber = :phone, a.email = :email, a.address = :address WHERE a.username = :username";
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Query q = em.createQuery(query);

        q.setParameter("username", username);
        q.setParameter("phone", phone);
        q.setParameter("email", email);
        q.setParameter("address", address);
        q.setParameter("name", name);

        try {
            trans.begin();
            q.executeUpdate();
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void deleteAccount(String username) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Account account = findAccountByUsername(username);

        try {
            trans.begin();
            em.remove(em.merge(account));
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public String EncodingString(String text) {
        byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
        text = new String(bytes, StandardCharsets.UTF_8);
        return text;
    }

    public String MD5(String str) {
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

}
