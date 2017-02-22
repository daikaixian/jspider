
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>学生管理系统</title>
  <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.js"></script>
</head>
<body>

<div class="container">
  <h3><span class="label label-info">本人信息</span></h3>
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

        <tr class="active">
      <td>${student.number}</td>
      <td>${student.name}</td>
      <td>${student.grade}</td>
      <td>${student.telephone}</td>
      <td>${student.hometown}</td>
      </tr>
    </tbody>
  </table>
</div>

<div >
  <hr/>
</div>



<div class="container">
  <h3><span class="label label-info">同学信息</span></h3>
  <table class="table">
    <thead>
    <tr>
      <th>学号</th>
      <th>姓名</th>
      <th>电话</th>
      <th>籍贯</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="other" varStatus="status">
      <c:if test="${status.count mod 2 == 0}">
        <tr class="active">
      </c:if>
      <c:if test="${status.count mod 2 != 0}">
        <tr class="success">
      </c:if>
      <td>${other.number}</td>
      <td>${other.name}</td>
      <td>${other.telephone}</td>
      <td>${other.hometown}</td>
      </tr>
    </c:forEach>

    </tbody>
  </table>
  <div align="center">

      <form action="/querybyname/" method="get">
      <input type="text" placeholder="请输入姓名" name="name">  <button class="btn btn-default" type="submit">查询</button>
    </form>
  </div>
</div>

</body>
</html>
