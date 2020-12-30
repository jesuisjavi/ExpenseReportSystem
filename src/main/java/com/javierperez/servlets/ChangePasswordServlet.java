package com.javierperez.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierperez.dataaccess.DataAccess;
import com.javierperez.utils.IDInfo;
import com.javierperez.utils.IDPasswordInfo;
import com.javierperez.utils.IDUsernameInfo;
import com.javierperez.workplace.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = { "/html/changepassword" })
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper om = new ObjectMapper();
        IDPasswordInfo id = om.readValue(req.getInputStream(), IDPasswordInfo.class);
        DataAccess dataAccess = DataAccess.getInstance();
        Employee employee = dataAccess.GetEmployeeById(id.getId());
        employee.setPassword(id.getPassword());
        dataAccess.UpdateEntity(employee);
        resp.setContentType("application/json");
        resp.getWriter().write("Success");
    }
}
