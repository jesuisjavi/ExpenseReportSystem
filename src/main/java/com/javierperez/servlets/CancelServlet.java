package com.javierperez.servlets;

import com.javierperez.dataaccess.DataAccess;
import com.javierperez.workplace.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CancelServlet", urlPatterns = { "/html/cancel" })
public class CancelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("id", "" + req.getParameter("id"));
        c.setHttpOnly(false);
        c.setPath("http://localhost:8080/html");
        resp.addCookie(c);

        DataAccess dataAccess = DataAccess.getInstance();
        Employee employee = dataAccess.GetEmployeeById(Integer.parseInt(req.getParameter("id")));

        if (employee.getJobPosition().equals("Manager"))
            resp.sendRedirect("/html/manager.html");
        else
            resp.sendRedirect("/html/employee.html");
    }
}
