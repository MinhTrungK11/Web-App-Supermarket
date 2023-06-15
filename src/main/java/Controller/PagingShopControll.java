/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PagingDAO;


import DAO.LatestProductDAO;
import DAO.SanPhamDAO;
import Entity.Loaihang;
import Entity.Nhomhang;
import Entity.Sanpham;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Trung
 */
@WebServlet(name = "PhanTrang", urlPatterns = {"/PhanTrang"})
public class PagingShopControll extends HttpServlet {

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
  
        LatestProductDAO daoLP = new LatestProductDAO();
        PagingDAO count = new PagingDAO();
        int index = Integer.parseInt(request.getParameter("index"));
        
        List<Sanpham> ListSP = count.getSanPham(index);
        List<Sanpham>  listlast = daoLP.getLatestSP();
        List<Sanpham> listgiamgia = daoLP.getSPGiamGia();
        int sl = count.getCount();
        
        
        request.setAttribute("Endtotal", sl);
        request.setAttribute("ListSP", ListSP);
        request.setAttribute("ListTop", listlast);
        request.setAttribute("ListGG", listgiamgia);
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
