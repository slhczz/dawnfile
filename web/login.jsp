<%--
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
<%
    HttpSession session1 = request.getSession();
    Object user = session1.getAttribute("user");
    if(user != null){
        response.sendRedirect("/dawnfile/myself.jsp");
    }
%>
<div id="topbar">
    <div class="container">
        <div id="topbar-menu">
            <ul>
                <li>
                    <a href="/dawnfile/login.jsp" class="red">登录</a>
                </li>
                <li class="separator">|</li>
                <li>
                    <a href="/dawnfile/register.jsp">免费注册</a>
                </li>
                <li class="separator">|</li>
                <li>
                    <a href="/dawnfile/help.html">帮助中心</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div id="main">
    <div class="container">
        <div id="register">
            <h1>登录</h1>
            <form action="/dawnfile/login" method="post" onsubmit="return register()">
                <table class="form">
                    <tbody><tr>
                        <th>
                            <label for="mobile" class="label">手机号码</label>
                            <em class="required">*</em>
                        </th>
                        <td>
                            <input type="text" name="account" id="mobile" class="medium" onblur="mobileBlur()">
                            <span class="errorMobile">手机号码错误!</span>
                            <span class="tip">仅限中国大陆移动、电信、联通手机号码。</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="password" class="label">登录密码</label>
                            <em class="required">*</em>
                        </th>
                        <td>
                            <input type="password" name="password" id="password" class="medium" onblur="passwordBlur()">
                            <span class="errorPassword">密码格式错误!</span>
                            <span class="tip">仅限6~16个字母、数字、特殊符号。</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                        </th>
                        <td>
                            <input type="checkbox" name="auto" id="radio" value="auto">七天免登陆
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <input type="submit" value="登 录" id="submit" class="submit">
                        </td>
                    </tr>
                    </tbody></table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
