/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GioHangDAO;
import DAO.SanPhamDAO;
import Entity.Account;
import Entity.Giohang;
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
 * @author letua
 */
@WebServlet(name = "DoneOrder", urlPatterns = {"/doneorder"})
public class DoneOrder extends HttpServlet {

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
        GioHangDAO dao = new GioHangDAO();
        SanPhamDAO daosp = new SanPhamDAO();
        List<Giohang> listSP = dao.getSPGioHang(account.getUsername());
        String ketQua = null;
        if(request.getParameter("vnp_ResponseCode")==null)
        {
            String hoten = dao.EncodingString(request.getParameter("hovaten"));
            String diachi = dao.EncodingString(request.getParameter("diachi"));
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            String maVoucher = request.getParameter("voucher");
            float tongthanhtoan = Float.parseFloat(request.getParameter("tongthanhtoan"));
            dao.datHang(account.getUsername(),hoten,diachi,sdt,email,tongthanhtoan,"shipcod");
            if(maVoucher!=null)
            {
                dao.capNhatSLVoucher(maVoucher);
            }
            for (Giohang gh:listSP)
            {
                dao.xoaSPGioHang(account.getUsername(), gh.getGiohangPK().getIdSanPham());
                daosp.capNhatSLSanPham(gh.getGiohangPK().getIdSanPham(), gh.getSoLuong());
            }
            ketQua = "ĐẶT HÀNG THÀNH CÔNG!";
        }
        
        else
        {
            if(request.getParameter("vnp_ResponseCode").equals("00"))
            {
                ketQua = "ĐẶT HÀNG THÀNH CÔNG!";
                float tongthanhtoan = Float.parseFloat(request.getParameter("vnp_Amount"))/100;
                List<String> list = (List<String>) session.getAttribute("in4order");
                dao.datHang(account.getUsername(),list.get(0),list.get(1),list.get(2),list.get(3),tongthanhtoan,"vnpay");
                if(list.get(4)!=null)
                {
                    dao.capNhatSLVoucher(list.get(4));
                }
                for (Giohang gh:listSP)
                {
                    dao.xoaSPGioHang(account.getUsername(), gh.getGiohangPK().getIdSanPham());
                    daosp.capNhatSLSanPham(gh.getGiohangPK().getIdSanPham(), gh.getSoLuong());
                }
                session.removeAttribute("in4order");
            }
            else
                ketQua = "ĐẶT HÀNG THẤT BẠI!";
            
        }

        request.setAttribute("ketQua", ketQua);
        request.getRequestDispatcher("donepayment.jsp").forward(request, response);
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
