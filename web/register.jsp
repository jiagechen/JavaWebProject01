<%--
  Created by IntelliJ IDEA.
  User: Windows10.0
  Date: 2020/9/10
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>朝花夕拾|账号注册</title>

  <link rel="stylesheet" href="css/register.css" />
  <style type="text/css">
    .form{
      margin-left: 500px;
    }
    #nav ul li a{
      color: white;
    }
    #nav ul li a:hover{
      /* background: #de4b15; */
      color: gray;
      transition: 1s;
    }
    p{
      color: white;
    }
  </style>
  <script src="js/jquery.min.js"></script>
  <script>
    function validateForm(){
      var uId = document.forms["register"]["uId"].value;
      var uName = document.forms["register"]["uName"].value;
      var upwd = document.forms["register"]["password"].value;
      var upwdConfirm = document.forms["register"]["passwordConfirm"].value;
      var errorMsg = "";
      if (uId ==null || uId =="") {
        alert("学号必须填写");
        return false;
        // errorMsg += "学号必须填写！";
      }
      if (uName ==null || uName =="") {
        alert("昵称必须填写");
        return false;
        //errorMsg += "\n昵称必须填写！";
      }
      if (upwd ==null || upwd =="") {
        alert("密码必须填写");
        return false;
        //errorMsg += "\n密码不能为空！";
      }
      if (upwdConfirm ==null || upwdConfirm =="") {
        alert("确认密码填写");
        return false;
        //errorMsg += "\n请再次确认密码！";
      }
      if (uId.length > 30) {
        alert("姓名长度不能大于30");
        return false;
        //errorMsg += "\n姓名长度不能大于30！";
      }
      if (uName.length > 30) {
        alert("昵称长度不能大于30");
        return false;
        //errorMsg += "\n昵称长度不能大于30！";
      }
      if (upwd.length > 30) {
        alert("密码长度不能大于30！");
        return false;
        //errorMsg += "\n密码长度不能大于30！";
      }
      if (errorMsg != "") {
        alert(errorMsg);
        return false;
      }
    }
    function validateID() {
      if(null == $("#uId").val() || ''==$("#uId").val()){
        $("#uIdSpan").html('<font color="red">用户名不能为空</font>');
        return;
      }
      $("#uIdSpan").html('<font color="green">正确</font>');
      $.ajax({
                  type:"post",
                  url:"validateIDServlet.do?",
                  data:"uname="+$("#uId").val(),
                  success:function (info) {
                    $("#uIdSpan").text(info);
                    $("#uIdSpan").html('<font color="green">'+info+'</font>')
                    //console.log(info);
                  }
      })
    }
    function validateUname() {
      if(null == $("#uName").val() || ''==$("#uName").val()){
        $("#uNameSpan").html('<font color="red">昵称不能为空</font>');
        return;
      }
      $("#uNameSpan").html('<font color="green">正确</font>');
    }
    function validatePassword() {
      if(null == $("#password").val() || ''==$("#password").val()){
        $("#passwordSpan").html('<font color="red">密码不能为空</font>');
        return;
      }
      $("#passwordSpan").html('<font color="green">正确</font>');
    }
    function validatePasswordConfirm() {
      if(null == $("#passwordConfirm").val() || ''==$("#passwordConfirm").val()){
        $("#passwordConfirmSpan").html('<font color="red">确认密码不能为空</font>');
        return;
      }
      if($("#passwordConfirm").val() != $("#password").val() ){
        $("#passwordConfirmSpan").html('<font color="red">密码不一致，请重新输入</font>');
        return;
      }
      $("#passwordConfirmSpan").html('<font color="green">正确</font>');
    }
  </script>
</head>
<body style="background: url(img/shanshui.png);background-attachment: fixed; background-size: 100% 100%">

<div id="nav" >
  <ul class="nav nav-pills">
    <li><a id="login"href="login.jsp" >返回首页</a></li>
    <li><a href="login.jsp#news" >关于我们</a></li>
  </ul>
</div>
<div  class="form" >
  <p align="left" style="font-size: 50px;font-weight: normal;line-height: 0px;">欢迎注册</p>
  <p align="left" style="font-size: 30px;font-weight: 300;line-height: 30px; ">朝花夕拾，留住你的美好时光。</p>
  <form action="Signup" method="post" name="register" onsubmit="return validateForm()">

    <div class="form-inf" >
      <input type="text" name="uId" id="uId" placeholder="学号" style="font-size: 20px;" onblur="validateID()" />
      <span id="uIdSpan" style="color:red"></span>
      <%--<font color="red"></font>--%>
    </div><br />

    <div class="form-inf">
      <input type="text" name="uName" id="uName" placeholder="昵称" style="font-size: 20px;" onblur="validateUname()"/>
      <span id="uNameSpan"></span>
    </div><br />

    <div class="form-inf">
      <input type="password" name="password" id="password" placeholder="密码" style="font-size: 20px;" onblur="validatePassword()"/>
      <span id="passwordSpan" style="color: red"></span>
    </div><br />

    <div class="form-inf">
      <input type="password" name="passwordConfirm" id="passwordConfirm" placeholder="确认密码"style="font-size: 20px;" onblur="validatePasswordConfirm()"/>
      <span id="passwordConfirmSpan" style="color: red"></span>
    </div><br />

    <div class="form-inf">
      <select id="college" name="college" style="font-size: 20px;">
        <option>机械工程学院</option>
        <option>化工学院</option>
        <option>电子工程与光电技术学院</option>
        <option>计算机科学与工程学院</option>
        <option>能源与动力工程学院</option>
        <option>经济管理学院</option>
        <option>自动化学院</option>
        <option>理学院</option>
        <option>外国语学院</option>
        <option>公共事务学院</option>
        <option>材料科学与工程学院</option>
        <option>环境与生物工程学院</option>
        <option>设计艺术与传媒</option>
        <option>钱学森学院</option>
        <option>知识产权学院</option>
        <option>马克思主义学院</option>
        <option>国际教育学院</option>
        <option>继续教育学院</option>
        <option>中法工程师学院</option>
        <option>紫金学院</option>
        <option>泰州科技学院</option>
      </select><br />
    </div>
    <button type="submit" class="btn">立即注册</button>
    <p class="bottom-text" >
      点击【立即注册】按钮，即代表您已同意
      <a href="protocol.jsp" target="_blank">用户协议</a>
      和<a href="privacy.jsp" target="_blank">《隐私条款》</a>
    </p>
  </form>
  <footer>

    <p >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      已注册过账号？ <a href="login.jsp">在此登录</a></p>
  </footer>
</div>


</body>
</html>
