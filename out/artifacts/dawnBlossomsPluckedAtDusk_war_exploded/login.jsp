<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/10
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>朝花夕拾|用户登录</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/login.css">
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
    <a href="#news">
      <img src="img/LOGO1.png"  />
    </a>
  </div>
  <div class="name">
    <!-- <a href="index.html">
        1669953081@qq.cpm
    </a> -->


    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="#">
      欢迎登录！
    </a>
  </div>
</div>
<!-- 2.导航 -->
<div id="nav">
  <ul class="nav nav-pills">

    <li><a href="register.jsp">注册</a></li>
    <li><a id="login">登录</a></li>
    <li><a href="#news">关于我们</a></li>

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
				      <a href="#news"><img src="img/login_bg1.jpeg" ></a>
					  
				      <div class="carousel-caption">
				        <!-- 文字 -->
						
				      </div>
					  
					  
					  
				    </div>
				    <div class="item">
				      <a href="#news_re"><img src="img/login_bg2.jpg" ></a>
				      <div class="carousel-caption">
				        
				      </div>
				    </div>
					
					
					<div class="item">
					  <a href="#news1"><img src="img/login_bg3.jpg" ></a>
					  <div class="carousel-caption">
					    
					  </div>
					</div>
					
					<div class="item">
					  <a href="#news_re1"><img src="img/login_bg4.jpg" ></a>
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










		<!-- 3.横幅 -->
		<div id="banner" style="background: url(./img/hpBG.jpg);background-attachment: fixed; background-size: 100% 100%">
		
			<!-- <img src="img/about_1.jpg" /> -->
			
			<div class="font">
				<h1 >朝花夕拾</h1>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				留住过去的美好时光</p>
			</div>
		</div>
			
		<!-- 4.介绍 -->
		<div id="news" style="background: url(img/university1_bg.png);background-attachment: fixed; background-size: 100% 100%">
		    <div class="photo">
				<img src="img/university1.jpg" >
			</div>
			<div class="newsFont">
				<h1 align="center" style="line-height: 60px;color: white;">网站简介</h1>
				<p style="line-height: 35px;color: white;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				朝花夕拾二手商品交易网站是面向南京理工大学全体师生的二手交易平台。平台为全体南京理工大学的师生搭建了全新、快捷、稳定的二手商品交易平台，允许所有师生使用本人的学号或工号进行注册。成功登陆之后用户即可进入自己所属的主页面，通过导航栏选择并使用本平台提供的各种功能。用户可以进入商品浏览界面选择自己喜爱的商品，进入商品详情界面可以联系卖家，并在个人中心中提供详细的数据记录。
				</p>
			</div>
		</div>
		<div id="news_re" style="background: url(img/university3_bg.png);background-attachment: fixed; background-size: 100% 100%">
		    <div class="photo_re">
				<img src="img/university3.jpg" >
			</div>
			<div class="newsFont_re">
				<h1 align="center" style="line-height: 60px;color: white;">开发者简介</h1>
				<p style="line-height: 35px;color: white;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					本网站由南京理工大学计算机科学与工程学院的毕业实习”冲冲冲”小组开发。开发者希望通过开发此网页可以为全体学生提供一个可以互相交流的二手物品交易平台，方便学生获取及时准确的二手商品信息，提供卖家与买家之间的沟通渠道，提高商品利用率，减少资源浪费，促进绿色循环经济，给卖家和买家带来真正的实惠和经济效益。
				 </p>
			</div>
		</div>
		<div id="news1" style="background: url(img/university2_bg.png);background-attachment: fixed; background-size: 100% 100%">
		    <div class="photo">
				<img src="img/university2.jpg" >
			</div>
			<div class="newsFont">
				<h1 align="center" style="line-height: 60px;color: white;">隐私服务概述</h1>
				<p style="line-height: 35px;color: white;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				朝花夕拾二手商品交易网站视用户信息安全与隐私保护为自己的生命线。我们遵循朝花夕拾二手商品交易网站隐私保护PBD，P代表Person，以保护用户隐私为核心；B代表Button，希望通过“隐私按钮”（产品设计）为用户提供合理高效的隐私保护；D代表Data，全方位保障数据安全。我们致力于打造多样的产品和服务以满足您的需求，相应的，各种功能及收集使用的个人信息类型、范围等会有所区别，请以具体的产品/服务功能为准。</p>
			</div>
		</div>
		<div id="news_re1" style="background: url(img/university4_bg.png);background-attachment: fixed; background-size: 100% 100%">
		    <div class="photo_re">
				<img src="img/university4.jpeg" >
			</div>
			<div class="newsFont_re">
				<h1 align="center" style="line-height: 60px;color: white;">用户协议概述</h1>
				<p style="line-height: 35px;color: white;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				朝花夕拾二手商品交易网站作为知识讨论社区，高度重视自由表达和个人、企业正当权利的平衡。依照法律规定删除违法信息是朝花夕拾二手商品交易网站社区的法定义务，朝花夕拾二手商品交易网站社区亦未与任何中介机构合作开展此项业务。朝花夕拾二手商品交易网站有权对用户使用的情况进行审查和监督，如用户在使用时违反任何上述规定，朝花夕拾二手商品交易网站或其授权的人有权要求用户改正或直接采取必要的措施。
				</p>
			</div>
		</div>
		
		
		
		
		<div id="hre"  >
			<h1 align="center" style="padding-top: 20px;color: black;" >查看更多</h1>
		    <h3  class="hre1h"style="color: black;margin-top: 6px;">隐私条款</h3>
			<div class="hre1">
				<!-- <a></a><h3 align="center">体育爱好</h3><br/> -->
				<a target="_blank" href="privacy.jsp"><img src="img/about_1.jpg" /></a>
			</div>
			<h3 class="hre2h"style="color: black;margin-top: 6px;">用户协议</h3>
			<div class="hre2">
				<!-- <h3 align="center">影视爱好</h3><br/> -->
				<a target="_blank" href="protocol.jsp"><img src="img/hhhp.jpg" /></a>
			</div>
			<h3 class="hre3h" style="color: black;margin-top: 6px;">返回顶部</h3><br/>
			<div class="hre3">
				<!-- <h3 align="center">给我留言</h3><br/> -->
				<a href="#header"><img src="img/sunset.jpg" /></a>
			</div>
		</div>
		
		<!-- 5.尾部 -->
		<div id="footer">
		  <div  style="color: #999; text-align: center; height: 50px; line-height: 50px;">
		        Copyright &copy;2020 朝花夕拾工作小组 All rights reserved.
		  </div>
		</div>








