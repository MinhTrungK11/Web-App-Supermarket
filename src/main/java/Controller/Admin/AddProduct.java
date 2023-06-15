/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.FilterHomeDAO;
import DAO.PictureDAO;
import Entity.Loaihang;
import Entity.Nhomhang;
import Entity.Sanpham;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.PicSp;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.FileUtil;
import constants.GlobalConstants;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mrtru
 */
@MultipartConfig
@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final File filename = null;

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
            SanPhamDAO spDAO = new SanPhamDAO();
            List<Nhomhang> listNH = spDAO.getNhomHang();
            List<Loaihang> listLH = spDAO.getLoaiHang();
            request.setAttribute("lsLH", listLH);
            request.setAttribute("lsNH", listNH);
            List<Sanpham> lsSP = spDAO.getTatCaSanPham();
            int addId = lsSP.size() + 1;
            request.setAttribute("idSP", addId);
            RequestDispatcher rd = request.getRequestDispatcher("Admin/add-product.jsp");
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
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            SanPhamDAO spDao = new SanPhamDAO();
            FilterHomeDAO ftDao = new FilterHomeDAO();
            PictureDAO pcDAO = new PictureDAO();
            String name = request.getParameter("name");
            int qty = Integer.parseInt(request.getParameter("qty"));
            String description = request.getParameter("description");
            double imPrice = Double.parseDouble(request.getParameter("imPrice"));
            double price = Double.parseDouble(request.getParameter("price"));
            Date expiry = Date.valueOf(request.getParameter("expiry"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            String groupId = request.getParameter("group");
            String categoryId = request.getParameter("category");
            Nhomhang nh = ftDao.get1NH(groupId);
            Loaihang lh = ftDao.get1LH(categoryId);

            List<Sanpham> lsSP = spDao.getTatCaSanPham();
            int addId = lsSP.size() + 1;
            Sanpham sp = new Sanpham(addId, nh, lh, name, qty, imPrice, price, expiry, description, discount);
            spDao.insertSanPham(sp);
            // upload multiple images
            String fileName = "";
            List<Part> fileParts;
            fileParts = request.getParts().stream().filter(part -> "picture".equals(part.getName())).collect(Collectors.toList());
            int c = 0;
            for (Part filePart : fileParts) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (!"".equals(fileName)) {
                    fileName = FileUtil.rename(String.valueOf(addId), c);
                    c = c + 1;
                    // lấy đường dẫn thực của dự án
                    String dirPath = request.getServletContext().getRealPath("") + GlobalConstants.DIR_UPLOAD;
                    File saveDir = new File(dirPath);
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }
                    String filePath = dirPath + File.separator + fileName;
                    filePart.write(filePath);
                    PicSp pic = new PicSp(fileName, sp);
                    pcDAO.addPicture(pic);
                }
            }
            response.sendRedirect("ManageProduct?msg=OK");
        }
    }
}
