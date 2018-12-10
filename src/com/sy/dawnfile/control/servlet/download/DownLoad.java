package com.sy.dawnfile.control.servlet.download;

import com.sy.dawnfile.file.FileIf;
import com.sy.dawnfile.file.Files;
import com.sy.dawnfile.user.User;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DownLoad", urlPatterns = "/download")
public class DownLoad extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        User user = (User) obj;
        // 1.你要下哪个文件
        String filename = request.getParameter("filename");
        FileIf ff = new FileIf();
        Files path = null;
        try {
            path = ff.queryPath(filename,user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 告诉客户端要下载的文件的MIME类型
        response.setContentType(this.getServletContext().getMimeType(path.getFilename()));
        String dfilename = "";
        // user-agent
        String agent = request.getHeader("User-Agent");
        if (agent.contains("MSIE")) {
            // IE浏览器
            dfilename = URLEncoder.encode(path.getFilename(), "utf-8");
            dfilename = dfilename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            dfilename = "=?utf-8?B?" + base64Encoder.encode(path.getFilename().getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            dfilename = URLEncoder.encode(path.getFilename(), "utf-8");
        }
        // filename
//        System.out.println(filename);




        // 告诉浏览器不要去解析，而是去下载
        response.setHeader("Content-Disposition", "attachment;filename=" + dfilename);

        // D:\apache-tomcat-8.0.53-windows-x64\apache-tomcat-8.0.53\webapps\day14download\download\a.flv
//        http://localhost:8080/dawnfile/download?filename=idmcchandler2_64.dll
        // 根据服务器地址，获得绝对路径。

        String pathname = getServletContext().getRealPath("/upload/" +path.getFilepath());
//        System.out.println(pathname);
        FileInputStream fis = new FileInputStream(pathname);// 获得输入流

        // 获得输出流
        ServletOutputStream os = response.getOutputStream();
        // 读写
        int len = 0;
        byte[] bs = new byte[1024*1024];
        while ((len = fis.read(bs)) != -1) {
            os.write(bs,0,len);
        }
        // 关闭连接
        fis.close();

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}