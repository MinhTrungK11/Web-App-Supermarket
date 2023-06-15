/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import Entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ResetPassword", urlPatterns = {"/resetpassword"})
public class ResetPassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession();
        if (session.getAttribute("authcode") == null) {
            response.sendRedirect("/forgotpassword");
        } else {
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("authcode");
        String code = request.getParameter("code");
        String passwordnew = request.getParameter("passwordnew");
        String reppasswordnew = request.getParameter("reppasswordnew");

        if (passwordnew.equals(reppasswordnew)) {
            if (code != null && code.equals(acc.getCode())) {
                AccountDAO dao = new AccountDAO();
                acc.setPassword(dao.MD5(passwordnew));
                dao.UpdateAccount(acc);
                if (dao.Login(acc.getUsername(), acc.getPassword()) != null) {
                    session.setAttribute("acc", acc);
                    session.removeAttribute("authcode");
                }
                response.sendRedirect("home");
            } else {
                request.setAttribute("mess", "Mã xác minh không hơp lệ");
                request.setAttribute("code", code);
                request.setAttribute("passwordnew", passwordnew);
                request.setAttribute("reppasswordnew", reppasswordnew);
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mess", "Mật khẩu mới không khớp");
            request.setAttribute("code", code);
            request.setAttribute("passwordnew", passwordnew);
            request.setAttribute("reppasswordnew", reppasswordnew);
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
