/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.DonHangDAO;
import Entity.PicSp;
import util.FileUtil;
import DAO.PictureDAO;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.Chitietdonhang;
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
 * @author mrtru
 */
@WebServlet(name = "DeleteProduct", urlPatterns = {"/DeleteProduct"})
public class DeleteProduct extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account adminAccount = (Account) session.getAttribute("adminAccount");

        if (adminAccount == null) {
            response.sendRedirect("AdminLogin");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            int pid = Integer.parseInt(request.getParameter("id"));
            DonHangDAO donHangDAO = new DonHangDAO();
            SanPhamDAO spDao = new SanPhamDAO();
            PictureDAO pcDao = new PictureDAO();
            List<Chitietdonhang> lsCt = donHangDAO.getChiTietDonHangByIdSp(pid);
            if (lsCt.isEmpty()) {
                List<PicSp> picList = pcDao.getPicByIdSanPham(pid);
                if (picList.size() > 0) {
                    int i = 0;
                    for (PicSp objPic : picList) {
                        i++;
                        String fileName = objPic.getPicture();
                        FileUtil.delFile(fileName, request);
                    }
                }
                spDao.deleteSanPham(pid);
                out.println("Xóa thành công");
            } else {
                out.println("Không thể thực hiện xóa vì sản phẩm đã được kinh doanh");
            }
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
