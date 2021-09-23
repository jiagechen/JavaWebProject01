<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|个人信息</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/userInfo.css" />
</head>
<body >
<!-- 1.头部 -->
<div id="header">
  <div class="logo">
    <a href="http://cs.njust.edu.cn/">
      <img src="img/cslogo.png"  />
    </a>
  </div>
  <div class="logo2">
    <a href="#">
      <img src="img/LOGO1.png"  />
    </a>
  </div>
  <div class="name">
    <!-- <a href="index.html">
        1669953081@qq.cpm
    </a> -->
<%
    User user = (User)session.getAttribute("user");
%>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="#">
      欢迎您！ <%=user.getName() %>
    </a>

    <a href="Logout" style="margin-left: 100px;">
      退出登录
    </a>
  </div>
</div>
<!-- 2.导航 -->
<div id="nav">
  <ul  >
    <li><a href="UserAdmin">用户管理</a></li>
    <!-- <li><a href="#">注册</a></li> -->
    <li role="presentation" class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
        信息管理 <span class="caret"></span>
      </a>
      <ul class="dropdown-menu"
          style="padding-left: 0;">
        <li style="border: none;height: 50px;width: 100px;margin: 0 auto;"><a href="DealAdmin"style="color: black;">交易管理&nbsp;&nbsp;<span class="badge"></span></a></li>
        <li style="border: none;height: 50px;width: 100px;"><a href="CmpltAdmin"style="color: black;">投诉管理&nbsp;&nbsp;<span class="badge"></span></a></li>
      </ul>
    </li>
    <li><a href="adminInfo.jsp" style="color: #de4b15;">个人中心<span class="badge"></span></a></li>
    <li><a href="adminIndex.jsp">返回首页</a></li>

  </ul>
</div>







<div class="panel panel-default" style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-body" style="font-size:16px;">
    <div class="media">
      <div class="media-left">
        <a href="#">
          <img class="media-object" src="ShowUserImage" style="height: 300px;width: 300px;">
        </a>
      </div>
      <div class="media-body">
        <br />
        <!-- <h1 class="media-heading" align="center">个人信息</h1> -->


        <div class="Otherfont">
          <p>学号：<%=user.getId() %> <br />
            昵称：<%=user.getName() %>  <a href="modifyUserInfo.jsp" title="修改昵称"><span class="glyphicon glyphicon-pencil" style="float: none;"></span></a><br />
            学院：<%=user.getSchool() %> <a href="modifyUserInfo.jsp" title="修改昵称"><span class="glyphicon glyphicon-pencil" style="float: none;"></span></a> <br /></p>
        </div>
      </div>
    </div>


    <div id="uploadForm" >
      <br />
      <form action="PostUserImage" method="post" enctype="multipart/form-data">
        <span class="glyphicon glyphicon-folder-open"></span>     
        <label for="uploadFile" id="lab-file">上传头像</label>
        <input id="uploadFile" type="file" name="filepath" required="required" style="display: none;"/>
        <input type="submit" class="btn btn-default" name="photo" value="确定" />
      </form> 
      
    </div>
  </div>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

</body>
</html>


