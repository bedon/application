<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="text-center">
        <h1>Welcome : ${user}</h1>
        <a href="logout">Log out</a>
    </div>
    <div class="row">
        <div class="container col-xs-6">
            <form action="add-file" enctype="multipart/form-data" method="POST">
                Enter file name: <input type="text" name="name"/>
                <input type="file" name="file"/>
                <input type="submit" value="Add file">
            </form>
        </div>
        <div class="container col-xs-6">
            <form action="search" method="GET">
                <input type="text" name="pattern">
                <input type="submit" value="Search">
            </form>
        </div>
    </div>
</div>
<div class="container text-center">
    <div class="row">
        <c:forEach items="${myfile}" var="file">
            <div class="container-fluid col-md-3 col-sm-6 col-xs-12">
                <a href="/image/${file.id}">
                    <img src="/image/${file.id}" height="70" width="100" alt="picture"/></a><br>
                    ${file.name}<br>
                <a href="/delete?id=${file.id}">Delete</a><br>
                <a href="/download/${file.id}">Download</a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
