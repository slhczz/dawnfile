<%@ page import="com.sy.dawnfile.user.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/7
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./iconfont/iconfont.css">
    <link rel="shortcut icon" href="./img/favicon.ico">
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

<div id="main">
    <div class="container">
        <div id="register">
            <h1>注册</h1>
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
                            <label for="password" class="label">设置密码</label>
                            <em class="required">*</em>
                        </th>
                        <td>
                            <input type="password" name="password" id="password" class="medium" onblur="passwordBlur()">
                            <span class="errorPassword">密码格式错误!</span>
                            <span class="tip">仅限6~16个字母、数字、特殊符号。</span>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <input type="submit" value="注 册" id="submit" class="submit">
                        </td>
                    </tr>
                    </tbody></table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
