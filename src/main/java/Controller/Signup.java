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
@WebServlet(name = "Signup", urlPatterns = {"/signup"})
public class Signup extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("signup.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        String User = request.getParameter("username");
        String Pass = request.getParameter("password");
        String Email = request.getParameter("email");
        String Name = request.getParameter("name");
        String PhoneNumber = request.getParameter("phonenumber");
        
        AccountDAO dao = new AccountDAO();
        
        if(dao.UsernameIsExist(User)==null && dao.EmailIsExist(Email)==null){
            SendEmailDAO sm = new SendEmailDAO();
            String code = sm.getRandon();
            
            Account acc = new Account(User);
                acc.setPassword(dao.MD5(Pass));
                acc.setEmail(Email);
                acc.setName(dao.EncodingString(Name));
                acc.setPhonenumber(PhoneNumber);
                acc.setCode(code);
                acc.setRole("user");
            
            if(sm.sendEmail(acc)==true){
                HttpSession session = request.getSession();
                session.setAttribute("authcode", acc);
                response.sendRedirect("verify");
            }
        }else{
            if(dao.UsernameIsExist(User)!=null){
                request.setAttribute("mess", "Tên tài khoản đã tồn tại");
            }else if(dao.EmailIsExist(Email)!=null){
                request.setAttribute("mess", "Địa chỉ email đã tồn tại");
            }
            request.setAttribute("username", User);
            request.setAttribute("password",Pass);
            request.setAttribute("email", Email);
            request.setAttribute("name", dao.EncodingString(Name));
            request.setAttribute("phonenumber", PhoneNumber);
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }
          
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
