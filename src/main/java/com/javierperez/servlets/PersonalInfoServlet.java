package com.javierperez.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierperez.dataaccess.DataAccess;
import com.javierperez.utils.IDInfo;
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
import java.util.List;

@WebServlet(name = "PersonalInfoServlet", urlPatterns = { "/html/personalinfo" })
public class PersonalInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("id", "" + req.getParameter("id"));
        c.setHttpOnly(false);
        c.setPath("http://localhost:8080/html");
        resp.addCookie(c);

        resp.sendRedirect("/html/personalinformation.html");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper om = new ObjectMapper();
        IDInfo id = om.readValue(req.getInputStream(), IDInfo.class);
        DataAccess dataAccess = DataAccess.getInstance();
        Employee employee = dataAccess.GetEmployeeById(id.getId());
        String json = om.writeValueAsString(employee);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
