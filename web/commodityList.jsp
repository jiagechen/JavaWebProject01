<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/14
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|商品列表</title>
  <link rel="stylesheet" href="css/commodityList.css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
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
<div id="nav">
  <ul  >
    <li><a href="CommodityList" style="color: #de4b15;">浏览商品</a></li>
    <!-- <li><a href="#">注册</a></li> -->   
    <li><a href="MessageAdmin">私信&nbsp;<span class="badge"></span></a></li>
    <li><a href="UserInfo">个人中心</a></li>
	<li><a href="userIndex.jsp">返回主页</a></li>
  </ul>
</div>







<!-- 名称搜索 -->
<div id="bg1">
  <div id="search">
    <div id="search-name">
      <form action="CommodityList">
        <input type="text" name="itemName" placeholder="输入物品名称搜索" class="text">
        <input type="hidden" name="operation" value="getCmdtByName">                
        

           <input type="submit" class="btn btn-default" >
 
           </span>
        
      </form>
    </div>
  </div>
  <!-- 分类 -->
  <div id="classify">
    <div id="selected">
      <p>当前：</p>
    </div>
    <form id="famtable" action="CommodityList">
      <!-- <input type="submit" value="教材、书籍">
      <input type="submit" value="衣物">
      <input type="submit" value="学习用品">
      <input type="submit" value="生活用品">
      <input type="submit" value="电子产品">
      <input type="submit" value="其他物品"> -->
      <input type="hidden" name="operation" value="getCmdtByType">                      
      <div class="btn-group" role="group" aria-label="...">
        <input type="submit" class="btn btn-default" style="height:33px" name="itemType" value="学习用品">
        <input type="submit" class="btn btn-default" style="height:33px" name="itemType" value="运动器件">
        <input type="submit" class="btn btn-default" style="height:33px" name="itemType" value="生活用品">
        <input type="submit" class="btn btn-default" style="height:33px" name="itemType" value="电子产品">
        <input type="submit" class="btn btn-default" style="height:33px" name="itemType" value="其他物品">
        <!-- 
        <button type="button" class="btn btn-default">学习用品</button>
        <button type="button" class="btn btn-default">运动器件</button>
        <button type="button" class="btn btn-default">生活用品</button>
        <button type="button" class="btn btn-default">电子产品</button>
        <button type="button" class="btn btn-default">其他</button> -->
      </div>
    </form>

  </div>
</div>

<%
    PageCommodity itemPage = (PageCommodity) session.getAttribute("page");
    List<Commodity> items = (List<Commodity>) session.getAttribute("items");
    int currentPage = itemPage.getCurPage();
    int totalPage = itemPage.getTotalPage();
    int totalCount = itemPage.getTotalCount();
    int itemSize = items.size();
%>
<!-- 物品展示 -->
<div id="bg2">
  <div class="showitem">
    <!-- url路径+一页的数量 -->
    <div class="items">
      
            <%
                for(int i=0;i<itemSize&&i<3;i++){
            %>
            <div class="item">
                <div class="picture">
                    <a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><img src="ShowItemImage?itemId=<%= items.get(i).getCommodityId()%>"/></a>
                </div>
                <div class="itemname">
                    <p><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getCommodityName()%></a></p>
                </div>
                <li><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getDescription()%></a></li>
            </div>
            <%
                }
            %>      
     
    </div>
  </div>
  <div class="showitem">
    <!-- url路径+一页的数量 -->
    <div class="items">
           
            <%
                for(int i=3;i<itemSize&&i<6;i++){
            %>
            <div class="item">
                <div class="picture">
                    <a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><img src="ShowItemImage?itemId=<%= items.get(i).getCommodityId()%>"/></a>
                </div>
                <div class="itemname">
                    <p><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getCommodityName()%></a></p>
                </div>
                <li><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getDescription()%></a></li>
            </div>
            <%
                }
            %>      
           
    </div>
  </div>
  <div class="showitem">
    <!-- url路径+一页的数量 -->
    <div class="items">
               
            <%
                for(int i=6;i<itemSize&&i<9;i++){
            %>
            <div class="item">
                <div class="picture">
                    <a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><img src="ShowItemImage?itemId=<%= items.get(i).getCommodityId()%>"/></a>
                </div>
                <div class="itemname">
                    <p><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getCommodityName()%></a></p>
                </div>
                <li><a target="_blank" href="CommodityInfo?itemId=<%=items.get(i).getCommodityId()%>"><%=items.get(i).getDescription()%></a></li>
            </div>
            <%
                }
            %>      
           
    </div>
  </div>
</div>


<div id="barcon2" style="margin-bottom: 30px;background-color: white;">
	<table class="barcon2-font">
		<tr>
            <td style="padding-left: 100px;"><a href="CommodityList?currentPage=<%=currentPage==1 ? 1 : currentPage - 1%>&operation=<%= session.getAttribute("operation") %>" id="prePage"><span class="glyphicon glyphicon-menu-left"></span></a></td>
            <td>&nbsp;&nbsp;|&nbsp;第<span class="badge"><%= currentPage %></span>页</td>
            <td>&nbsp;&nbsp;&nbsp;共<span class="badge"><%= totalPage %></span>页&nbsp;|&nbsp;&nbsp;</td>
			<td><a href="CommodityList?currentPage=<%=currentPage==totalPage ? currentPage : currentPage+1%>&operation=<%= session.getAttribute("operation") %>" id="prePage"><span class="glyphicon glyphicon-menu-right"></span></a></td>
            <td>
				<form action="CommodityList" style="padding-left: 50px;">
					<input type="text" name="currentPage" onkeyup="onlyNumber(this)" onblur="onlyNumber(this)" placeholder="选择页码" style="height: 35px;width: 65px;font-size: 15px;"/>
					<input type="hidden" name="operation" value=<%= session.getAttribute("operation") %>>
				    <div class="btn-group btn-group-sm" role="group" aria-label="..." style="margin-left: 0;">
					   <button type="submit" class="btn btn-default">GO<tton>
				    </div>
				</form>		
			</td>
		</tr>
	</table>
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

