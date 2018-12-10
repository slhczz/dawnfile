package com.sy.dawnfile.control.servlet.login;

import com.sy.dawnfile.path.PathIf;
import com.sy.dawnfile.user.User;
import com.sy.dawnfile.user.UserIf;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String auto = request.getParameter("auto");
        System.out.println(auto);

        //判断是否有用户数据传过来
        if(account == null&&password == null){
            //没有返回登录页
            response.sendRedirect("/dawnfile/login.jsp");
        }else {
            //有登录信息
            UserIf uf = new UserIf();
            try {
                User user = uf.login(account, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    //是否选择7天免登陆
                    if(auto != null){
                        Cookie c_account = new Cookie("account",account);
                        Cookie c_password = new Cookie("password",user.getPassword());
                        Cookie jssession = new Cookie("JSESSIONID",session.getId());
                        c_account.setMaxAge(60*60*24*7);
                        response.addCookie(jssession);
                        c_account.setMaxAge(60*60*24*7);
                        response.addCookie(c_account);
                        c_password.setMaxAge(60*60*24*7);
                        response.addCookie(c_password);
                    }
                    //查询登录用户的文件夹名称
                    String uuid = uf.queryUuid(account);

                    String user_path = null;
                    PathIf pf = new PathIf();
                    try {
                        user_path = pf.queryPath(uuid);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //将用户信息和文件夹名称保存在session中
                    session.setAttribute("user_path", user_path);
                    session.setAttribute("user", user);
                    //进入上传文件和查看文件页面
                    request.getRequestDispatcher("/upload.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/dawnfile/login.jsp");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}