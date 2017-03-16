<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="img/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- 页面标题为当前阅读文章的标题 -->
<title>Struggle-${pageOne.pageTitle }</title>
</head>
<link rel="stylesheet" href="/StruggleBlog/css/page-one.css">
<script type="text/javascript"  src="/StruggleBlog/js/jquery-2.1.0.js" ></script>
<script type="text/javascript" src="/StruggleBlog/js/page-comments.js"></script>
<body>
<input id="pid" type="hidden" value=${pageOne.pageId }>
<div id="page-one">
	<div class="hdimg">
			<a id="a-img" class="hdimg img" href="index.jsp"> <img src="img/hdimg.jpg"
				width="60px" height="60px" />
			</a>
	</div>
	<h2 id="page-title" align="center">《${pageOne.pageTitle }》</h2>
	<p style="line-height: 20px" align="center">作者:${pageOne.pageAuthor }&nbsp;|&nbsp;分类:${pageOne.type.TName }&nbsp;|&nbsp;发布于:<fmt:formatDate value="${pageOne.pageTime}" pattern="yyyy-MM-dd"/>&nbsp;|&nbsp;阅读量:${pageOne.pageRead }</p>
	<!-- 文章内容 -->
	${pageOne.pageWord }
	<hr style="height:1px;border:none;border-top:1px dashed black;">
	<div class="next-more">
		<span><a href="javascript:void(0);" id="po-up">&larr;</a></span><span><a href="javascript:void(0);" id="go-top"><b>&and;</b></a></span> <span><a id="po-next" href="javascript:void(0);">&rarr;</a></span> 
	</div>
	<div class="word-page">
			<p>发表评论</p>
			<p>(*号为必填)</p>
			<!--文本输入域-->
			<form id="forms">
				<textarea  id="word" ></textarea>
				<br />
				<span id="txt">*</span>
				<br />
				<div id="inputs">
					<span class="text">昵称：</span>
					<input type="text" value="Name*" onfocus="if(this.value=='Name*'){this.value='';}" onblur="if(this.value==''){this.value='Name*';}" id="name" /><span id="nameT"></span>
					<br />
					<span class="text">邮箱：</span>
					<input type="text" value="Email*" onfocus="if(this.value=='Email*'){this.value='';}" onblur="if(this.value==''){this.value='Email*';}" id="email"/><span id="emailT"></span>
					<br />
					<span class="text">网站：</span>
					<input type="url" value="Http://" onfocus="if(this.value=='Http://'){this.value='';}" onblur="if(this.value==''){this.value='Http://';}" id="url"/><span id="urlT"></span>
					<br />
					<br />
				</div>
				<input type="reset" value="清空" />
				<input type="button" value="评论" id="su"/>
			</form>
			<a id="colse-h" href="javascript:void(0);"><p >其他人都说&nbsp;&nbsp;&nabla;</p></a>
		</div>
		<div class="history">
			<p id="msg" align="center"></p>
			<ul id="lw">
				<c:forEach items="${pageOne.comments }" var="pc" >
					<li>
					<span><a href="${pc.CUrl }" target='_blank'>${pc.CUname }</a>&nbsp;|&nbsp;${pc.CTime}：</span>
					<p id='lww'>&gt;&nbsp;${pc.CComment }</p><p>${pc.CReply }</p>
					</li>
				</c:forEach>
				
			</ul>
		</div>
		<hr style="height:1px;border:none;border-top:1px dashed black;">
</div>
<!--底部-->

		<div class="by" style="text-align: center; height: 150px;">
			<p>&copy;2017 Struggle Powered by StruggleYang</p>
			<p>路漫漫其修远兮，吾将上下而求索</p>
		</div>
</body>
</html>