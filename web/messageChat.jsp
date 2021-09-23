<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/21
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|XXX</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/chat.css">
</head>
<body onscroll="aa()">
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

<div class="page-header" id="content" style="height:30px">
  <h2 align="center">To&nbsp; <%=session.getAttribute("recieverId") %><small>&nbsp;&nbsp;from <%=session.getAttribute("senderId") %></small></h2>
</div>

<div class="panel panel-default" style=" margin-left: 50px;margin-right: 50px;">
  <div class="panel-body" style="font-size:16px;">

    <div id="scroll_div" align="center"  style="overflow-y: scroll;width: 650px;height: 350px;margin-left: 350px;">
<%
    List<Message> messages = (List<Message>)session.getAttribute("messages");
    String senderId = (String)session.getAttribute("senderId");
    Message message;
    for(int i = 0; i < messages.size(); i++){
    	message = messages.get(i);
    	if(!message.getSenderId().equals(senderId)){
%>

      <!-- Left -->
      <div class="sender">
        <div>
          <img src="img/default.jpg">
        </div>
        <div>
          <div class="left_triangle"></div>
          <span><%=message.getMessageContent() %></span>
        </div>
      </div>    
      		
<%    	}else{
%>

      <!-- Right -->
      <div class="receiver">
        <div>
          <img src="ShowUserImage">
        </div>
        <div>
          <div class="right_triangle"></div>
          <span><%=message.getMessageContent() %></span>
        </div>
      </div>
      	
<%        }
    }

%>    

      </div>
    </div>

    <br />
    <form action="PostMessage" method="get">
	  <div align="center" style="margin-bottom: 0px;">
        <p>
	      <textarea id="item-desc" name="content" style="width: 500px;height: 60px;"></textarea>
        </p>
	    <input class="btn btn-default" id="submit" type="submit" value="发送"style="width: 150px;font-size: 25px;" />
	  </div>
    </form>

  </div>
</div>





<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    $('#scroll_div').scrollTop( $('#scroll_div')[0].scrollHeight);
    // $('#scroll_div').scrollLeft( $('#scroll_div')[0].scrollWidth);
  });
</script>
<script type="text/javascript">
			 $(document).ready(function(){
			    $('#scroll_div').scrollTop( $('#scroll_div')[0].scrollHeight);
			    // $('#scroll_div').scrollLeft( $('#scroll_div')[0].scrollWidth);
			 });
			</script>
			<script>
			        function getCookie(c_name) {
			        //从cookie中获取滚动条距离顶端位置
			            if (document.cookie.length > 0) {
			                var aCookie = document.cookie.split(";");
			                for (var i = 0; i < aCookie.length; i++) {
			                    var aCrumb = aCookie[i].split("=");
			                    if (aCrumb[0] == c_name) {
			                        return unescape(aCrumb[1]);
			                    }
			                }
			            }
			            return ""
			        }
			        function setCookie(c_name, value) {
			        //给cookie赋值
			            document.cookie = c_name + "=" + escape(value) + ";";
			        }
			        function aa() {
			            //页面指定了DTD，即指定了DOCTYPE时，使用document.documentElement。
			            //页面没有DTD，即没指定DOCTYPE时，使用document.body。
			            setCookie("pos", document.documentElement.scrollTop)
			        }
			        function checkMe() {
			            //给scrollTop赋值
			            if (getCookie("pos") != "") {
			                document.documentElement.scrollTop = getCookie("pos") + "px"
			            }
			        }
			        checkMe()
			    </script>
</body>
</html>

