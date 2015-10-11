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

    <td><button type="button" class="btn btn-warning" onclick="updateStudent(${student.id},${student.number},'${student.name}',${student.grade},${student.telephone},'${student.hometown}')">修改</button>
        &nbsp;&nbsp;
        <button type="button" class="btn btn-danger" onclick="deleteStudent(${student.id})">删除</button>
    </td>
  </tr>
</c:forEach>

  </tbody>
</table>

  <div align="center"><button type="button" class="btn btn-info" onclick="count()">统计</button>
    &nbsp;&nbsp;
    <%--<button type="button" class="btn btn-primary" onclick="print()">打印</button>--%>
</div>
</div>

<div >
  <hr/>
</div>


<div class="container">
<h3><span class="label label-info">新增学生</span></h3>
  <div>
    <form class="form-horizontal" role="form" action="/createstudent/" method="post">

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
                 placeholder="请输入籍贯">
        </div>
      </div>

      <%--<input type="hidden" name="teacherId" value="${teacher.id}" >--%>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">新增</button>
        </div>
      </div>

    </form>


  </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close"
                data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="myModalLabel">
          修改学生信息
        </h4>
      </div>
      <form action="/updatestudent/" method="post">
      <div class="modal-body">

        <div class="form-group">
          <label for="one" class="col-sm-2 control-label">学号</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="m_one"
                   placeholder="请输入学号" name="number">
          </div>
        </div>

        <div class="form-group">
          <label for="two" class="col-sm-2 control-label">姓名</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="m_two" name="name"
                   placeholder="请输入姓名">
          </div>
        </div>

        <div class="form-group">
          <label for="three" class="col-sm-2 control-label">高考总分</label>
          <div class="col-sm-10">
            <input type="number" class="form-control" id="m_three" name="grade"
                   placeholder="请输入高考总分">
          </div>
        </div>

        <div class="form-group">
          <label for="four" class="col-sm-2 control-label">电话</label>
          <div class="col-sm-10">
            <input type="tel" class="form-control" id="m_four" name="telephone"
                   placeholder="请输入电话">
          </div>
        </div>


        <div class="form-group">
          <label for="five" class="col-sm-2 control-label">籍贯</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="m_five" name="hometown"
                   placeholder="请输入高考籍贯">
          </div>
        </div>

        <input type="hidden" name="id" id="m_id">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default"
                data-dismiss="modal">关闭
        </button>
        <button type="submit" class="btn btn-primary">
          提交更改
        </button>
      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->



  </div>


<div class="modal fade" id="countModal" tabindex="-1" role="dialog"
     aria-labelledby="countModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close"
                data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="countModalLabel">
          统计学生
        </h4>
      </div>
      <form action="/countstudent/" method="get">
        <div class="modal-body">



          <div class="form-group">
            <label for="three" class="col-sm-2 control-label">高考总分</label>
            <div class="col-sm-10">
              <select  class="form-control" id="m_select" name="grade">
              <option value="400">高于400分</option>
                <option value="430">高于430分</option>
                <option value="460">高于460分</option>
                <option value="490">高于490分</option>
              </select>
            </div>
          </div>



        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"
                  data-dismiss="modal">关闭
          </button>
          <button type="submit" class="btn btn-primary">
            提交更改
          </button>
        </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->

</div>

</body>
<script>
  function deleteStudent(id){
    isdelete = confirm("确定要删除该学生吗");
    if(isdelete){
      window.location.href="/deletestudent/?studentId="+id;
    }

  }

  function updateStudent(id,number,name,grade,telephone,hometown){
    $("#m_id").val(id);
    $("#m_one").val(number);
    $("#m_two").val(name);
    $("#m_three").val(grade);
    $("#m_four").val(telephone);
    $("#m_five").val(hometown);

    $("#myModal").modal('show');

  }
  function print(){

    $("#visiable").hide()
  }

  function show(){

    $("#visiable").show()
  }
  function count(){
    $("#countModal").modal('show');
  }
</script>
</html>
