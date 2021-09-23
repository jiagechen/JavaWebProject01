<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|修改个人信息</title>
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
<%
    if(user.getStatus()==2){
%>

<div id="nav">
  <ul >
    <li><a href="CommodityList">浏览商品</a></li>
    <!-- <li><a href="#">注册</a></li> -->
    <li><a href="MessageAdmin">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="UserInfo">个人中心</a></li>
    <li><a href="userIndex.jsp">返回主页</a></li>

  </ul>
</div>

<%
    }else{
%>

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
    <li><a href="adminInfo.jsp">个人中心<span class="badge"></span></a></li>
    <li><a href="adminIndex.jsp">返回首页</a></li>

  </ul>
</div>

<%    	
    }
%>








<div class="panel panel-default" style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-heading" align="center">修改个人信息</div>
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
          <form action="MdfyUserInfo">
          <p>学号：<%=user.getId() %> <br />
            昵称：<input placeholder="输入您的昵称" name="name" value="<%=user.getName() %>" style="height: 30px;" /> <br />
            学院：<select id="college" name="college" style="font-size: 20px;height: 30px;">
              <option>机械工程学院</option>
              <option>化工学院</option>
              <option>电子工程与光电技术学院</option>
              <option>计算机科学与工程学院</option>
              <option>能源与动力工程学院</option>
              <option>经济管理学院</option>
              <option>自动化学院</option>
              <option>理学院</option>
              <option>外国语学院</option>
              <option>公共事务学院</option>
              <option>材料科学与工程学院</option>
              <option>环境与生物工程学院</option>
              <option>设计艺术与传媒</option>
              <option>钱学森学院</option>
              <option>知识产权学院</option>
              <option>马克思主义学院</option>
              <option>国际教育学院</option>
              <option>继续教育学院</option>
              <option>中法工程师学院</option>
              <option>紫金学院</option>
              <option>泰州科技学院</option>
            </select> <br />
            <input onClick="return confirm('确定您的个人修改?')" style="float: left;width: 310px;font-size: 20px;" type="submit" class="btn btn-default" name="modify" value="确定修改" />
          </p>
            </form>
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


