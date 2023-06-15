/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.VoucherDAO;
import Entity.Account;
import Entity.Voucher;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditVoucher", urlPatterns = {"/EditVoucher"})
public class EditVoucher extends HttpServlet {

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
            out.println("<title>Servlet EditVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditVoucher at " + request.getContextPath() + "</h1>");
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
            VoucherDAO voucherDAO = new VoucherDAO();
            String pid = request.getParameter("pid");
            Voucher voucher = voucherDAO.getVoucherById(pid);
            request.setAttribute("voucher", voucher);
            RequestDispatcher rd = request.getRequestDispatcher("Admin/edit-voucher.jsp");
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
            VoucherDAO voucherDAO = new VoucherDAO();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String idVoucher = request.getParameter("id");
            int discount = Integer.parseInt(request.getParameter("discount"));
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            Voucher voucher = new Voucher(idVoucher, discount, soLuong);
            voucherDAO.updateVoucher(voucher);
            response.sendRedirect("ManageVoucher?msg=OK");
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
