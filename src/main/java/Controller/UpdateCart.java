/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GioHangDAO;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.Giohang;
import Entity.GiohangPK;
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
@WebServlet(name = "UpdateCart", urlPatterns = {"/updatecart"})
public class UpdateCart extends HttpServlet {

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
        String[] inputsl = request.getParameterValues("quantity");
        
        GioHangDAO dao = new GioHangDAO();
        SanPhamDAO daosp = new SanPhamDAO();
        List<Giohang> listSP = dao.getSPGioHang(account.getUsername());
        
        for(int i=0;i<listSP.size();i++)
        {
            if(Integer.parseInt(inputsl[i])==0)
                dao.xoaSPGioHang(listSP.get(i).getGiohangPK().getTenTaiKhoan(), listSP.get(i).getGiohangPK().getIdSanPham());
            else
            {
                GiohangPK ghpk = new GiohangPK(listSP.get(i).getGiohangPK().getTenTaiKhoan(),listSP.get(i).getGiohangPK().getIdSanPham());
                int sl=Integer.parseInt(inputsl[i]);
                int maxsl = daosp.get1SP(listSP.get(i).getGiohangPK().getIdSanPham()).getSoLuong();
                if(sl > maxsl)
                    sl = maxsl;
                Giohang gh = new Giohang(ghpk,sl,listSP.get(i).getGia());
                dao.capNhatSPGioHang(gh);
            }
        }
        response.sendRedirect("/cart");
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
