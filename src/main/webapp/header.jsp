<%--
  Created by IntelliJ IDEA.
  User: ruslankononov
  Date: 19.03.2020
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
          crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-light">
        <a class="navbar-brand text-success" href="#">ReadMore</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link text-success" href="<%=request.getContextPath()%>/cabinet.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-success" href="<%=request.getContextPath()%>/newProduct.jsp">Add new item</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-success" href="<%=request.getContextPath()%>/bucket.jsp">Bucket</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" method="get">
                <button class="btn btn-outline-success logout my-2 my-sm-0" type="button">Log out</button>
            </form>
        </div>
    </nav>
</body>
</html>


