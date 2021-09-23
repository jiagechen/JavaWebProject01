<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/21
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.*" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|商品信息</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/commodityInfo.css">
  <style>
			.intro .text a{
				color: wheat;
			}
			.intro .text a:hover{
				color: gainsboro;
				transition: 0.3s;
			}
		</style>
</head>
<body background="img/shanshui.png" style="background-attachment: fixed; background-size: 100% 100%">
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

<%
    Commodity cmdt = (Commodity)session.getAttribute("cmdt");
    
%>

<div class="bg">
  <div class="show">
    <div class="pictures">
      <div class="pic">

       <img class="big" src="ShowItemImage?itemId=<%= cmdt.getCommodityId()%>">
      </div>
    </div>

    <div class="imf">
      <dl>
        <dt class="ti">
          <%=cmdt.getCommodityName() %>

        </dt>
        <dd class="ti">物品类型:</br><span><%=cmdt.getCommodityType() %></span></dd>



        <dd class="ti">价格:</br><span id="price">&#165 <%=cmdt.getPrice() %></span></dd>

        <dd class="ti">卖家学号:</br><span><%=cmdt.getSellId() %></span></dd>

        
<%
    if(cmdt.getCommodityStatus() == 2){
%>
        <dd class="ti">商品状态:</br><span><%="正在交易" %></span></dd><br/>
        <div class="btn-group" role="group" aria-label="...">
          <form action="MessageChat" method="get" style="display: inline;">
            <input type="hidden" value="<%= cmdt.getSellId()%>" name="recieverId"/>
            <input type="submit" class="btn btn-default" value="联系卖家"/>
          </form>
          <form action="AddComplaint" method="get" style="display: inline;">
            <input type="hidden" name="operation"value="request"/>             
            <input type="submit" class="btn btn-default" value="投诉"/>          
          </form>
        </div>
<%
    }else if(cmdt.getCommodityStatus()==3){
%>
        <dd class="ti">商品状态:</br><span><%="可以购买" %></span></dd><br/>
        <div class="btn-group" role="group" aria-label="...">
          <form action="DealManage" method="get" style="display: inline;">
            <input type="submit" class="btn btn-default" value="购买"/>      
            <input type="hidden" name="operation" value="buy"/>
            <input type="hidden" name="cmdtId" value="<%=cmdt.getCommodityId()%>"/>
            
          </form>
        
          <form action="MessageChat" method="get" style="display: inline;">
            <input type="hidden" value="<%= cmdt.getSellId()%>" name="recieverId"/>
            <input type="submit" class="btn btn-default" value="联系卖家"/>
          </form>
          <form action="AddComplaint" method="get" style="display: inline;">
            <input type="hidden" name="operation"value="request"/>             
            <input type="submit" class="btn btn-default" value="投诉"/>          
          </form>
        </div>
<%    	
    }else if(cmdt.getCommodityStatus()==4){
%>

        <dd class="ti">商品状态:</br><span><%="交易完成" %></span></dd><br/>
        <div class="btn-group" role="group" aria-label="...">
          <form action="MessageChat" method="get" style="display: inline;">
            <input type="hidden" value="<%= cmdt.getSellId()%>" name="recieverId"/>
            <input type="submit" class="btn btn-default" value="联系卖家"/>
          </form>
          <form action="AddComplaint" method="get" style="display: inline;">
            <input type="hidden" name="operation"value="request"/>             
            <input type="submit" class="btn btn-default" value="投诉"/>          
          </form>
        </div>

<%    	
    }
%>

          
      </dl>
    </div>

  </div>
  <div class="intro">
    <div class="text">
      <h3>商品详情介绍</h3>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=cmdt.getDescription() %></p>
    </div>
  </div>
  <!-- 评论 -->
  
  
  
	<div class="intro">
		<div class="text">
			<h3>评论</h3>
			<table class="table" style="color:white">
<%
      List<Comment> comments = (List<Comment>)session.getAttribute("comments");
      String uId = ((User)session.getAttribute("user")).getId();
      for(int i = 0; i < comments.size(); i++){
    	  Comment com = comments.get(i);
    	  if(com.getId().equals(uId)){
  %>
  			  
			  <!-- 第1行 -->
			  <tr align="center">
			  	<td><%=i+1 %></td>
			  	<td><%=com.getId() %>：</td>
			  	<td  colspan="4"><%=com.getContent() %></td>
				
				<td><%=com.getLikeNum() %>&nbsp;<a title="点赞" onclick="return success()" href="ChangeCom?cmdtId=<%=com.getCommodityId()%>&topicId=<%=com.getTopicId()%>&operation=modify">
				        <span class="glyphicon glyphicon-thumbs-up"></span></a>
				<a title="删除评论"  onclick="return confirm('确定删除该评论？')" href="ChangeCom?cmdtId=<%=com.getCommodityId()%>&topicId=<%=com.getTopicId()%>&operation=delete">
				        <span class="glyphicon glyphicon-trash"></span></a>
				</td>
			  </tr>
<% 		  
    	  }else{
%>

              <!-- 第2行 -->
			  <tr align="center">
			  	<td><%=i+1 %></td>
			  	<td><%=com.getId() %>：</td>
			  	<td colspan="4"><%=com.getContent() %></td>
			
				<td><%=com.getLikeNum() %>&nbsp;<a title="点赞"  onclick="return success()"href="ChangeCom?cmdtId=<%=com.getCommodityId()%>&topicId=<%=com.getTopicId()%>&operation=modify">
				        <span class="glyphicon glyphicon-thumbs-up"></span></a>
				</td>
			  </tr>

<%    		  
    	  }
      }
%>			  
			 
			  
			</table>
			  
		</div>
	</div>
  
</div>

<div class="message">
  <h2 align="center" style="color: white;">评论</h2>
  <div id="div1">
    <form action="AddComment" method="get">
      <textarea name="content"cols="130" rows="8" id="txt"></textarea><br />
      <div class="btn-group" role="group" aria-label="...">
        <input type="submit" class="btn btn-default"
                style="width: 70px;height: 30px;font-size: 15px;" value="发布"              
                />
      </div>
    </form>
    <ul id="ul1">
    </ul>
  </div>
</div>





<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    function success(){
        alert('点赞成功');
    }
</script>
</body>
</html>

