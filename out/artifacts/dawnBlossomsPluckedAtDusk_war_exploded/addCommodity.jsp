<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/14
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.*" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|出售</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/addCommodity.css">
  <script src="js/jquery.min.js"></script>
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
  <ul >
    <li><a href="CommodityList">浏览商品</a></li>
    <!-- <li><a href="#">注册</a></li> -->
    <li><a href="MessageAdmin">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="UserInfo">个人中心</a></li>
    <li><a href="userIndex.jsp">返回主页</a></li>

  </ul>
</div>

<div class="page-header" id="content">
  <h1 align="center">出售 <small>SELL</small></h1>
</div>

<div class="panel panel-default" style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-body">


    <form action="CommodityPost" id="findform" name="addCommodity" method="post" enctype="multipart/form-data">
      <fieldset>
        <legend>请填写商品相关信息</legend>
        <p>
          <label for="item-name">商品名称：</label>
          <input name="itemName" id="item-name" type="text" required="required" />
        </p>
        <p>
          <label for="item-loc">期望价格：</label>
          <input name="price" id="item-loc" type="text" placeholder="请输入数字或小数点（单位/元）" onkeyup="onlyNumber(this)" onblur="onlyNumber(this)" required="required" />
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
        </p>
        <p class="upload">
          <label>实物图片：</label>
          <input id="item-img1" type="file" name="picture" accept="image/*"  />
          <!-- <input type="text" id="text" disabled="disabled" /> -->
          <label id="file1" for="item-img1">上传文件</label>

        </p>
        <p>
          <label for="item-desc" >物品描述：</label><br />
          <textarea id="item-desc" name="desc" ></textarea>
        </p>
        <p id="footer" style="background-color: white;">
          <input id="submit" type="submit" value="提交信息" />
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
<script language="JavaScript" >
 function 
  onlyNumber(obj){
 //得到第一个字符是否为负号
 var 
  t = obj.value.charAt(0); 
 //先把非数字的都替换掉，除了数字和. 
 obj.value = obj.value.replace(/[^\d\.]/g,
 ''
 ); 
 //必须保证第一个为数字而不是. 
 obj.value = obj.value.replace(/^\./g,
 ''
 ); 
 //保证只有出现一个.而没有多个. 
 obj.value = obj.value.replace(/\.{2,}/g,
 '.'
 ); 
 //保证.只出现一次，而不能出现两次以上 
 obj.value = obj.value.replace(
 '.'
 ,
 '$#$'
 ).replace(/\./g,
 ''
 ).replace(
 '$#$'
 ,
 '.'
 );
 }
 </script>
</body>
</html>

