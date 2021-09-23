<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|个人信息</title>
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
<div id="nav">
  <ul  >
    <li><a href="CommodityList">浏览商品</a></li>
    <!-- <li><a href="#">注册</a></li> -->
    <li><a href="addCommodity.jsp">增添商品</a></li>
    <li><a href="MessageAdmin">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="userIndex.jsp">返回主页</a></li>

  </ul>
</div>







<div class="panel panel-default" style="margin-left: 50px;margin-right: 50px;">
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
          <p>学号：<%=user.getId() %> <br />
            昵称：<%=user.getName() %> &nbsp;<a href="modifyUserInfo.jsp" title="修改昵称"><span class="glyphicon glyphicon-pencil" style="float: none;"></span></a> <br />
            学院：<%=user.getSchool() %>  &nbsp;<a href="modifyUserInfo.jsp" title="修改学院"><span class="glyphicon glyphicon-pencil" style="float: none;"></span></a><br /></p>
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


<!-- 红色是审核未通过 -->
<!-- 黄色是待处理 -->
<!-- 绿色是已发布-->
<!-- 蓝色是待发布 -->



<div class="panel panel-default"style="margin-left: 50px;margin-right: 50px;">
  
  <div class="panel-heading" align="center">出售
    
      <div class="btn-group" role="group" aria-label="..."style="float: right;">
        <form action="addCommodity.jsp" method="get">
	      <button type="submit" class="btn btn-default">添加</button>
	    </form>
      </div>
    
  </div>
  <div class="panel-body">
    <div class="list-group">

<%
    List<Commodity> sellItem = (List<Commodity>)session.getAttribute("sellItem");
    Commodity cmdt;    
    for(int i = 0; i < sellItem.size(); i++){
    	cmdt = sellItem.get(i);
    	if(cmdt.getCommodityStatus()==0){//待发布
%>
    
      <button type="submit" class="list-group-item list-group-item-info"><%=cmdt.getCommodityName() %>
        <a href="DealManage?operation=delete&cmdtId=<%= cmdt.getCommodityId()%>" title="删除此商品" onClick="return confirm('确定删除?')";><span class="glyphicon glyphicon-trash"></span></a>
        <a href="DealManage?operation=post&cmdtId=<%= cmdt.getCommodityId()%>" title="发布此商品" onClick="return confirm('确定发布该商品?')";><span class="glyphicon glyphicon-cloud-upload"></span></a>
        <a href="CommodityMdfy?itemId=<%= cmdt.getCommodityId()%>"title="修改商品信息"><span class="glyphicon glyphicon-pencil"></span></a>
        <a href="CommodityInfo?itemId=<%=cmdt.getCommodityId() %>" title="查看商品详情"><span class="glyphicon glyphicon-th-list"></span></a>     
      </button>

    
<%    		
    	}else if(cmdt.getCommodityStatus()==2){//正在交易
%>

      
      <button type="submit" class="list-group-item list-group-item-warning"><%=cmdt.getCommodityName() %>
        <a href="DealManage?operation=cancle&cmdtId=<%= cmdt.getCommodityId()%>" title="取消交易" onclick="return confirm('确定取消交易？')"><span class="glyphicon glyphicon-remove"></span></a> 
        <a href="DealManage?operation=delivery&cmdtId=<%= cmdt.getCommodityId()%>"title="发货"onClick="return confirm('确定发货?')";><span class="glyphicon glyphicon-plane"></span></a>
        <a href="CommodityInfo?itemId=<%=cmdt.getCommodityId() %>" title="查看商品详情"><span class="glyphicon glyphicon-th-list"></span></a>     
      </button>
       
<%   		
    	}else if(cmdt.getCommodityStatus()==3){//等待购买
%>
 
      <button type="submit" class="list-group-item list-group-item-success"><%=cmdt.getCommodityName() %>
        <a title="删除此商品" href="DealManage?operation=delete&cmdtId=<%= cmdt.getCommodityId()%>"onClick="return confirm('确定删除?')";><span class="glyphicon glyphicon-trash"></span></a>
        <a href="CommodityMdfy?itemId=<%= cmdt.getCommodityId()%>" title="修改商品信息"><span class="glyphicon glyphicon-pencil"></span></a>
        <a href="CommodityInfo?itemId=<%=cmdt.getCommodityId() %>" title="查看商品详情"><span class="glyphicon glyphicon-th-list"></span></a>     
      </button>
<%
    	}else if(cmdt.getCommodityStatus()==4){//交易完成    		
%>
      <button type="submit" onclick="alert('交易完成！')" class="list-group-item list-group-item"><%=cmdt.getCommodityName() %>
        <a href="CommodityInfo?itemId=<%=cmdt.getCommodityId() %>" title="查看商品详情"><span class="glyphicon glyphicon-th-list"></span></a>     
      </button>    
<%          		
    	}else if(cmdt.getCommodityStatus()==5){//未通过审核
%>
    
      <button type="submit" onclick="alert('该商品未通过审核！')" class="list-group-item list-group-item-danger"><%=cmdt.getCommodityName() %>
        <a href="DealManage?operation=delete&cmdtId=<%= cmdt.getCommodityId()%>" onClick="return confirm('确定删除?')" title="删除此商品"><span class="glyphicon glyphicon-trash"></span></a>      
      </button>

<%        	
    	}
    }
