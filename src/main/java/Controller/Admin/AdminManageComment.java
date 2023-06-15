/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.AdminCommentDAO;
import Entity.Account;
import Entity.Danhgia;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "AdminManageComment", urlPatterns = {"/AdminManageComment"})
public class AdminManageComment extends HttpServlet {

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

        if (adminAccount == null) {
            response.sendRedirect("AdminLogin");
        } else {
            AdminCommentDAO com = new AdminCommentDAO();
            List<Danhgia> listComment = new ArrayList<>();

            // Khai báo idComment cho chức năng xóa từng comment một
            String idComment = request.getParameter("idComment");
            // Nếu idComment mà khác null -> request từ DeleteCommentForm -> thực hiện delete dựa trên idComment
            if (idComment != null) {
                com.deleteCommentByID(idComment);
            }

            //// Chức năng tìm kiếm hoặc xóa comment giữa 2 mốc thời gian
            String findOrDelete = request.getParameter("findOrDelete");
            if (findOrDelete == null) {
                findOrDelete = "find";
            }
            Date date1 = new Date();
            Date date2 = new Date();
            String strDate1 = request.getParameter("date1");
            String strDate2 = request.getParameter("date2");

            try {
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate1);
                date2 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate2);
                boolean isDelete = findOrDelete.equals("delete");
                if (isDelete) {
                    com.deleteCommentBetweenTwoDate(date1, date2);
                }
                listComment = com.filterCommentBetweenTwoDate(date1, date2);
            } catch (Exception ex) {
                listComment = com.getCommentList();
            }

            // Chuẩn bị data để vẽ biểu đồ
            String dateFromChart = request.getParameter("dateForChart");
            int month;
            int year;
            String dateToChart;
            // Kiểm tra dữ liệu tháng năm vừa lấy được từ request để vẽ biểu đồ
            if (dateFromChart == null) {
                // Nếu dataFromChart == null -> lấy tháng và năm hiện tại để vẽ biểu đồ
                // Cập nhật lại ngày tháng hiện tại vào biến dateToChart để setAttribute 
                LocalDate current = LocalDate.now();
                month = current.getMonthValue();
                year = current.getYear();
                dateToChart = (new StringBuilder()).append(month).append("-").append(year).toString();
            } else {
                // Nếu dateFromChart có dữ liệu. Cập nhật các biến ngày tháng dựa trên dữ liệu của dateFromChart
                String[] temp = dateFromChart.split("-");
                month = Integer.parseInt(temp[0]);
                year = Integer.parseInt(temp[1]);
                dateToChart = dateFromChart;
            }
            // Cập nhật dữ liệu cho các biến cần thiết để setAttribute
            String dataForBarChart = com.countCommentByMonth(month, year);
            List<String> dataForLineChart = com.topUserMostComment(month, year);
            List<String> listUserNameForLineChart = com.getTop3UserMostComment(month, year);

            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("dataForBarChart", dataForBarChart);
            request.setAttribute("date1", strDate1);
            request.setAttribute("date2", strDate2);
            request.setAttribute("dateForChart", dateToChart);
            request.setAttribute("listUsernameForLineChart", listUserNameForLineChart);
            request.setAttribute("dataForLineChart", dataForLineChart);
            request.setAttribute("listComment", listComment);
            request.getRequestDispatcher("Admin/admin-manage-comment.jsp").forward(request, response);
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
