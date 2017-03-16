<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/StruggleBlog/img/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Struggle_update_page</title>
</head>
<link rel="stylesheet" href="/StruggleBlog/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="/StruggleBlog/wangEditor/css/wangEditor.css">
<script src="/StruggleBlog/layui/layui.js"></script>
<script type="text/javascript" charset="UTF-8" src="/StruggleBlog/js/jquery-2.1.0.js" ></script>
<link rel="stylesheet"
	href="/StruggleBlog/css/page-update.css">
<body >
	<div id="editor-f">
	<h1 align="center" style="font-size: 20px; line-height: 40px;">文章修改</h1>
	<form class="layui-form" id="go-page" action="/StruggleBlog/page_savePage.go" method="post" enctype="multipart/form-data">
				<input type="hidden" name="oper" value="update">
				<input type="hidden" name="page.pageId" value="${pageOne.pageId }">
				<input type="hidden" id = "thisTid" value="${pageOne.type.TId }"> 
				<div class="layui-form-item">
					<label class="layui-form-label">文章标题</label>
					<div class="layui-input-block">
						<input id="page-title" type="text" name="page.pageTitle" required lay-verify="required"
							placeholder="请输入标题" autocomplete="off" value="${pageOne.pageTitle}" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">文章类型</label>
					<select id="page-type" name="page.type.TId" lay-verify="required">
						<!-- 此处ajax请求获取类型 -->
						<option value='0'>请选择类型</option>
					</select>
				</div>

				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">文章摘要</label>
					<div class="layui-input-block">
						<textarea id="page-zy" name="page.pageRemark" placeholder="请输入文章摘要"
							class="layui-textarea" >${pageOne.pageRemark }</textarea>
					</div>
				</div>
				
				<!--将文章内容赋值于隐藏域  -->
				<input id="getWord" type="hidden" name = "page.pageWord">
				<!-- end -->
				
				<p>文章原图:》》&nbsp;<img width="30%" height="30%" src="${pageOne.imgAdds.imgAdd }" alt="${pageOne.pageTitle}">&nbsp;《《</p>
				<label class="layui-form-label">修改配图</label>
				<div class="layui-form-item">
					<input id = "files" type="file"size="30" name="pageImg" />
					<input id = "ifnull" type="hidden" name = "ifnull" value="">
				</div>
				
			</form>
			<!-- wangEditor模块 -->
				<div id="editor">${pageOne.pageWord }</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="button" id="up-to-service" class="button" value="提交修改">
						<input id = "close" type="button" class="button" value="取消修改">
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
<script type="text/javascript" src="/StruggleBlog/js/update-page.js"></script>
</html>