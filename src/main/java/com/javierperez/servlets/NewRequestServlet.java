package com.javierperez.servlets;

import com.javierperez.dataaccess.DataAccess;
import com.javierperez.workplace.Employee;
import com.javierperez.workplace.ExpenseRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "NewRequestServlet", urlPatterns = { "/html/newrequest" })
public class NewRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("id", "" + req.getParameter("id"));
        c.setHttpOnly(false);
        c.setPath("http://localhost:8080/html");
        resp.addCookie(c);
        resp.sendRedirect("/html/newrequest.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("id", "" + req.getParameter("id"));
        c.setHttpOnly(false);
        c.setPath("http://localhost:8080/html");
        resp.addCookie(c);

        DataAccess dataAccess = DataAccess.getInstance();
        ExpenseRequest request = new ExpenseRequest(LocalDate.now().toString(), Double.parseDouble(req.getParameter("amount")), req.getParameter("description"), "PENDING", Integer.parseInt(req.getParameter("id")), "-", "");
        dataAccess.SaveEntity(request);

        Employee employee = dataAccess.GetEmployeeById(Integer.parseInt(req.getParameter("id")));

        if (employee.getJobPosition().equals("Manager"))
            resp.sendRedirect("/html/manager.html");
        else
            resp.sendRedirect("/html/employee.html");
    }
}
