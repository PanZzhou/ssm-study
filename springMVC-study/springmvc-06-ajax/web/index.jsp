<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
<%--    引用jquery库--%>
    <script src="${pageContext.request.contextPath}/static/jQuery-3.4.1.js"></script>
    <script>
      function on(){
        $.post({
          url:"${pageContext.request.contextPath}/all",
          //name对应传给后端方法中的参数名name
          data:{"name":$("#username").val()},
          success:function (data,status){
            alert(data)
            console.log(status)
          },
          error:function (data){
            alert(data)
          }
        })
      }
    </script>

  </head>
  <body>

  用户名:<input type="text" id="username" onblur="on()" value="杨红">

  </body>
</html>
