/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.FilterHomeDAO;
import DAO.PictureDAO;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.Loaihang;
import Entity.Nhomhang;
import Entity.Sanpham;
import Entity.PicSp;
import constants.GlobalConstants;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import util.FileUtil;

/**
 *
 * @author mrtru
 */
@MultipartConfig
@WebServlet(name = "EditProduct", urlPatterns = {"/EditProduct"})
public class EditProduct extends HttpServlet {

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
            SanPhamDAO spDaO = new SanPhamDAO();
            PictureDAO pcDao = new PictureDAO();
            List<Nhomhang> lsNH = spDaO.getNhomHang();
            int id_sp = Integer.parseInt(request.getParameter("pid"));
            Sanpham sp = spDaO.get1SP(id_sp);
            List<Loaihang> lsLH = spDaO.getLoaiHangByIdNhomHang(sp.getIdNhomHang().getIdNhomHang());
            List<PicSp> image = pcDao.getPicByIdSanPham(id_sp);
            request.setAttribute("product", sp);
            request.setAttribute("image", image);
            request.setAttribute("lsLH", lsLH);
            request.setAttribute("lsNH", lsNH);
            RequestDispatcher rd = request.getRequestDispatcher("Admin/edit-product.jsp");
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
            PictureDAO pcDao = new PictureDAO();
            FilterHomeDAO ftDao = new FilterHomeDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int qty = Integer.parseInt(request.getParameter("qty"));
            double import_price = Double.parseDouble(request.getParameter("import_price"));
            double price = Double.parseDouble(request.getParameter("price"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            String description = request.getParameter("description");
            Date expiry = Date.valueOf(request.getParameter("expiry"));
            String category = request.getParameter("category");
            String group = request.getParameter("group");

            Nhomhang nh = ftDao.get1NH(group);
            Loaihang lh = ftDao.get1LH(category);
            Sanpham sp = new Sanpham(id, nh, lh, name, qty, import_price, price, expiry, description, discount);
            spDao.updateSanPham(sp);
            //Upload multi images
            String fileName = "";
            List<Part> fileParts;
            List<PicSp> image = pcDao.getPicByIdSanPham(id);
            fileParts = request.getParts().stream().filter(part -> "picture".equals(part.getName())).collect(Collectors.toList());
            int c = image.size();
            for (Part filePart : fileParts) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (!"".equals(fileName)) {
                    fileName = FileUtil.rename(String.valueOf(id), c);
                    c = c + 1;
                    // lấy đường dẫn thực của dự án
                    String dirPath = request.getServletContext().getRealPath("") + GlobalConstants.DIR_UPLOAD;
                    File saveDir = new File(dirPath);
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }
                    String filePath = dirPath + File.separator + fileName;
                    filePart.write(filePath);
                    if (pcDao.checkNamePic(fileName) == false) {
                        PicSp pic = new PicSp(fileName, sp);
                        pcDao.addPicture(pic);
                    }

                }
            }
            response.sendRedirect("ManageProduct?msg=OK");
        }
    }

}
