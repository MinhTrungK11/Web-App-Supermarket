/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GioHangDAO;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.Danhgia;
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
@WebServlet(name = "DetailProduct", urlPatterns = {"/detailproduct"})
public class DetailProduct extends HttpServlet {

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
        int idSP = Integer.parseInt(request.getParameter("idSP"));
        SanPhamDAO dao = new SanPhamDAO();
        GioHangDAO daogh = new GioHangDAO();
        List<Nhomhang> listNH = dao.getNhomHang();
        List<Loaihang> listLH = dao.getLoaiHang();
        int slTrongGio = 0;
        if(account!=null)
            slTrongGio=daogh.getSoLuongTrongGio(account.getUsername());
        Sanpham sp = dao.get1SP(idSP);
        List<Sanpham> sptt = dao.getSPTuongTu(sp.getIdLoaiHoang());
        List<Danhgia> listDG = dao.getDanhGia(sp);
        int slDG = 0;
        if(listDG != null)
            slDG = listDG.size();
        request.setAttribute("SP", sp);
        request.setAttribute("SPTT", sptt);
        request.setAttribute("ListLH", listLH);
        request.setAttribute("ListNH", listNH);
        request.setAttribute("slTrongGio", slTrongGio);
        request.setAttribute("ListDG", listDG);
        request.setAttribute("slDG", slDG);
        request.getRequestDispatcher("shop-details.jsp").forward(request, response);
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
