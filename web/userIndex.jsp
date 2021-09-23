<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/27
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|欢迎您！</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/login.css">
  <link rel="stylesheet" href="css/commodityList.css"/>
  
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
  <ul  >
    <li><a href="CommodityList">浏览商品</a></li>
    <li><a href="MessageAdmin">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="UserInfo">个人中心</a></li>
    <li><a href="userIndex.jsp">返回首页</a></li>

  </ul>
</div>




<!-- 轮播图 -->
<div id="carousel-example-generic" class="carousel slide damu-carousel" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <a href="CommodityList"><img src="img/userIndex_bgg1.png" ></a>

      <div class="carousel-caption">
        <!-- 文字 -->

      </div>



    </div>


    <div class="item">
      <a href="CommodityList"> <img src="img/userIndex_bgg2.png" ></a>
      <div class="carousel-caption">

      </div>
    </div>


    <div class="item">
      <a href="MessageAdmin"><img src="img/UserIndex_bgg3.png" ></a>
      <div class="carousel-caption">

      </div>
    </div>

    <div class="item">
      <a href="UserInfo"><img src="img/userIndex_bgg4.png" ></a>
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



<div style="float: right;margin-right: 80px;margin-top: 30px;"> <a href="CommodityList"><img src="img/LOGO1.png" /></a></div>
<div class="jumbotron" style="margin: 0;">
    <h1 style="margin-left: 80px;" >浏览商品&nbsp;<span class="glyphicon glyphicon-arrow-down"></span></h1>
    <p style="margin-left: 80px;">浏览商城二手物品。</p>
    <p style="margin-left: 80px;"><a class="btn btn-primary btn-lg" href="CommodityList" role="button">Learn more</a></p>
  <div id="bg2">
  <div class="showitem">
    <!-- url路径+一页的数量 -->
    <div class="items">
           <%
                List<Commodity> items = (List<Commodity>)session.getAttribute("userLikes");
                for(int i=0;i<3&&i<items.size();i++){     	
            %>
            <div class="item">
                <div class="picture">
                    <a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><img src="ShowItemImage?itemId=<%= items.get(i).getCommodityId()%>"/></a>
                </div>
                <div class="itemname">
                    <p style="font-size: 15px;"><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getCommodityName()%></a></p>
                </div>
                <li><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getDescription()%></a></li>
            </div>
            <%
                }
            %>      
     
      </div>
    </div>
  </div>
</div>






<div style="float: right;margin-right: 80px;margin-top: 30px;"> <a href="CommodityList"><img src="img/LOGO1.png" /></a></div>
<div class="jumbotron" style="margin: 0;">
  <h1 style="margin-left: 80px;" >购买商品&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></h1>
  <p style="margin-left: 80px;">购买商城二手物品。</p>
  <p style="margin-left: 80px;"><a class="btn btn-primary btn-lg" href="CommodityList" role="button">Learn more</a></p>
</div>

<div style="float: right;margin-right: 80px;margin-top: 30px;"> <a href="MessageAdmin"><img src="img/LOGO1.png" /></a></div>
<div class="jumbotron" style="margin: 0;">
  <h1 style="margin-left: 80px;" >私&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></h1>
  <p style="margin-left: 80px;">与卖家私聊，提高购买效率。</p>
  <p style="margin-left: 80px;"><a class="btn btn-primary btn-lg" href="#content" role="button">Learn more</a></p>
</div>

<div style="float: right;margin-right: 80px;margin-top: 30px;"> <a href="UserInfo"><img src="img/LOGO1.png" /></a></div>
<div class="jumbotron" style="margin: 0;">
  <h1 style="margin-left: 80px;" >个人中心&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></h1>
  <p style="margin-left: 80px;">展示个人多种交易状态。</p>
  <p style="margin-left: 80px;"><a class="btn btn-primary btn-lg" href="UserInfo" role="button">Learn more</a></p>
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

