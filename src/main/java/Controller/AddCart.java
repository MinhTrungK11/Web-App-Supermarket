/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GioHangDAO;
import Entity.Account;
import Entity.Giohang;
import Entity.GiohangPK;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddCart", urlPatterns = {"/addcart"})
public class AddCart extends HttpServlet {

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

        if (account==null)
        {
            response.sendRedirect("/login");
        }
        else
        {
            int idSP = Integer.parseInt(request.getParameter("idSP"));
            float gia = Float.parseFloat(request.getParameter("gia"));
            int sl = 1;
            String sl_detail = request.getParameter("soluong");
            if(sl_detail!=null)
                sl=Integer.parseInt(sl_detail);
            GioHangDAO dao = new GioHangDAO();

            GiohangPK ghpk = new GiohangPK(account.getUsername(),idSP);

            Giohang gh = new Giohang(ghpk,sl,gia);

            if (dao.checkSanPhamGioHang(account.getUsername(), idSP))
            {
                gh.setSoLuong(dao.get1SP(account.getUsername(),idSP).getSoLuong()+sl);
                dao.capNhatSPGioHang(gh);
            }
            else
            { 
                dao.themSPVaoGio(gh);
            }
            response.sendRedirect(request.getHeader("Referer"));
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
