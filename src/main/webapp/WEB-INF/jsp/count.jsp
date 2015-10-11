<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理系统</title>
  <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
  <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.js"></script>
</head>
<body>
<div align="right">当前用户:${teacher.name}</div>

<div class="container">
  <h3><span class="label label-info">管理学生</span></h3>
  <table class="table">
  <thead>
  <tr>
    <th>学号</th>
    <th>姓名</th>
    <th>高考总分</th>
    <th>电话</th>
    <th>籍贯</th>
  </tr>
  </thead>
  <tbody>
<c:forEach items="${studentList}" var="student" varStatus="status">
<c:if test="${status.count mod 2 == 0}">
  <tr class="active">
</c:if>
  <c:if test="${status.count mod 2 != 0}">
    <tr class="success">
  </c:if>
    <td>${student.number}</td>
    <td>${student.name}</td>
    <td>${student.grade}</td>
    <td>${student.telephone}</td>
    <td>${student.hometown}</td>

  </tr>
</c:forEach>

  </tbody>
</table>
  <span>共${fn:length(studentList)}人</span>
</div>

<div >
  <hr/>
</div>



</body>

</html>
