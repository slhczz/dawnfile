package com.sy.dawnfile.control.servlet.register;
import com.sy.dawnfile.path.PathIf;
import com.sy.dawnfile.user.User;
import com.sy.dawnfile.user.UserIf;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet(name = "Register",urlPatterns = "/register")
public class Register extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        UserIf uf= new UserIf();
        PathIf pf = new PathIf();
        boolean register = false;
        //创建用户唯一标识
        user.setUuid(UUID.randomUUID().toString());
        try {
            //将注册信息保存在user bean中
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            //用户信息保存在数据库中
            register = uf.register(user.getPhone(), user.getEmail(), user.getPassword(), user.getUuid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(register){
            try {
                //登录成功，将用户的文件夹名称保存在数据库中
                //文件夹名称是唯一标识的前15个字符
                boolean b = pf.addPath(user.getUuid(), user.getUuid().substring(0, 15));
//                System.out.println("文件夹创建成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/dawnfile/login.jsp");
        }else{
            response.sendRedirect("/dawnfile/index.jsp");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}