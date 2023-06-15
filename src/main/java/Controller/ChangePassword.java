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

/**
 *
 * @author nguye
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/changepassword"})
public class ChangePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            request.setAttribute("username", acc.getUsername());
            request.setAttribute("email", acc.getEmail());
            request.setAttribute("name", acc.getName());
            request.setAttribute("phonenumber", acc.getPhonenumber());
            request.setAttribute("address", acc.getAddress());
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            AccountDAO dao = new AccountDAO();
            String password = request.getParameter("passwordold");
            String passwordnew = request.getParameter("passwordnew");
            String reppasswordnew = request.getParameter("reppasswordnew");

            if (passwordnew.equals(reppasswordnew)) {
                if (dao.Login(acc.getUsername(), dao.MD5(password)) != null) {
                    acc.setPassword(dao.MD5(passwordnew));
                    dao.UpdateAccount(acc);
                    response.sendRedirect("/logout");
                } else {
                    request.setAttribute("mess", "vui lòng kiểm tra lại mật khẩu");
                    request.setAttribute("passwordold", password);
                    request.setAttribute("passwordnew", passwordnew);
                    request.setAttribute("reppasswordnew", reppasswordnew);
                    request.getRequestDispatcher("change-password.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Mật khẩu mới không khớp");
                request.setAttribute("passwordold", password);
                request.setAttribute("passwordnew", passwordnew);
                request.setAttribute("reppasswordnew", reppasswordnew);
                request.getRequestDispatcher("change-password.jsp").forward(request, response);
            }

        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
