<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理系统</title>
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
    <th>操作</th>
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
    <td><button type="button" class="btn btn-warning">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger">删除</button></td>
  </tr>
</c:forEach>

  </tbody>
</table>
  <div align="center"><button type="button" class="btn btn-info">统计</button>&nbsp;&nbsp;<button type="button" class="btn btn-primary">打印</button>
</div>
  </div>

<div >
  <hr/>
</div>

<div class="container">
<h3><span class="label label-info">新增学生</span></h3>
  <div>
    <form class="form-horizontal" role="form" action="" method="post">

      <div class="form-group">
        <label for="one" class="col-sm-2 control-label">学号</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="one"
                 placeholder="请输入学号" name="number">
        </div>
      </div>

      <div class="form-group">
        <label for="two" class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="two" name="name"
                 placeholder="请输入姓名">
        </div>
      </div>

      <div class="form-group">
        <label for="three" class="col-sm-2 control-label">高考总分</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="three" name="grade"
                 placeholder="请输入高考总分">
        </div>
      </div>

      <div class="form-group">
        <label for="four" class="col-sm-2 control-label">电话</label>
        <div class="col-sm-10">
          <input type="tel" class="form-control" id="four" name="telephone"
                 placeholder="请输入电话">
        </div>
      </div>


      <div class="form-group">
        <label for="five" class="col-sm-2 control-label">籍贯</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="five" name="hometown"
                 placeholder="请输入高考总分">
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">新增</button>
        </div>
      </div>

    </form>


  </div>
</div>


</body>
</html>
