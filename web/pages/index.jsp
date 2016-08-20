<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body align="center">
    <h1>Welcome username: ${user}</h1>
    <a href="logout">Log out</a>
    <div>
        <form action="search" method="GET">
            <input type="text" name="pattern">
            <input type="submit" value="Search">
        </form>
    </div>
    <div>
        <table align="center">
            <thead>
            <tr>
                <td width="300" height="80"><b>File</b></td>
                <td width="300" height="80"><b>Name</b></td>
                <td width="300" height="80"><b>Action</b></td>
            </tr>
            </thead>
            <c:forEach items="${myfile}" var="file">
                <tr>
                    <td><a href="/image/${file.id}"><img src="/image/${file.id}" height="70" width="100" alt="picture"/></a></td>
                    <td>${file.name}</td>
                    <td>
                        <a href="/delete?id=${file.id}">Delete</a><br>
                        <a href="/download/${file.id}">Download</a>
                    </td>
                </tr>
                <th>
            </c:forEach>
        </table>
    </div>
    <br>
    <div>
        <form action="add-file" enctype="multipart/form-data" method="POST">
            Enter file name: <input type="text" name="name" />
            <input type="file" name="file" /><br>
            <input type="submit" value="Add file">
        </form>
    </div>
</body>
</html>
