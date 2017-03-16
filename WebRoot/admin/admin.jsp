<%@page import="org.struggle_blog.entity.Admin"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/StruggleBlog/img/favicon.ico" />
<title>Struggle-管理员</title>
</head>
<link rel="stylesheet" href="/StruggleBlog/layui/css/layui.css"
	media="all">
<link rel="stylesheet" href="/StruggleBlog/css/admin.css" media="all">
<link rel="stylesheet"
	href="/StruggleBlog/wangEditor/css/wangEditor.css">
<script src="/StruggleBlog/layui/layui.js"></script>
<script type="text/javascript" charset="UTF-8" src="js/jquery-2.1.0.js" ></script>
<body>
	<!--此隐藏域存储消息  -->
	<input type="hidden" value	="${msg }" id="getMsg">
	<input type="hidden" value="${adInfo }" id = "getInfo">
	<div id="max-s">
		<div class="hdimg">
			<a class="hdimg img" href="index.jsp"> <img src="img/hdimg.jpg"
				width="60px" height="60px" />
			</a>
		</div>
		<div id="tabs" class="layui-tab layui-tab-brief"
			lay-filter="docDemoTabBrief">
			<ul class="layui-tab-title">
				<li id="pm-tag" class="layui-this">文章管理</li>
				<li id = "page-new">文章发布</li>
				<li>类型管理</li>
				<li>链接管理</li>
				<li>留言管理</li>
				<li>资料修改</li>
				<li>博客管理</li>
			</ul>
			<div class="layui-tab-content">
				<!--文章管理  -->
				<div class="layui-tab-item layui-show">
					<div id = "page-manage">
						<h1 align="center">所有文章</h1>
						<div class = ".all-sou">
							<input id="sousuo-page" type="text" required lay-verify="required"
							placeholder="请输入检索" autocomplete="off" class="layui-input">
							<button class="layui-btn layui-btn-radius layui-btn-normal" id= "sou-p-but">文章检索</button>
						</div>
						<table class="layui-table" lay-even lay-skin="nob">
						  <thead  style="text-align: center;">
						    <tr align="center">
						      <th>编号</th>
						      <th>文章</th>
						      <th>类型</th>
						      <th>发布时间</th>
						      <th>阅读量</th>
						      <th>状态</th>
						      <th>管理</th>
						    </tr> 
						  </thead>
						  <tbody id="tbody">
						  <!-- 文章 -->
						  </tbody>
						</table>
					</div>
				</div>
				<div class="layui-tab-item" id="editor-f">
					<a id = "but-new-page" href="javascript:void(0);">点击此处编辑新文章</a>
				</div>
				<div class="layui-tab-item">
					<div id = "type-manage">
						<div class = ".all-sou">
							<input id="sousuo-type" type="text" required lay-verify="required"
							placeholder="请输入检索/添加" autocomplete="off" class="layui-input">
							<button id="sou-t-but" class="layui-btn layui-btn-radius layui-btn-normal">检索</button>
							<button id="insert-t-but" class="layui-btn layui-btn-radius layui-btn-warm">添加</button>
						</div>
						<table class="layui-table" lay-even lay-skin="nob">
						  <thead  style="text-align: center;">
						    <tr align="center">
						      <th>编号</th>
						      <th>类型</th>
						      <th>状态</th>
						      <th>管理</th>
						    </tr> 
						  </thead>
						  <tbody id="types-m">
						  <!-- 类型 -->
						  </tbody>
						</table>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class = ".all-sou">
							<input id="sousuo-links" type="text" required lay-verify="required"
							placeholder="请输入检索/添加" autocomplete="off" class="layui-input">
							<button id="sou-l-but" class="layui-btn layui-btn-radius layui-btn-normal">检索</button>
							<button id="insert-l-but" class="layui-btn layui-btn-radius layui-btn-warm">添加</button>
						</div>
						<table class="layui-table" lay-even lay-skin="nob">
						  <thead  style="text-align: center;">
						    <tr align="center">
						      <th>编号</th>
						      <th>链接名称</th>
						      <th>链接地址</th>
						      <th>状态</th>
						      <th>管理</th>
						    </tr> 
						  </thead>
						  <tbody id="links-m">
						  <!-- 类型 -->
						  </tbody>
						</table>
				</div>
				<div class="layui-tab-item">
					<p id="lw">
					
					</p>
				</div>
				<div class="layui-tab-item">资料修改</div>
				<div class="layui-tab-item">博客管理</div>
			</div>
		</div>
	</div>
	<!--底部-->
	<div class="by" style="text-align: center; height: 100px;">
		<p>&copy;2017 Struggle Powered by StruggleYang</p>
		<p>路漫漫其修远兮，吾将上下而求索</p>
	</div>
	<!--底部结束-->
</body>
<!-- 引入wangEditer -->
<script type="text/javascript"
	src="/StruggleBlog/wangEditor/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="/StruggleBlog/wangEditor/js/wangEditor.min.js"></script>
<script type="text/javascript" src="/StruggleBlog/js/admin.js"></script>
</html>
