/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Entity.Donhang;
import DAO.DonHangDAO;
import Entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "FilterDonHangByDate", urlPatterns = {"/FilterDonHangByDate"})
public class FilterDonHangByDate extends HttpServlet {

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
            out.println("<title>Servlet FilterDonHangByDate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterDonHangByDate at " + request.getContextPath() + "</h1>");
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
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            Date ngayBatDau = java.sql.Date.valueOf(request.getParameter("ngayBatDau"));
            Date ngayKetThuc = java.sql.Date.valueOf(request.getParameter("ngayKetThuc"));
            DonHangDAO dhdao = new DonHangDAO();
            List<Donhang> lsDH = dhdao.getDonHangBetween2Day(ngayBatDau, ngayKetThuc);
            for (Donhang o : lsDH) {
                String tinhTrang = o.getTinhTrang();
                String icon = "fas fa-shipping-fast", color = "red";
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                String ngayDat = formatter.format(o.getNgayDat());
                if (tinhTrang.equalsIgnoreCase("Hoàn Tất")) {
                    icon = "fa fa-check";
                    color = "blue";
                } else if (tinhTrang.equalsIgnoreCase("Đã nhận")) {
                    icon = "fa fas fa-box";
                    color = "green";
                }
                out.println("<tr>\n"
                        + "                                                    <td>" + o.getTaiKhoan() + "</td>\n"
                        + "                                                    <td>" + o.getHoVaTen() + "</td>\n"
                        + "                                                    <td>" + o.getDiaChi() + "</td>\n"
                        + "                                                    <td>" + o.getSdt() + "</td>\n"
                        + "                                                    <td>" + o.getTongThanhToan() + "</td>\n"
                        + "                                                    <td>" + o.getThanhToan() + "</td>\n"
                        + "                                                    <td>\n"
                        + "                                                                <a>\n"
                        + "                                                                    <i class=\"" + icon + "\" style=\"color:" + color + "\"></i>\n"
                        + "                                                                </a>\n"
                        + "                                                    </td>\n"
                        + "                                                    <td>" + ngayDat + "</td>\n"
                        + "                                                    <td>" + o.getChuThich() + "</td>\n"
                        + "                                                    <td>\n"
                        + "                                                        <a type=\"button\" id=\"" + o.getMaDonHang() + "\" class=\"details tm-product-delete-link\">\n"
                        + "                                                            <i class=\"fas fa-eye\" style=\"color:cyan\"></i>\n"
                        + "                                                        </a>\n"
                        + "                                                    </td>\n"
                        + "                                                    <td>\n"
                        + "                                                        <a type=\"button\" id=\"" + o.getMaDonHang() + "\" class=\"confirm tm-product-delete-link\">\n"
                        + "                                                            <i class=\"fas fa-check\" style=\"color:cyan\"></i>\n"
                        + "                                                        </a>\n"
                        + "                                                    </td>\n"
                        + "                                                </tr>");
            }
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
