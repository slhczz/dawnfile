package com.sy.dawnfile.control.servlet.upload;

import java.io.*;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sy.dawnfile.file.FileIf;
import com.sy.dawnfile.user.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//@WebServlet(name = "UpLoad", urlPatterns = "/upload")
public class UpLoad extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        //获取用户文件夹名称
        Object path1 = session.getAttribute("user_path");
        Object obj = session.getAttribute("user");
        User user = (User) obj;

        if(obj == null){
            resp.sendRedirect("/dawnfile/login.jsp");
        }else {
            String message = "";
            String user_path = (String) path1;
            String local_path = this.getServletContext().getRealPath("/upload/");
            String path = local_path + user_path;
            File file = new File(path);
            //查看文件目录是否存在
            if (!file.exists() && !file.isDirectory()) {
                System.out.println(path + "目录不存在，需要创建！");
                //创建目录
                file.mkdirs();
            }
            try {
                //使用Apache文件上传组件处理文件上传步骤：
                //1.创建一个DiskFileItemFactory工厂
                DiskFileItemFactory factory = new DiskFileItemFactory();
                //2.创建一个文件上传解析器
                ServletFileUpload upload = new ServletFileUpload(factory);
                //解决中文乱码
                upload.setHeaderEncoding("UTF-8");
                //3.判断提交的数据普通表单的数据还是带文件上传的表单
                if (!upload.isMultipartContent(req)) {
                    //如果是表单数据普通表单,则按照传统方式获取数据
                    return;
                }
                //4.使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
                List<FileItem> list = upload.parseRequest(req);
                for (FileItem item : list) {
                    //如果fileItem中封装的是普通输入项的数据
                    if (item.isFormField()) {
                        //获取字段名字
                        String name = item.getFieldName();
                        //解决普通输入项中中文乱码问题
                        String value = item.getString("UTF-8");//value = new String(value.getBytes("iso8859-1"),"UTF-8");
//                        System.out.println(name + " = " + value);
                    } else {    //如果表单中提交的是上传文件
                        //获得上传的文件名称
                        String filename = item.getName();
                        System.out.println(filename);
                        if (filename == null || filename.trim().equals(" ")) {
                            continue;
                        }
                        //注意：不同的浏览器提交的文件名称是不一样的，有些浏览器提交的文件会带有路径，如“D:\\project\WebRoot\hello.jsp”，有一些是单纯的文件名：hello.jsp
                        //去掉获取到文件名中的路径名，保留单纯的文件名
                        filename = filename.substring(filename.lastIndexOf("\\") + 1);
                        int i = filename.lastIndexOf(".");
                        String uuid = UUID.randomUUID().toString();
                        String filname = uuid.substring(uuid.lastIndexOf("-")+1);
                        //获取item中的上传文件的输入流
                        InputStream in = item.getInputStream();
                        //创建一个文件输入流
                        FileOutputStream out = new FileOutputStream(path + "\\" + filname+filename.substring(i));
                        //创建一个缓冲区
                        byte buffer[] = new byte[1024];
                        //判断输入流中的数据是否已经读取完毕的标志位
                        int len = 0;
                        //循环将输入流读入到缓冲区当中，（len = in.read(buffer)>0）就表示in里面还有数据存在
                        while ((len = in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录（path+"\\"+filename）当中
                            out.write(buffer, 0, len);
                        }
                        //关闭输入流
                        in.close();
                        //关闭输出流
                        out.close();
                        //删除处理文件上传生成的临时文件
                        item.delete();
                        message = "文件上传成功!";
                        FileIf ff = new FileIf();
                        ff.addFile(filename,filname+filename.substring(i),user_path+"\\"+filname+filename.substring(i),user.getUsername());
                    }
                }

            } catch (Exception e) {
                message = "文件上传失败！";
                e.printStackTrace();
            }
            req.setAttribute("message", message);
            req.getRequestDispatcher("/upload.jsp").forward(req, resp);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}