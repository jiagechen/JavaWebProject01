<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/24
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|用户管理</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/userAdmin.css" />
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
    User user_ = ((User)session.getAttribute("user"));
%>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="#">
      欢迎您！<%=user_.getName() %> (管理员)
    </a>

    <a href="Logout" style="margin-left: 100px;">
      退出登录
    </a>
  </div>
</div>
<!-- 2.导航 -->
<div id="nav">
  <ul  >
    <li><a href="UserAdmin" style="color: #de4b15;">用户管理</a></li>
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



<div class="panel panel-default"style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-heading" align="center">用户管理</div>
  <div class="panel-body">
    <table class="table table-hover">
      <!-- 第1行 -->
      <tr align="center">
        <td >序号</td>
        <td>用户学号</td>
        <td>用户昵称</td>
        <td>所属学院</td>
        <td>用户状态</td>
        <td>信誉分数</td>
        <td>管理用户状态</td>
      </tr>
      
<%
    List<User> users = (List<User>)session.getAttribute("users");    
    for(int i = 0; i < users.size(); i++){
    	User user = users.get(i);
    	if(user.getStatus()==0){
%>
     
      <tr align="center" class="danger">
        <td><%=i+1 %></td>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getSchool()%></td>
        <td><%="封号"%></td>
        <td><%=user.getReputation()%></td>
        <td><a title="解封" onclick="return confirm('确定要解封该用户？')" href="UserAdmin?operation=remove&uId=<%=user.getId()%>"><span class="glyphicon glyphicon-thumbs-up"></span></a></td>
      </tr>

<%    		
    	}else{
%>

      <tr align="center">
        <td><%=i+1 %></td>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getSchool()%></td>
        <td><%="正常"%></td>
        <td><%=user.getReputation()%></td>
        <td><a title="封号" onclick="return confirm('确定要封禁该用户？')" href="UserAdmin?operation=restrict&uId=<%=user.getId()%>"><span class="glyphicon glyphicon-thumbs-down"></span></td>
      </tr>
      
<% 		
    	}
    }
%>
    </table>




<%
    String curPageStr = (String)session.getAttribute("curPage");
    Integer totalPage = (Integer)session.getAttribute("totalPage");
    int curPage = Integer.valueOf(curPageStr);
%>



    <!-- 分页 -->
    <div id="barcon2"  style="margin-bottom: 30px;background-color: white;padding-left: 467px;">
      <table class="barcon2-font">
        <tr>
          <td style="padding-left: 100px;"><a href="UserAdmin?curPage=<%=curPage==1 ? 1 : curPage - 1%>" id="prePage"><span class="glyphicon glyphicon-menu-left" id="prePage"></a></td>
          <td>&nbsp;&nbsp;|&nbsp;第<span class="badge"><%=curPage %></span>页</td>
          <td>&nbsp;&nbsp;&nbsp;共<span class="badge"><%=totalPage %></span>页&nbsp;|&nbsp;&nbsp;</td>
          <td><a href="UserAdmin?curPage=<%=curPage==totalPage ? curPage : curPage +1 %>" id="prePage"><span class="glyphicon glyphicon-menu-right"></span></a></td>

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

