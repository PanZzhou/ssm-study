<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/jQuery-3.4.1.js"></script>
    <script>
        $(function (){
            $("#btn1").click(function (){
                $.post("${pageContext.request.contextPath}/a2",function (data){
                    var html = "";
                    for (let i=0; i<data.length;i++){
                        html += "<tr>"+
                            "<td>"+data[i].name+"</td>"+
                            "<td>"+data[i].sex+"</td>"+
                            "<td>"+data[i].age+"</td>"+
                            "</tr>"
                    }
                    //显示在这个id=content组件里面
                    $("#content").html(html);
                })
            })
        });
    </script>
</head>
<body>

<input type="button" value="加载数据" id="btn1">
<table>
    <tr>
        <td>姓名</td>
        <td>性别</td>
        <td>年龄</td>
    </tr>
    <tbody id="content">
<%--    数据，后台--%>
    </tbody>
</table>

</body>
</html>
