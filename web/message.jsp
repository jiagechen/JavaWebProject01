<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/21
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<%@ page import="java.util.Map" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|私信</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/message.css">
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
    <li><a href="MessageAdmin" style="color: #de4b15;">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="UserInfo">个人中心</a></li>
    <li><a href="userIndex.jsp">返回主页</a></li>
  </ul>
</div>


<!-- 修改商品信息、在商城中删除该商品 -->
<div class="panel panel-default"style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-heading" align="center" style="font-size: 25px;">私信</div>
  <div class="panel-body">
    <div class="list-group">
<%
	List<Map<String,String>> messages = (List<Map<String,String>>)session.getAttribute("messages");
    for(int i = 0; i < messages.size(); i++){
%>

	  <button type="button" class="list-group-item"><%=messages.get(i).get("sender") %>&nbsp;<span style="float: none;" class="badge"><%=Integer.parseInt(messages.get(i).get("count")) %></span>
        <a href="MessageChat?operation=update&recieverId=<%= messages.get(i).get("sender")%>"><span class="glyphicon glyphicon-comment"></span></a>
      </button>

<%		
	}

%>
      
    </div>

<%		
	PageDeal pageDeal = (PageDeal)session.getAttribute("page");
    int curPage = pageDeal.getCurPage();
    int totalPage = pageDeal.getTotalPage();
%>

    <!-- 分页 -->
    <div id="barcon2"  style="margin-bottom: 30px;background-color: white;padding-left: 467px;">
      <table class="barcon2-font">
        <tr>
                          <td style="padding-left: 100px;"><a href="MessageAdmin?curPage=<%=curPage == 1 ? 1 : curPage - 1%>" id="prePage"><span class="glyphicon glyphicon-menu-left"></span></a></td>
          <td>&nbsp;&nbsp;|&nbsp;第<span class="badge"><%=curPage %></span>页</td>
          <td>&nbsp;&nbsp;&nbsp;共<span class="badge"><%=totalPage %></span>页&nbsp;|&nbsp;&nbsp;</td>
          <td><a href="MessageAdmin?curPage=<%=curPage == totalPage ? curPage : curPage + 1%>" id="prePage"><span class="glyphicon glyphicon-menu-right"></span></a></td>

          </form>
                          
        </tr>
      </table>
    </div>

  </div>
</div>




<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>

