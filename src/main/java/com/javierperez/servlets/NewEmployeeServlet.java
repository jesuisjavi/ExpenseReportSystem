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

@WebServlet(name = "NewEmployeeServlet", urlPatterns = { "/html/newemployee" })
public class NewEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("id", "" + req.getParameter("id"));
        c.setHttpOnly(false);
        c.setPath("http://localhost:8080/html");
        resp.addCookie(c);
        resp.sendRedirect("/html/newemployee.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("id", "" + req.getParameter("id"));
        c.setHttpOnly(false);
        c.setPath("http://localhost:8080/html");
        resp.addCookie(c);

        DataAccess dataAccess = DataAccess.getInstance();
        Employee employee = new Employee(req.getParameter("firstname"), req.getParameter("lastname"), req.getParameter("dob"), req.getParameter("username"), req.getParameter("password"), req.getParameter("jobposition"));
        dataAccess.SaveEntity(employee);

        resp.sendRedirect("/html/manager.html");
    }
}
