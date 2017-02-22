
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理系统</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.js"></script>
</head>
<body>

<br>
<div class="container">
<form class="form-horizontal" role="form" action="login" method="post">
    <div class="form-group" align="center">
        <h2 class="form">学生管理系统</h2>
    </div>
    <div class="form-group">
        <label for="firstname" class="col-sm-2 control-label">账号</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="firstname"
                   placeholder="请输入账号" name="number">
        </div>
    </div>
    <div class="form-group">
        <label for="lastname" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="lastname"
                   placeholder="请输入密码" name="pswd">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">登录</button>
        </div>
    </div>
</form>
</div>
</body>
</html>
