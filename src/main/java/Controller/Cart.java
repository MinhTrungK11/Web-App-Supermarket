/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GioHangDAO;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.Giohang;
import Entity.Loaihang;
import Entity.Nhomhang;
import Entity.Voucher;
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
@WebServlet(name = "Cart", urlPatterns = {"/cart"})
public class Cart extends HttpServlet {

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
        if(account!=null)
        {
            String maVoucher = request.getParameter("voucher");
            GioHangDAO dao = new GioHangDAO();
            SanPhamDAO daosp = new SanPhamDAO();
            List<Giohang> listSP = dao.getSPGioHang(account.getUsername());
            List<Nhomhang> listNH = daosp.getNhomHang();
            List<Loaihang> listLH = daosp.getLoaiHang();
            double shipcost = 15000;
            Voucher voucher = dao.getVoucher(maVoucher);
            int slTrongGio = dao.getSoLuongTrongGio(account.getUsername());
            request.setAttribute("ListSP", listSP);
            request.setAttribute("shipcost", shipcost);
            request.setAttribute("voucher", voucher);
            request.setAttribute("ListNH", listNH);
            request.setAttribute("ListLH", listLH);
            request.setAttribute("slTrongGio", slTrongGio);
            request.getRequestDispatcher("shoping-cart.jsp").forward(request, response);
        }
        else
        {
            response.sendRedirect("/login");
        }
        
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
