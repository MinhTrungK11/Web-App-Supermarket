/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LatestProductDAO;
import DAO.FilterHomeDAO;
import DAO.GioHangDAO;
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
@WebServlet(name = "CategoryControl", urlPatterns = {"/category"})
public class CategoryControl extends HttpServlet {

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
        FilterHomeDAO dao = new FilterHomeDAO();
        SanPhamDAO daoSP = new SanPhamDAO();
        LatestProductDAO daoLP = new LatestProductDAO();

        String cateID = request.getParameter("idLoaiHang");
        String tenLH = request.getParameter("tenLH");
        
        List<Nhomhang> listNH = daoSP.getNhomHang();
        List<Loaihang> listLH = daoSP.getLoaiHang();
        Loaihang lh = dao.get1LH(cateID);
        List<Sanpham> listSP = dao.getFilterCategory(lh);
        
        request.setAttribute("ListNH", listNH);
        request.setAttribute("ListLH", listLH);
        request.setAttribute("ListSP", listSP);
        request.setAttribute("tenLH", tenLH);
        request.getRequestDispatcher("filterresults.jsp").forward(request, response);
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
