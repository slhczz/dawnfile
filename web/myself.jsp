<%@ page import="com.sy.dawnfile.user.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/7
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./iconfont/iconfont.css">
    <link rel="stylesheet" href="./css/form.css">
    <link rel="stylesheet" href="./css/register.css">
    <link rel="shortcut icon" href="./img/favicon.ico">
    <script src="js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="js/register.js" ></script>
</head>
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
                    <a href="./dawnfile/login.jsp" class="red">登录</a>
                </li>
                <li class="separator">|</li>
                <li>
                    <a href="./dawnfile/register.jsp">免费注册</a>
                </li>
                <%
                }else{
                %>
                <li>
                    <a href="./dawnfile/myself.jsp" class="red"><%=user.getUsername()%></a>
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
                    <a href="./dawnfile/help.html">帮助中心</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div id="main">
<%
%>
</div>
</body>
</html>
