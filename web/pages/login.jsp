<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        <%@include file='../css/login-style.css' %>
    </style>
</head>
<body>
    <div class="container mainblock">
        <form action="login" method="POST">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" id="login" name="login" placeholder="Login"> </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
            <div class="form-group">
                <a href="">Registration</a> | <a href="">Forget password</a>
            </div>
            <button type="submit" class="btn btn-primary">Sign in</button>
        </form>
    </div>
</body>
</html>
