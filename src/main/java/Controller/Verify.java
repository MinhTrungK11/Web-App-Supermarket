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
@WebServlet(name = "Verify", urlPatterns = {"/verify"})
public class Verify extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //request.getRequestDispatcher("verify.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HttpSession session = request.getSession();
        if(session.getAttribute("authcode")==null){
            response.sendRedirect("signup");    
        }else{
            request.getRequestDispatcher("verify.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("authcode");
        String code = request.getParameter("code");
        
        if(code != null && code.equals(acc.getCode())){
            AccountDAO dao = new AccountDAO();
            dao.Signup(acc);
            if(dao.Login(acc.getUsername(), acc.getPassword())!=null){
                session.setAttribute("acc", acc);
                session.removeAttribute("authcode");
            }
            response.sendRedirect("home");                    
        }else{
            request.setAttribute("mess", "Mã xác minh không hơp lệ");
            request.setAttribute("code",code);
            request.getRequestDispatcher("verify.jsp").forward(request,response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
