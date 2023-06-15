/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Entity.Loaihang;
import Entity.Nhomhang;
import DAO.SanPhamDAO;
import Entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mrtru
 */
@WebServlet(name = "EditLoaiHang", urlPatterns = {"/EditLoaiHang"})
public class EditLoaiHang extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditLoaiHang</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditLoaiHang at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        Account adminAccount = (Account) session.getAttribute("adminAccount");

        if (adminAccount == null) {
            response.sendRedirect("AdminLogin");
        } else {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String idLH = request.getParameter("id");
            SanPhamDAO spDAO = new SanPhamDAO();
            List<Nhomhang> nhomhang = spDAO.getNhomHang();
            Loaihang loaihang = spDAO.getLoaiHangById(idLH);
            request.setAttribute("lsNH", nhomhang);
            request.setAttribute("loaihang", loaihang);
            RequestDispatcher rd = request.getRequestDispatcher("Admin/edit-loaihang.jsp");
            rd.forward(request, response);
        }
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
        HttpSession session = request.getSession();
        Account adminAccount = (Account) session.getAttribute("adminAccount");

        if (adminAccount == null) {
            response.sendRedirect("AdminLogin");
        } else {
            SanPhamDAO spDao = new SanPhamDAO();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String idLH = request.getParameter("id");
            String nameLH = request.getParameter("name");
            String idNH = request.getParameter("group");
            Nhomhang nhomhang = spDao.getNhomHangById(idNH);
            Loaihang loaihang = new Loaihang(idLH, nhomhang, nameLH);
            spDao.updateLoaiHang(loaihang);
            response.sendRedirect("ManageCategory?msg=OK");
        }
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
