/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GioHangDAO;
import DAO.PagingDAO;
import DAO.LatestProductDAO;
import DAO.SanPhamDAO;
import DAO.PagingDAO;
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
 * @author Trung
 */
@WebServlet(name = "ShopControll", urlPatterns = {"/shopgrid"})
public class ShopControll extends HttpServlet {

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
        LatestProductDAO daoLP = new LatestProductDAO();
        GioHangDAO daogh = new GioHangDAO();
        PagingDAO count = new PagingDAO();
        
        int slTrongGio = 0;
        if (account != null) {
            slTrongGio = daogh.getSoLuongTrongGio(account.getUsername());
        }
        List<Sanpham> listSP = count.getSanPhamindex1();
        List<Sanpham>  listlast = daoLP.getLatestSP();
        List<Sanpham> listgiamgia = daoLP.getSPGiamGia();
        List<Nhomhang> listNH = dao.getNhomHang();
        List<Loaihang> listLH = dao.getLoaiHang();
        
        request.setAttribute("ListSP", listSP);
        request.setAttribute("ListTop", listlast);
        request.setAttribute("ListGG", listgiamgia);
        
        int sl = count.getCount();
        request.setAttribute("Endtotal", sl);
        request.setAttribute("slTrongGio", slTrongGio);
         request.setAttribute("ListNH", listNH);
        request.setAttribute("ListLH", listLH);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