<div class="hide-center">
  <div id="formhead">
    <div id="formhead-title">
      &nbsp;&nbsp;&nbsp;&nbsp;
      登   &nbsp;录
    </div>
    <button type="button" id="close">X</button>
  </div>



  <form name="login" action="Login" method="post" onsubmit="return validateForm()">
  <div id="formbody">
    <div class="loginUserName">
      <input id="input-topright-loginInput" name="userName" class="loginInput" placeholder="学 号" type="text" >
    </div>
    <div class="loginPassword">
      <input id="input-bottomright-loginInput" name="password" class="loginInput" placeholder="密 码" type="password" style="border-bottom-right-radius:5px;">
    </div>
    <div id="formfoot">
      <button id="BSignIn" type="submit">Sign In</button>
    </div>
    <br/>
    <p  class="bottom-text" align="center" >
      点击【Sign In】按钮，即代表您已同意
      <a href="protocol.jsp" target="_blank">用户协议</a>
      <br/>和<a href="privacy.jsp" target="_blank">《隐私条款》</a>
    </p>
  </div>
  </form>


</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
  $("#login").click(function () {
    $(".hide-center").fadeIn("slow");
    $(".overCurtain").fadeIn("slow");
  })
  $("#close").click(function () {
    $(".hide-center").fadeOut("slow")
    $(".overCurtain").fadeOut("slow")
  })
</script>
<script>
  $('.carousel').carousel({
    interval: 500
  })
</script>
<script>
  function validateForm(){
    var uname = document.forms["login"]["userName"].value;
    var upwd = document.forms["login"]["password"].value;
    var errorMsg = "";
    if (uname ==null || uname =="") {
      errorMsg += "学号必须填写！";
    }
    if (upwd ==null || upwd =="") {
      errorMsg += "\n密码不能为空！";
    }
    if (uname.length > 30) {
      errorMsg += "\n姓名长度不能大于30！";
    }
    if (upwd.length > 30) {
      errorMsg += "\n密码长度不能大于30！";
    }
    if (errorMsg != "") {
      alert(errorMsg);
      return false;
    }
  }
</script>
</body>
</html>
