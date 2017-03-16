<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>Struggle</title>
<meta charset="utf-8" />
<!--使响应式布局兼容移动设备-->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="shortcut icon" href="img/favicon.ico" />
</head>
<link rel="stylesheet" charset="UTF-8" href="css/index.css"  />
<script type="text/javascript" charset="UTF-8" src="js/jquery-2.1.0.js" ></script>
<script type="text/javascript" charset="UTF-8" src="js/index.js" ></script>
<body>
	<input id = "state" type="hidden" value="${state }">
	<div id="i-zz">
    <div id="i-insertdiv">	
   		<a id="colse-load" class="hdimg img" href="javascript:void(0);"> 
   		<img  width="128" height="128" class="hd-img" /></a>
			<h1 class="ttl">
			<a href="index.jsp" class="blog_name"></a>
		</h1>
		<p class="cont">哈哈,签名已然离去！</p>
    </div>
    </div>
	<div id="div-max">
		<div class="sidebar">
			<div class="g-sdc box">
				<div class="m-hdimg">
					<a class="hdimg img"  href="index.jsp"> <img
						src="img/hdimg.jpg" width="128" height="128" class="hd-img"  />
					</a>
					<h1 class="ttl">
						<a href="index.jsp" class="blog_name"></a>
					</h1>
				</div>

				<!-- 签名-->
				<div class="m-about" id="j-about">
					<p class="cont">哈哈,签名已然离去！</p>
				</div>
				<ul class="m-nav">
					<li><a href="javascript:void(0);"  id="home">主页</a></li>
					<li><a href="https://github.com/YQ1724555319" target="_blank">GitHub</a></li>
					<li><a href="javascript:void(0);" id="page_classify" target="_blank">归档</a></li>
					<li><a href="view-source:localhost:8080/StruggleBlog/index.jsp"  target="_blank">实验室</a></li>
					<li><a href="javascript:void(0);" id="more-link">友情链接</a></li>
					<li><a href="javascript:void(0);" id="bookmarks">我的书签</a></li>
					<li><a href="javascript:void(0);" id="leave-word">留言</a></li>
					<li><a href="javascript:void(0);" id="about-me">关于我</a></li>
					<!-- Search -->
					<li class="m-sch">
					<input id = "sou_page_index" type="text" name="wd" class="txt" value="检索"
						onfocus="if(this.value=='检索'){this.value='';}"
						onblur="if(this.value==''){this.value='检索';}" />
					</li>
				</ul>
			</div>
		</div>
		<div class="main">
			<button class="show-menu"
				style="background-image: url(img/show-menu.png);"></button>
			<!--正文文章开始-->
			<div class="all-page">
				<!--未展开左侧菜单显示博主资料-->
				<div class="show-info">
					<div class="hdimgs">
						<a class="hdimg img" id = "refresh-page" href="javascript:void(0);"> <img src="img/hdimg.jpg"
							width="60px" height="60px" class="hd-img"  />
						</a>
					</div>
					<div class="left-todo">
						<p class="cont-l"></p>
					</div>
					<div class="right-todo">
						<p class="cont-r"></p>
					</div>
				</div>
				<!--其他页面加载到此div-->
				<div class="d-html-more"></div>
				<!--文章开始-->
				<div id="page-list" style="width:auto; height: auto;">
				<!-- 此处文章列表 -->
				</div>
				<!-- 文章结束 -->
				<!--下一页及更多-->
				<div class="next-more">
					<span><a id="go-last" href="javascript:void(0);">&larr;</a></span><span><a id="go-top" href="javascript:void(0);">▲</a></span><span><a id="go-next" href="javascript:void(0);">&rarr;</a></span>  
				</div>
				</div>
				<!--文章列表结束-->
				<div class = push></div>
				<!--底部-->
				<div class="by" style="text-align: center; height: 150px;">
					<p>&copy;2017 Struggle Powered by StruggleYang</p>
					<p class="cont">我是一个有底线的人-_-！</p>
				</div>
				<!--底部结束-->
			</div>
			<!--正文文章结束-->
		</div>
</body>
</html>
