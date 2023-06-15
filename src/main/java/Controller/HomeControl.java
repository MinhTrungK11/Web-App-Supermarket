/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.CookieUtils;
import DAO.GioHangDAO;
import DAO.LatestProductDAO;
import DAO.PagingDAO;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.Loaihang;
import Entity.Nhomhang;
import Entity.Sanpham;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author letua
 */
@WebServlet(name = "HomeControl", urlPatterns = {"/home"})
public class HomeControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        SanPhamDAO dao = new SanPhamDAO();
        GioHangDAO daogh = new GioHangDAO();
        LatestProductDAO daoLP = new LatestProductDAO();
        PagingDAO count = new PagingDAO();
        
        List<Sanpham> listSP = dao.getTatCaSanPham();
        List<Nhomhang> listNH = dao.getNhomHang();
        List<Loaihang> listLH = dao.getLoaiHang();
        List<Sanpham>  listlast = daoLP.getLatestSP();
        List<Sanpham> listtop = daoLP.getTopRatedSanPham();
        List<Sanpham> listgiamgia = daoLP.getSPGiamGia();
        
        int slTrongGio = 0;
        if (account != null) {
            slTrongGio = daogh.getSoLuongTrongGio(account.getUsername());
        }
        int sl = count.getCount();
        
        request.setAttribute("ListSP", listSP);
        request.setAttribute("ListNH", listNH);
        request.setAttribute("ListLH", listLH);
        request.setAttribute("slTrongGio", slTrongGio);
        request.setAttribute("List", listlast);
        request.setAttribute("ListTop", listtop);
        request.setAttribute("ListGG", listgiamgia);
        request.setAttribute("Endtotal", sl);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = CookieUtils.get("userC", request);
        String password = CookieUtils.get("passC", request);
        AccountDAO dao = new AccountDAO();
        Account acc = (Account) session.getAttribute("acc");
        if(acc==null && username!=null){
            acc = dao.Login(username, password);
            session.setAttribute("acc", acc);
        }
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
