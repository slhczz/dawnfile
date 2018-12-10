package com.sy.dawnfile.control.filter;


import com.sy.dawnfile.path.PathIf;
import com.sy.dawnfile.user.User;
import com.sy.dawnfile.user.UserIf;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class DawnFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        String account = null;
        String passowrd = null;
//        String contextPath = request.getServletPath();
//        System.out.println(contextPath);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("account".equals(cookie.getName())) {
                    account = cookie.getValue();
                }
                if ("password".equals(cookie.getName())) {
                    passowrd = cookie.getValue();
                }
            }
            if (account != null && passowrd != null) {
                UserIf uf = new UserIf();
                User user = null;
//                System.out.println(account + ":" + passowrd);
                try {
                    user = uf.login(account, passowrd);
//                    System.out.println(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (user != null) {
                    if (account.equals(user.getPhone()) && passowrd.equals(user.getPassword()) || account.equals(user.getEmail()) && passowrd.equals(user.getPassword())) {
//                    request.getRequestDispatcher("/dawnfile/upload.jsp").forward(request,response);
                        HttpSession session = request.getSession();
                        PathIf pf = new PathIf();
                        String user_path = null;
                        try {
                            user_path = pf.queryPath(user.getUuid());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        session.setAttribute("user", user);
                        session.setAttribute("user_path", user_path);
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendRedirect("/dawnfile/login.jsp");
                    }
                } else {
                    response.sendRedirect("/dawnfile/login.jsp");
                }
            } else {
                response.sendRedirect("/dawnfile/login.jsp");
            }
        } else {
            response.sendRedirect("/dawnfile/login.jsp");
        }
//        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
