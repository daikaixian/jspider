<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Jspider</title>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js">
    </script>
</head>
<body>
<div align="center"><h1>欢迎你！！！</h1></div>

<br>
<br>
<div align="center">
    <form action="execute" method="post" id="form">
    用户名:<input  name="username" id="username"><br>
    密&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="password" name="pswd" id="pswd"> <br>
    email地址:<input name = "email" id ="email"><br>
        <br>
    轮训周期:<select >
    <option value="1m">一分钟一次</option>
    <option value="1h">一小时一次</option>
    <option value="12h">12小时一次</option>
    <option value="1d">一天一次</option>
</select>

        <br>
        <br>
    <input type="button" onclick = "execute()" value="执行"/>&nbsp;&nbsp;<input type = "button" onclick="stop()" value="停止"/>
    </form>
    <br>

    <br>
    <br>
    <br>
</div>
    <span style="color: red">
        <ul>
     <li>  请注意，此程序是用于演示的demo，所以请不要试图寻找任何bug，因为你会轻易的达到目的。</li> <br><br>
        <li>正常的使用流程是；填写目标网站的正确用户名和密码，并填上接收邮件的正确地址，然后点击“执行”，即可。要停止任务，则点击“停止”</li><br><br>
            <li> 关于轮训周期，系统内部硬性设置成了一分钟一次,这里的修改不会生效,主要考虑到用于演示,所以一小时一次或者更长周期是没有意义的。当然，在正式的使用中时间是可以任意设置的，
                除了每隔一段时间执行一次以外，还可以设置成诸如每天早上8点和下午2点执行一次，周末不执行等等</li>
<br><br>
            <li>切换账号演示前一定要点击停止！
            <br>
            <br>

            <li>演示过后请一定要记得点击“停止”!!
            <li>演示过后请一定要记得点击“停止”!!
            <li>演示过后请一定要记得点击“停止”!!
        </ul>
    </span>


</body>
<script>
    function execute(){
        username = $("#username").val();
        pswd = $("#pswd").val();
        email = $("#email").val();

        if(username == null ||username.length==0){
            alert("请填写用户名");
            return
        }
        if(pswd == null ||pswd.length==0){
            alert("请填写密码");
            return
        }
        if(email == null ||email.length==0){
            alert("请填写邮箱");
            return
        }
        //$("#form").submit();
        $.post("execute", {
            "username" : username,
            "pswd" : pswd,
            "email" : email
        })

        alert("已启动，请稍后查收邮箱，不要重复点击“执行”！！！");
    }

    function stop(){
        $.get("stop");
        alert("已关闭任务，可重新点击“执行”")
    }

</script>
</html>