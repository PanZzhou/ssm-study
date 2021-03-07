<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <%--    bootstrap美化页面--%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>
    <%--action是要定向的控制器方法路径，，name是实体类对应的属性名--%>
    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
<%--        出现问题：id没有传送到Controller，解决方法：隐藏域--%>
        <input type="hidden" name="bookID" value="${Qbook.bookID}">

        <div class="form-group">
            <label >书籍名称:</label>
            <input type="text" name="bookName" class="form-control" value="${Qbook.bookName}" required>
        </div>
        <div class="form-group">
            <label>书籍数量:</label>
            <input type="text" name="bookCounts" class="form-control" value="${Qbook.bookCounts}" required>
        </div>
        <div class="form-group">
            <label>书籍概述:</label>
            <input type="text" name="detail" class="form-control" value="${Qbook.detail}" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="修改">
        </div>
    </form>

</div>


</body>
</html>
