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

@WebServlet(name = "EditProfile", urlPatterns = {"/editprofile"})
public class EditProfile extends HttpServlet {

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
            request.setAttribute("address",acc.getAddress());
            request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String Name = request.getParameter("name");
        String PhoneNumber = request.getParameter("phonenumber");
        String Address = request.getParameter("address");

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            if (Name.equals(acc.getName()) && PhoneNumber.equals(acc.getPhonenumber()) && Address.equals(acc.getAddress())) {
                request.setAttribute("mess", "Cập nhật không thành công");
                request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
            } else {
                AccountDAO dao = new AccountDAO();
                acc.setAddress(dao.EncodingString(Address));
                acc.setPhonenumber(PhoneNumber);
                acc.setName(dao.EncodingString(Name));
                dao.UpdateAccount(acc);
                response.sendRedirect("profile");
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
