package com.javierperez.servlets;

import com.javierperez.dataaccess.DataAccess;
import com.javierperez.workplace.Email;
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

@WebServlet(name = "NewEmailServlet", urlPatterns = { "/html/newemail" })
public class NewEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("id", "" + req.getParameter("id"));
        c.setHttpOnly(false);
        c.setPath("http://localhost:8080/html");
        resp.addCookie(c);

        DataAccess dataAccess = DataAccess.getInstance();
        Employee sender = dataAccess.GetEmployeeById(Integer.parseInt(req.getParameter("id")));

        Email email = new Email(LocalDate.now().toString(), sender.getUsername(), Integer.parseInt(req.getParameter("receiver")), req.getParameter("subject"), req.getParameter("body"));
        dataAccess.SaveEntity(email);

        if (sender.getJobPosition().equals("Manager"))
            resp.sendRedirect("/html/manager.html");
        else
            resp.sendRedirect("/html/employee.html");
    }
}
