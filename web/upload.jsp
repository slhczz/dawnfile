<%@ page import="java.io.File" %>
<%@ page import="com.sy.dawnfile.path.PathIf" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.sy.dawnfile.user.User" %>
<%@ page import="com.sy.dawnfile.file.FileIf" %>
<%@ page import="com.sy.dawnfile.file.Files" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/7
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>文件上传</title>
    <link rel="shortcut icon" href="./img/favicon.ico">
    <link rel="stylesheet" href="./css/common.css">
</head>
<style type="text/css">
    *{margin: 0;padding: 0;}
    body{width: 1000px;margin: 0 auto;}
    .header{height: 150px;margin: 30px auto;width: 50%;}
    .header img{width: 100%}
    .content{height: 100px;background-color: gainsboro;}
    .content form{padding: 35px 0;}
    .content #file{height: 35px;width: 900px;padding-left: 20px;}
    .content #sub{height: 35px;width: 55px;}
    .foot{height: 50px;width:1000px;background-color: antiquewhite;position: absolute;bottom: 12px;text-align: center;line-height: 50px;}
    .foot p{font-size: 12px;}
</style>
<body>

<div id="topbar">
    <div class="container">
        <div id="topbar-menu">
            <ul>
                <%
                    Object obj = session.getAttribute("user");
                    User user = (User) obj;
                    if(user == null){
                        %>
                        <li>
                            <a href="/dawnfile/login.jsp" class="red">登录</a>
                        </li>
                        <li class="separator">|</li>
                        <li>
                            <a href="/dawnfile/register.jsp">免费注册</a>
                        </li>
                        <%
                    }else{
                        %>
                        <li>
                            <a href="/dawnfile/myself.jsp" class="red"><%=user.getUsername()%></a>
                        </li>
                        <li class="separator">|</li>
                        <li>
                            <a href="/dawnfile/quitlogin">退出</a>
                        </li>
                        <%
                    }
                %>

                <li class="separator">|</li>
                <li>
                    <a href="/dawnfile/help.html">帮助中心</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="header">
    <a href="upload.jsp"><img src="./img/logo_rectangle.png"></a>
</div>
<div class="content">

    <form action="/dawnfile/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" id="file" multiple="multiple"  />
        <input type="submit" id="sub" value="上传"/>
    </form>
</div>
<%
Object message = request.getAttribute("message");
%>
<p><%=message%></p>
<%
    FileIf ff = new FileIf();
    ArrayList<Files> files = ff.queryAll(user.getUsername());
    for ( Files file:files) {
        String filepath = file.getFilepath();
        String filename = filepath.substring(filepath.lastIndexOf("\\")+1);
%>
    <li><a href="/dawnfile/download?filename=<%=filename%>"><%=file.getFilename()%></a></li>
<%
    }
%>
<div class="foot">
    <p>版权所有 <span>©2018&nbsp;slhczz</span></p>
</div>
</body>
</html>
