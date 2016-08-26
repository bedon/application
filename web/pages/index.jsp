<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        <%@include file='../css/index-style.css' %>
    </style>
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="user col-sm-10 col-md-3">
                ${user}
            </div>
            <div class="search col-sm-8 col-md-6 hidden-sm">
                <form action="search" method="GET">
                    <input type="text" name="pattern">
                    <button class="" type="submit">Search</button>
                </form>
            </div>
            <div class="sign-out-btn col-sm-2 col-md-3">
                <form action="logout">
                    <button class="btn btn-primary">Sign out</button>
                </form>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="search-small col-sm-12 visible-sm-block">
                <form action="search" method="GET">
                    <input type="text">
                    <button class="" type="submit">Search</button>
                </form>
            </div>
        </div>
    </div>
</header>
<main>
    <div class="container text-center">
        <form class="form-inline" action="add-file" enctype="multipart/form-data" method="POST">
            <div class="form-group">
                <label>Enter file name: <input type="text" name="name" /></label>
            </div>
            <div class="form-group">
                <input type="file" name="file" />
            </div>
            <input class="btn btn-primary" type="submit" value="Add file">
        </form>
    </div>
    <div class="container main">
        <div class="row">
            <c:forEach items="${myfile}" var="file">
                <div class="container-fluid content col-lg-2 col-md-3 col-sm-6 col-xs-12">
                    <a href="/image/${file.id}">
                        <img src="/image/${file.id}" alt="picture"/></a><br>
                        ${file.name}<br>
                    <a href="/delete?id=${file.id}">Delete</a><br>
                    <a href="/download/${file.id}">Download</a>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
