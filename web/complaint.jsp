<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/24
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.*" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|商品信息</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/complaint.css">
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

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="#">
      欢迎您！<%=((User)session.getAttribute("user")).getName() %>
    </a>

    <a href="Logout" style="margin-left: 100px;">
      退出登录
    </a>
  </div>
</div>
<!-- 2.导航 -->
<%
    if(((User)session.getAttribute("user")).getStatus()==2){
%>

<div id="nav">
  <ul >
    <li><a href="CommodityList">浏览商品</a></li>
    <!-- <li><a href="#">注册</a></li> -->
    <li><a href="MessageAdmin">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="UserInfo">个人中心</a></li>
    <li><a href="userAdmin.jsp">返回主页</a></li>

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

<%
    Commodity cmdt = (Commodity)session.getAttribute("cmdt");  
%>

<div class="bg">
  <div class="show">
    <div class="pictures">
      <div class="pic">

        <img class="big" src="ShowItemImage?itemId=<%= cmdt.getCommodityId()%>" />
      </div>
    </div>
    <div class="imf">
      <dl>
        <dt class="ti" >
           <%=cmdt.getCommodityName() %>
        </dt>
        <dd class="ti">物品类型:</br><span><%=cmdt.getCommodityType() %></span></dd>

        <dd class="ti">价格:</br><span id="price">&#165 <%=cmdt.getPrice() %></span></dd>

        <dd class="ti">卖家学号:</br><span><%=cmdt.getSellId() %></span></dd>
<%
    if(cmdt.getCommodityStatus() == 2){
%>
        <dd class="ti">商品状态:</br><span><%="正在交易"%></span></dd><br/>

<%    	
    }else if(cmdt.getCommodityStatus() == 3){
%>
        <dd class="ti">商品状态:</br><span><%="可以购买"%></span></dd><br/>

<%   	
    }else if(cmdt.getCommodityStatus() == 4){
%>
        <dd class="ti">商品状态:</br><span><%="交易完成"%></span></dd><br/>

<%   	
    }
%>
      </dl>
    </div>
  </div>
</div>
<div class="message">
  <h2 align="center">投诉内容</h2>
  <div id="div1">
  <%
      String content = (String)session.getAttribute("content");
      session.removeAttribute("content");
  %>
    <form action="AddComplaint" method="get">
      <textarea cols="130" rows="8" id="txt" name="content"><%=content==null?"":content %></textarea><br />
      <div class="btn-group" role="group" aria-label="...">
<%
    if(content==null){
%>

        <input type="submit" class="btn btn-default"
              style="width: 70px;height: 30px;font-size: 15px;"
              value="确认"/>      

<%   	
    }
%>
      
      </div>
    </form>
    <ul id="ul1">
    </ul>
  </div>
</div>
<!-- 留白 -->
<div style="height: 50px; background: white;"></div>




<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>

