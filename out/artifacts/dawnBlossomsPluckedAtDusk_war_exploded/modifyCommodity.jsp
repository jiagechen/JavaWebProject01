<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/22
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.*" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|修改商品信息</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/modifyCommodity.css">
</head>
<body onload="change();">
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
      欢迎您！<%=user.getName() %>
    </a>

    <a href="Logout" style="margin-left: 100px;">
      退出登录
    </a>
  </div>
</div>
<!-- 2.导航 -->
<div id="nav">
  <ul >
    <li><a href="CommodityList">浏览商品</a></li>
    <!-- <li><a href="#">注册</a></li> -->
    <li><a href="MessageAdmin">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="UserInfo">个人中心&nbsp;<span class="badge"></span></a></li>
    <li><a href="userAdmin.jsp">返回主页</a></li>

  </ul>
</div>


<div class="page-header" id="content">
  <h1 align="center">修改商品信息 <small>Modity</small></h1>
</div>

<div class="panel panel-default" style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-body">

<%
    Commodity cmdt = (Commodity)session.getAttribute("cmdt");
%>
    <form action="CommodityMdfy" id="findform" name="mdfyCommodity" method="post" enctype="multipart/form-data">
      <fieldset>
        <legend>请填写商品相关信息<span class="glyphicon glyphicon-arrow-down"></span></legend>
        <p>
          <label for="item-name">商品名称：</label>
          <input name="itemName" id="item-name" type="text" required="required" placeholder="<%=cmdt.getCommodityName()%>"/>
        </p>
        <p>
          <label for="item-loc">期望价格：</label>
          <input name="price" id="item-loc" type="text" required="required" placeholder="<%=cmdt.getPrice()%>"/>
        </p>
        <p>
          <label for="item-type">物品类别：</label>
          <select id="item-type" name="type">
            <option>学习用品</option>
            <option>运动器件</option>
            <option>生活用品</option>
            <option>电子产品</option>
            <option>其他</option>
          </select>
          <!-- <input  type = "button"  value = "修改"  onclick = "change()" /> -->
        </p>
        <p class="upload">
          <label>实物图片：</label>
          <input id="item-img1" type="file" name="picture" accept="image/*" required="required" />
          <!-- <input type="text" id="text" disabled="disabled" /> -->
          <label id="file1" for="item-img1">上传文件</label>

        </p>
        <p>
          <label for="item-desc">物品描述：</label><br />
          <textarea id="item-desc" name="desc" ><%=cmdt.getDescription()%></textarea>
        </p>
        <p id="footer" style="background-color: white;">
          <input id="submit" type="submit" value="确定修改" />
          <input id="reset" type="reset" value="重置信息" />

        </p>
      </fieldset>
    </form>



  </div>
</div>





<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<script  type = "text/javascript" >
  function change(){
	  
    document.getElementById("item-type")[2].selected=true;
  }
</script >
</body>
</html>

