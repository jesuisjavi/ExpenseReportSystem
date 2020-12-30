package com.javierperez.servlets;

import com.javierperez.dataaccess.DataAccess;
import com.javierperez.utils.LoginInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = { "/html/login" })
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        DataAccess dataAccess = DataAccess.getInstance();
        LoginInfo info = dataAccess.Login(username, password);

        if (info.isSuccess()){
            Cookie c = new Cookie("id", "" + info.getId());
            c.setHttpOnly(false);
            c.setPath("http://localhost:8080/html");
            resp.addCookie(c);
            if(info.getEmployeeType().equals("Manager")) {

                resp.sendRedirect("/html/manager.html");
            }
            else{
                resp.sendRedirect("/html/employee.html");
            }
        }
        else {
            resp.getWriter().write("Login failure. Please try again");
        }
    }
}
