/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ACER
 */
import Entity.Danhgia;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.LinkedHashMap;
import com.google.gson.Gson;
import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.LinkedHashMap;
import com.google.gson.Gson;
import Entity.Account;



public class AdminCommentDAO {

    public String countCommentByMonth(int month, int year) {
        List<Danhgia> list = getAllCommentByMonthYear(month, year);
        return createDataPoints(countCommentByDay(list, month, year));
    }
    
    public List<String> topUserMostComment(int month, int year) {
        List<String> result = new ArrayList<>();
        List<String> top3UserComment = getTop3UserMostComment(month, year);
        String dataPoints;
        String username;

        for (int i = 0; i < 3; i++) {
            username = top3UserComment.get(i);
            if (username.equals("")) {
                result.add(null);
            } else {
                dataPoints = getCommentNumberOfSpecificUser(username, month, year);
                result.add(dataPoints);
            }
        }
        return result;
    }

    private HashMap<Object, Object> sortByKey(HashMap<Object, Object> map) {
        LinkedHashMap<Object, Object> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            list.add(Integer.parseInt(entry.getKey().toString()));
        }
        Collections.sort(list);
        for (int num : list) {
            sortedMap.put(num, findValue(map, num));
        }
        return sortedMap;
    }

    private Object findValue(HashMap<Object, Object> map, int key) {
        int temp;
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            temp = Integer.parseInt(entry.getKey().toString());
            if (temp == key) {
                return entry.getValue();
            }
        }
        return null;
    }

    private int countCommentInDay(int day, List<Danhgia> list) {
        int result = 0;
        for (Danhgia d : list) {
            Date date = d.getThoiGian();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int dayInComment = localDate.getDayOfMonth();
            if (day == dayInComment) {
                result++;
            }
        }
        return result;
    }

    public List<Danhgia> getAllCommentByMonthYear(int month, int year) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Query query = em.createQuery("SELECT d FROM Danhgia d where d.thoiGian BETWEEN :moc1 AND :moc2");

        try {
            query.setParameter("moc1", new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(year) + "-" + String.valueOf(month) + "-01"));
            query.setParameter("moc2", new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(YearMonth.of(year, month).lengthOfMonth())));
            List<Danhgia> list = query.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> getTop3UserMostComment(int month, int year) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT d.taiKhoan FROM Danhgia d WHERE d.thoiGian BETWEEN :moc1 AND :moc2 GROUP BY d.taiKhoan ORDER BY COUNT(d) DESC";
        TypedQuery<Account> q = em.createQuery(query, Account.class);
        List<Account> list;
        List<String> result = new ArrayList<>();

        try {
            q.setParameter("moc1", new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(year) + "-" + String.valueOf(month) + "-01"));
            q.setParameter("moc2", new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(YearMonth.of(year, month).lengthOfMonth())));
            list = q.getResultList();
            for (int i = 0; i < list.size(); i++) {
                result.add(list.get(i).getUsername());
            }
            while (result.size() < 3) {
                result.add("");
            }
            while (result.size() > 3) {
                result.remove(result.size() - 1);
            }
            return result;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public String getCommentNumberOfSpecificUser(String username, int month, int year) {
        List<Danhgia> list;

        // Lấy tất cả các đánh gia của một user trong một khoảng thời gian
        try {
            list = getAllCommentByMonthYear(month, year);
            list.removeIf(d -> (!d.getTaiKhoan().getUsername().equals(username)));
        } catch (Exception ex) {
            return null;
        }
        return createDataPoints(countCommentByDay(list, month, year));
    }

    public String createDataPoints(HashMap<Object, Object> map) {
        Gson gsonObj = new Gson();
        Map<Object, Object> temp = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

        try {
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                temp = new HashMap<Object, Object>();
                temp.put("label", entry.getKey());
                temp.put("y", Integer.parseInt(entry.getValue().toString()));
                list.add(temp);
            }
        } catch (Exception ex) {
            return null;
        }
        return gsonObj.toJson(list);
    }

    private HashMap<Object, Object> countCommentByDay(List<Danhgia> list, int month, int year) {
        HashMap<Object, Object> map = new HashMap<>();
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        int i = 1;

        while (i <= daysInMonth) {
            int commentsInDay = countCommentInDay(i, list);
            map.put(String.valueOf(i), String.valueOf(commentsInDay));
            i++;
        }

        return sortByKey(map);
    }

    public List<Danhgia> getCommentList() {
        List<Danhgia> list;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Query query = em.createQuery("SELECT d FROM Danhgia d");

        try {
            list = query.getResultList();
        } catch (Exception ex) {
            list = null;
        }

        return list;
    }

    public Danhgia findCommentByID(String idComment) {
        if (idComment == null || idComment.isEmpty()) {
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String query = "SELECT d FROM Danhgia d WHERE d.idDanhGia = :idDanhGia";
        TypedQuery<Danhgia> q = em.createQuery(query, Danhgia.class);
        q.setParameter("idDanhGia", Integer.parseInt(idComment));

        try {
            Danhgia comment = q.getSingleResult();
            return comment;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void deleteCommentByID(String idComment) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Danhgia comment = findCommentByID(idComment);

        try {
            trans.begin();
            em.remove(em.merge(comment));
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void deleteCommentBetweenTwoDate(Date date1, Date date2) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            List<Danhgia> list = filterCommentBetweenTwoDate(date1, date2);
            for (Danhgia d: list) {
                em.remove(em.merge(d));
            }
            trans.commit();
        }
        catch (Exception ex) {
            trans.rollback();
        }
    }
    
    public List<Danhgia> filterCommentBetweenTwoDate(Date date1, Date date2) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Danhgia> list = new ArrayList<>();
        Query query = em.createQuery("SELECT d FROM Danhgia d WHERE d.thoiGian BETWEEN :moc1 AND :moc2");
        
        try {
            query.setParameter("moc1", date1);
            query.setParameter("moc2", date2);
            list = query.getResultList();
            return list;
        }
        catch (Exception ex) {
            return  null;
        }
    }
}
