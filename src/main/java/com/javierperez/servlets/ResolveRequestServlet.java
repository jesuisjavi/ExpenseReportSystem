package com.javierperez.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierperez.dataaccess.DataAccess;
import com.javierperez.utils.IDPasswordInfo;
import com.javierperez.utils.ResolveReqInfo;
import com.javierperez.workplace.Employee;
import com.javierperez.workplace.ExpenseRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResolveRequestServlet", urlPatterns = { "/html/resolverequest" })
public class ResolveRequestServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper om = new ObjectMapper();
        ResolveReqInfo info = om.readValue(req.getInputStream(), ResolveReqInfo.class);
        DataAccess dataAccess = DataAccess.getInstance();
        ExpenseRequest request = dataAccess.GetRequestById(info.getRequestId());
        request.setStatus(info.getDecision());
        request.setInfo(info.getInfo());
        request.setResolver(dataAccess.GetEmployeeById(info.getId()).getUsername());
        dataAccess.UpdateEntity(request);
        resp.setContentType("application/json");
        resp.getWriter().write("Success");
    }
}
