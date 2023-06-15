/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GioHangDAO;
import DAO.OrderDAO;
import Entity.Account;
import Entity.Chitietdonhang;
import Entity.Donhang;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
@WebServlet(name = "Orders", urlPatterns = {"/orders"})
public class Orders extends HttpServlet {

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
        OrderDAO dao = new OrderDAO();
        GioHangDAO daogh = new GioHangDAO();
        
        List<Donhang> listDH_DangGiao= dao.getDonHang("Đang giao", account.getUsername());
        Collections.reverse(listDH_DangGiao);
        ArrayList<List<Chitietdonhang>> listCTDH_DangGiao = new ArrayList<List<Chitietdonhang>>();
        for(int i=0;i<listDH_DangGiao.size();i++)
        {
            listCTDH_DangGiao.add(dao.getChiTietDonHang(listDH_DangGiao.get(i).getMaDonHang()));
        }
        
        List<Donhang> listDH_HoanTat= dao.getDonHang("Hoàn tất", account.getUsername());
        Collections.reverse(listDH_HoanTat);
        ArrayList<List<Chitietdonhang>> listCTDH_HoanTat = new ArrayList<List<Chitietdonhang>>();
        for(int i=0;i<listDH_HoanTat.size();i++)
        {
            listCTDH_HoanTat.add(dao.getChiTietDonHang(listDH_HoanTat.get(i).getMaDonHang()));
        }
        
        request.setAttribute("listDH_DangGiao", listDH_DangGiao);
        request.setAttribute("listCTDH_DangGiao", listCTDH_DangGiao);
        request.setAttribute("listDH_HoanTat", listDH_HoanTat);
        request.setAttribute("listCTDH_HoanTat", listCTDH_HoanTat);
        request.setAttribute("slTrongGio", daogh.getSoLuongTrongGio(account.getUsername()));
        request.getRequestDispatcher("shoping-order.jsp").forward(request, response);
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
