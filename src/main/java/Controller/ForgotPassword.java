/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.SendEmailDAO;
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
@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgotpassword"})
public class ForgotPassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String email = request.getParameter("email");
        if (email != null) {
            AccountDAO dao = new AccountDAO();
            Account acc = dao.EmailIsExist(email);
            if (acc == null) {
                request.setAttribute("mess", "Địa chỉ email không tồn tại");
                request.setAttribute("email", email);
                request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            } else {
                SendEmailDAO sm = new SendEmailDAO();
                String code = sm.getRandon();
                acc.setCode(code);

                if (sm.sendEmail(acc) == true) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authcode", acc);
                    response.sendRedirect("/resetpassword");
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
