/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.AdminAccountDAO;
import Entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
@WebServlet(name = "AdminAdjustAccount", urlPatterns = {"/AdminAdjustAccount"})
public class AdminAdjustAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean isNull(String str) {
        return str == null;
    }

    private void setAdminAccountInSession(HttpSession session, Account account) {
        Account adminAccount = (Account) session.getAttribute("adminAccount");
        // Kiểm tra xem 2 username có bằng nhau không
        if (adminAccount.getUsername().equals(account.getUsername())) {
            session.setAttribute("adminAccount", account);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Account adminAccount = (Account) session.getAttribute("adminAccount");

        if (adminAccount == null) {
            response.sendRedirect("AdminLogin");
        } else {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String pass1 = request.getParameter("pass1");
            String pass2 = request.getParameter("pass2");
            String status = "";

            AdminAccountDAO tk = new AdminAccountDAO();
            Account tempAccount = new Account();

            if (username != null && name != null && email != null && address != null && phone != null && pass1 != null && pass2 != null) {
                tempAccount = tk.findAccountByUsername(username);

                // Cập nhật thông tin, không cập nhật mật khẩu. 
                if (pass1.equals("") && pass2.equals("")) {
                    tk.updateInformation(username, name, email, address, phone);
                    status = "CẬP NHẬT THÔNG TIN THÀNH CÔNG";
                    setAdminAccountInSession(session, tempAccount);
                }
                // Mật khẩu nhập lại không khớp
                else if (!pass1.equals(pass2)) {
                    status = "MẬT KHẨU NHẬP LẠI KHÔNG KHỚP";
                } 
                // Cập nhật cả thông tin và mật khẩu
                else {
                    tk.updateInformation(username, name, email, address, phone);
                    tk.updatePassword(username, pass2);
                    status = "CẬP NHẬT THÔNG TIN VÀ MẬT KHẨU THÀNH CÔNG";
                    setAdminAccountInSession(session, tempAccount);
                }
            }

            Account account = tk.findAccountByUsername(username);
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("username", account.getUsername());
            request.setAttribute("name", account.getName());
            request.setAttribute("email", account.getEmail());
            request.setAttribute("address", account.getAddress());
            request.setAttribute("phone", account.getPhonenumber());
            request.setAttribute("status", status);
            request.getRequestDispatcher("Admin/admin-adjust-account.jsp").forward(request, response);
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
