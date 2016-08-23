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
    <h1>${logError} ${regError}</h1>
    <form action="login" method="POST">
        <div class="form-group">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" name="login" placeholder="Login"></div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
        </div>
        <div class="form-group">
            <a href="" role="button" data-toggle="modal" data-target="#registration-window">Registration</a> |
            <a href="" role="button" data-toggle="modal" data-target="#password-window">Forget password</a>
        </div>
        <br><br>
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form>
    <div class="modal fade" id="registration-window" role="dialog">
        <div class="modal-content modalblock">
            <div class="modal-header ">
                <form action="register" method="POST">
                    <div class="form-group">
                        <label>Login: <input type="text" class="form-control" name="login"
                                             placeholder="Login"></label>
                    </div>
                    <div class="form-group">
                        <label>Password: <input type="password" class="form-control" name="password"
                                                placeholder="Password"></label>
                    </div>
                    <div class="form-group">
                        <label>Email: <input type="email" class="form-control" name="email"
                                             placeholder="Email"></label>
                    </div>
                    <button class="btn btn-primary" type="submit">Register</button>
                </form>
            </div>
        </div>
    </div>
    <div class="modal fade" id="password-window" role="dialog">
        <div class="modal-content modalblock">
            <div class="modal-header ">
                <form action="" method="POST">
                    <div class="form-group">
                        <label>Enter your email: <input type="email" class="form-control" name="email"
                                                        placeholder="Email"></label>
                    </div>
                    <button class="btn btn-primary" type="submit">Send password</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