%>

    </div>


<%
    PageCommodity pageSell = (PageCommodity)session.getAttribute("pageSell");
    int sellCurPage = pageSell.getCurPage();
    int sellTotalPage = pageSell.getTotalPage();
%>

    <!-- 分页 -->
    <div id="barcon2"  style="margin-bottom: 30px;background-color: white;padding-left: 467px;">
      <table class="barcon2-font">
        <tr>
          <td style="padding-left: 100px;"><a href="UserInfo?sellCurPage=<%=sellCurPage==1 ? 1 : sellCurPage - 1%>" id="prePage"><span class="glyphicon glyphicon-menu-left"></span></a></td>
          <td>&nbsp;&nbsp;|&nbsp;第<span class="badge"><%=sellCurPage %></span>页</td>
          <td>&nbsp;&nbsp;&nbsp;共<span class="badge"><%=sellTotalPage %></span>页&nbsp;|&nbsp;&nbsp;</td>
          <td><a href="UserInfo?sellCurPage=<%=sellCurPage==sellTotalPage ? sellTotalPage : sellCurPage + 1%>" id="prePage"><span class="glyphicon glyphicon-menu-right"></span></a></td>

          </form>
                          
        </tr>
      </table>
    </div>

  </div>
</div>


<!-- 红色是待付款 -->
<!-- 绿色是已付款 -->


<div class="panel panel-default"style="margin-left: 50px;margin-right: 50px;">
  <div class="panel-heading" align="center">购买</div>
  <div class="panel-body">
    <div class="list-group">

<%
    List<Deal> buyItem = (List<Deal>)session.getAttribute("buyItem");
    Deal deal;    
    for(int i = 0; i < buyItem.size(); i++){
	    deal = buyItem.get(i);
	    if(deal.getDealStatus()==1){   	    	
%>

      <button type="submit" class="list-group-item list-group-item-danger"><%=deal.getCommodityName() %>
        <a title="联系卖家" href="MessageChat?recieverId=<%= deal.getSellId()%>"><span class="glyphicon glyphicon-comment"></span></a> 
        <a href="CommodityInfo?itemId=<%=deal.getCommodityId() %>" title="查看商品详情"><span class="glyphicon glyphicon-th-list"></span></a>     
      </button>

<%
	    }else{
%>

      <button type="submit" class="list-group-item list-group-item-success"><%=deal.getCommodityName() %>
        <a title="联系卖家" href="MessageChat?recieverId=<%= deal.getSellId()%>"><span class="glyphicon glyphicon-comment"></span></a> 
        <a href="CommodityInfo?itemId=<%=deal.getCommodityId() %>" title="查看商品详情"><span class="glyphicon glyphicon-th-list"></span></a>     
      </button>

<%
	    }
    }	
%>

    </div>

<%
    PageDeal pageBuy = (PageDeal)session.getAttribute("pageBuy");
    int buyCurPage = pageBuy.getCurPage();
    int buyTotalPage = pageBuy.getTotalPage();
%>
    <!-- 分页 -->
    <div id="barcon2"  style="margin-bottom: 30px;background-color: white;padding-left: 467px;">
      <table class="barcon2-font">
        <tr>
          <td style="padding-left: 100px;"><a href="UserInfo?buyCurPage=<%=buyCurPage==1 ? 1 : buyCurPage - 1%>" id="prePage"><span class="glyphicon glyphicon-menu-left"></span></a></td>
          <td>&nbsp;&nbsp;|&nbsp;第<span class="badge"><%=buyCurPage %></span>页</td>
          <td>&nbsp;&nbsp;&nbsp;共<span class="badge"><%=buyTotalPage %></span>页&nbsp;|&nbsp;&nbsp;</td>
          <td><a href="UserInfo?buyCurPage=<%=buyCurPage==buyTotalPage ? buyTotalPage : buyCurPage + 1%>" id="prePage"><span class="glyphicon glyphicon-menu-right"></span></a></td>
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


