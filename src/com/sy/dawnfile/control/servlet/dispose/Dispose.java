package com.sy.dawnfile.control.servlet.dispose;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "Dispose", urlPatterns = "/dispose")
public class Dispose extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accect = request.getParameter("accect");
        if(accect != null){
            if("login".equals(accect)){
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else if("register".equals(accect)) {
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        }else{
            response.sendRedirect("/index.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}