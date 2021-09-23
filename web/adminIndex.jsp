<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/27
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.*" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|欢迎您！</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/login.css">
  <style type="text/css">
    img{
      width: 100%;
      height: 100%;
    }
    .damu-carousel{
      height: 900px;
      overflow: hidden;
    }
  </style>
</head>
<body>

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
  <ul class="nav nav-pills" >
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
    <li><a href="adminInfo.jsp">个人中心</a></li>
    <li><a href="adminIndex.jsp">返回首页</a></li>

  </ul>
</div>




<!-- 轮播图 -->
<div id="carousel-example-generic" class="carousel slide damu-carousel" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <a href="UserAdmin"><img src="img/AdminIndex_bgg1.png" ></a>

      <div class="carousel-caption">
        <!-- 文字 -->

      </div>



    </div>


    <div class="item">
      <a href="DealAdmin"> <img src="img/AdminIndex_bgg2.png" ></a>
      <div class="carousel-caption">

      </div>
    </div>


    <div class="item">
      <a href="CmpltAdmin"><img src="img/AdminIndex_bgg3.png" ></a>
      <div class="carousel-caption">

      </div>
    </div>

   

    ...
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>



<div style="float: right;margin-right: 80px;margin-top: 30px;"> <a href="UserAdmin"><img src="img/LOGO1.png" /></a></div>
<div class="jumbotron" style="margin: 0;">
  <h1 style="margin-left: 80px;" >用户管理&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></h1>
  <p style="margin-left: 80px;">对多次违规用户进行封号处理，对合格用户解封。</p>
  <p style="margin-left: 80px;"><a class="btn btn-primary btn-lg" href="UserAdmin" role="button">Learn more</a></p>
</div>

<div style="float: right;margin-right: 80px;margin-top: 30px;"> <a href="DealAdmin"><img src="img/LOGO1.png" /></a></div>
<div class="jumbotron" style="margin: 0;">
  <h1 style="margin-left: 80px;" >交易管理&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></h1>
  <p style="margin-left: 80px;">查看平台交易信息。</p>
  <p style="margin-left: 80px;"><a class="btn btn-primary btn-lg" href="DealAdmin" role="button">Learn more</a></p>
</div>

<div style="float: right;margin-right: 80px;margin-top: 30px;"> <a href="CmpltAdmin"><img src="img/LOGO1.png" /></a></div>
<div class="jumbotron" style="margin: 0;">
  <h1 style="margin-left: 80px;" >投诉管理&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></h1>
  <p style="margin-left: 80px;">处理用户投诉。</p>
  <p style="margin-left: 80px;"><a class="btn btn-primary btn-lg" href="CmpltAdmin" role="button">Learn more</a></p>
</div>



<!-- 5.尾部 -->
<div id="footer">
  <div  style="color: #999; text-align: center; height: 50px; line-height: 50px;">
    Copyright &copy;2020 朝花夕拾工作小组 All rights reserved.
  </div>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
  $('.carousel').carousel({
    interval: 1000
  })
</script>
</body>
</html>

