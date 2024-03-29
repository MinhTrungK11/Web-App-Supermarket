/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.FilterHomeDAO;
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
@WebServlet(name = "GroupSPControll", urlPatterns = {"/groupsp"})
public class GroupSPControll extends HttpServlet {

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
        
        
        FilterHomeDAO daoGroup = new FilterHomeDAO();
        SanPhamDAO daoSP = new SanPhamDAO();
        String cateID = request.getParameter("idNhomHang");
        String tenNH = request.getParameter("tenNH");
        
        List<Nhomhang> listNH = daoSP.getNhomHang();
        List<Loaihang> listLH = daoSP.getLoaiHang();
        Nhomhang nh = daoGroup.get1NH(cateID);
        List<Sanpham> listSP = daoGroup.getGroupSP(nh);
        
        request.setAttribute("ListNH", listNH);
        request.setAttribute("ListLH", listLH);
        request.setAttribute("ListSP", listSP);
        request.setAttribute("tenNH", tenNH);
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
