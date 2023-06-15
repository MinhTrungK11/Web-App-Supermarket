
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
import javax.swing.text.DefaultEditorKit;

/**
 *
 * @author ACER
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Nếu trong session đã có admin đăng nhập rồi -> chuyển tới trang dashboard
        if (adminAccount != null) {
            response.sendRedirect("AdminDashboard");
        } 
        // Nếu 2 cái giá trị tk và password = null -> Trong request không có 2 thuộc tính này -> request lại trang login
        else if (username == null && password == null) {
            request.getRequestDispatcher("Admin/admin-login.jsp").forward(request, response);
        } 
        // Đủ điều kiện =>  Check login
        else {
            AdminAccountDAO tk = new AdminAccountDAO();
            Account account = tk.findAccountByUsernamePassRole(username, password, "admin");
            if (account != null) {
                session.setAttribute("adminAccount", account);
                request.getRequestDispatcher("AdminDashboard").forward(request, response);
            } else {
                request.setAttribute("username", username);
                request.setAttribute("mess", "Tên đăng nhập hoặc mật khẩu không đúng");
                request.getRequestDispatcher("Admin/admin-login.jsp").forward(request, response);
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
