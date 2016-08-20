<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${logError}
    <form action="login" method="POST">
        Login: <input type="text" name="login"/><br>
        Password: <input type="password" name="password"/><br>
        <a href="forgot_password">Forgot password</a><br>
        <input type="submit" value="Log in">
    </form>
    ${regError}
    <form action="register" method="POST">
        Input login: <input type="text" id="login" name="login" /><br>
        Input password: <input type="password" id="password" name="password" /><br>
        Input email: <input type="email" id="email" name="email" /><br>
        <input type="submit" value="Register" />
    </form>
</body>
</html>
