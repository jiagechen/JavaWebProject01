<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/24
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|个人信息</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/dealAdmin.css" />
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
    User user = ((User)session.getAttribute("user"));
%>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="#">
      欢迎您！ <%=user.getName() %> (管理员)
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
      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" style="color: #de4b15;">
        信息管理 <span class="caret" style="color: #de4b15;"></span>
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





<div class="panel panel-default"style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-heading" align="center">交易管理</div>
  <div class="panel-body">
    <table class="table table-hover">
      <!-- 第1行 -->
      <tr align="center">
        <td >#</td>
        <td>商品名称</td>
        <td>卖家学号</td>
        <td>买家学号</td>
        <td>交易时间</td>
        <td>交易状态</td>
        <td>删除记录</td>
      </tr>      
<%
    List<Deal> deals = (List<Deal>)session.getAttribute("deals");
    for(int i = 0; i < deals.size(); i++){
    	Deal deal = deals.get(i);
%>

      <tr align="center">
        <td><%=i+1 %></td>
        <td><%=deal.getCommodityName() %></td>
        <td><%=deal.getSellId() %></td>
        <td><%=deal.getBuyId() %></td>
        <td><%=deal.getDealTime() %></td>
        <td><%=deal.getDealStatus()==1?"正在处理":"交易完成" %></td>
        <td><a href="DealManage?operation=deleteDeal&cmdtId=<%= deal.getCommodityId()%>"><span class="glyphicon glyphicon-trash"></span></a></td>
      </tr>

<%
    }
%>


    </table>

<%
    int curPage = ((PageDeal)session.getAttribute("page")).getCurPage();
    int totalPage = ((PageDeal)session.getAttribute("page")).getTotalPage();
%>

    <!-- 分页 -->
    <div id="barcon2"  style="margin-bottom: 30px;background-color: white;padding-left: 467px;">
      <table class="barcon2-font">
        <tr>
                          <td style="padding-left: 100px;"><a href="DealAdmin?curPage=<%=curPage==1 ? 1 : curPage - 1%>" id="prePage"><span class="glyphicon glyphicon-menu-left"></span></a></td>
          <td>&nbsp;&nbsp;|&nbsp;第<span class="badge"><%=curPage %></span>页</td>
          <td>&nbsp;&nbsp;&nbsp;共<span class="badge"><%=totalPage %></span>页&nbsp;|&nbsp;&nbsp;</td>
          <td><a href="DealAdmin?curPage=<%=curPage==totalPage ? curPage : curPage+1%>" id="prePage"><span class="glyphicon glyphicon-menu-right"></span></a></td>

          </form>
                          
        </tr>
      </table>
    </div>


  </div>


























  <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

</body>
</html>

