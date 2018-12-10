package com.sy.dawnfile.control.servlet.quitlogin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet(name = "QuitLogin", urlPatterns = "/quitlogin")
public class QuitLogin extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie c_account = new Cookie("account","");
        Cookie c_password = new Cookie("password","");
        Cookie jssession = new Cookie("JSESSIONID","");
        c_account.setMaxAge(0);
        response.addCookie(jssession);
        c_account.setMaxAge(0);
        response.addCookie(c_account);
        c_password.setMaxAge(0);
        response.addCookie(c_password);
        response.sendRedirect("/dawnfile/index.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}