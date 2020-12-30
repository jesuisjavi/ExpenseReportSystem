package com.javierperez.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierperez.dataaccess.DataAccess;
import com.javierperez.utils.IDInfo;
import com.javierperez.utils.IDUsernameInfo;
import com.javierperez.workplace.Email;
import com.javierperez.workplace.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChangeUsernameServlet", urlPatterns = { "/html/changeusername" })
public class ChangeUsernameServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper om = new ObjectMapper();
        IDUsernameInfo id = om.readValue(req.getInputStream(), IDUsernameInfo.class);
        DataAccess dataAccess = DataAccess.getInstance();
        String json;

        Employee check = dataAccess.GetEmployeeByUsername(id.getUsername());
        if (check != null){
            json = "!Success";
        }
        else {
            Employee employee = dataAccess.GetEmployeeById(id.getId());
            employee.setUsername(id.getUsername());
            dataAccess.UpdateEntity(employee);
            json = "Success";
        }
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
