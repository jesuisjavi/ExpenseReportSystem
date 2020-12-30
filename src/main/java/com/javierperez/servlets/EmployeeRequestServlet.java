package com.javierperez.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierperez.dataaccess.DataAccess;
import com.javierperez.utils.IDInfo;
import com.javierperez.workplace.ExpenseRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeRequestServlet", urlPatterns = { "/html/employeerequest" })
public class EmployeeRequestServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper om = new ObjectMapper();
        IDInfo id = om.readValue(req.getInputStream(), IDInfo.class);
        DataAccess dataAccess = DataAccess.getInstance();
        List<ExpenseRequest> requests = dataAccess.GetRequestsByEmployee(id.getId());
        String json = om.writeValueAsString(requests);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}