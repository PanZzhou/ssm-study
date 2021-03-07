<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/jQuery-3.4.1.js"></script>
    <script>
        function check(){
            $.post({
                url:"${pageContext.request.contextPath}/loginCheck",
                data:{"name":$("#user").val()},
                success:function (data){
                    if(data.toString()=="ok"){
                        $("#userInfo").css("color","green");
                    }else{
                        $("#userInfo").css("color","red");
                    }
                    $("#userInfo").html(data);
                }
            })
        }
        function check1(){
            $.post({
                url:"${pageContext.request.contextPath}/pwdCheck",
                data:{"pwd":$("#pwd").val()},
                success:function (data){
                    if(data.toString()=="ok"){
                        $("#pwdInfo").css("color","green");
                    }else{
                        $("#pwdInfo").css("color","red");
                    }
                    $("#pwdInfo").html(data);
                }
            })
        }
    </script>
</head>
<body>
<p>
    用户名：<input type="text" id="user" onblur="check()">
    <span id="userInfo"></span>
</p>
<p>
    密码：<input type="password" id="pwd" onblur="check1()">
    <span id="pwdInfo"></span>
</p>
</body>
</html>
