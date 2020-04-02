<%--
  Created by IntelliJ IDEA.
  User: ruslankononov
  Date: 20.03.2020
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login Form</h2>
    <form action="login" method="post">
        <label for="login">Login: </label>
        <input type="text" name="login" id="login">
        <br>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password">
        <br>
        <input type="submit" value="submit">
    </form>
</body>
</html>
