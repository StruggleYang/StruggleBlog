
/******************************侧滑抽屉开始***********************/
		$(document).ready(function() {
			// 记录访问状态
			var state = $("#state").val();
			ajaxLoad();
			enterSousuo();
			ajaxPage();
			if(state!="vistior") {
				$("#div-max").hide();
				/*关闭载入画面*/
				var ifclick = 0;
				$("#colse-load").click(function() {
					ifclick++;
					$("#i-zz").fadeOut(1500);
					$("body").css("overflow-x","hidden");
					$("body").css("overflow-y","auto");
					$("#div-max").fadeIn();
				});
				//  如果游客没有点击进入则让其延时自动进入
				if(ifclick == 0) {
					setTimeout("$('#colse-load').click()",7000);
				}
			}else {
				$("#i-zz").hide();
				$("body").css("overflow-x","hidden");
				$("body").css("overflow-y","auto");
			}
			$(".show-info").hide();
			/*点击按钮时将侧边栏弹出或隐藏（核心代码）*/
			$(".show-menu").click(function() {
				// 将主页资料隐藏
				$(".show-info").slideToggle("slow");
				$(".g-sdc").slideToggle("slow");
				$("body").toggleClass("big-page");
				// 判断其left属性切换按钮内容
				if ($(".sidebar").css("left") == "0px") {
					$(this).css("background-image", "url(img/show-menu.png)");
				} else {
					$(this).css("background-image", "url(img/close-menu.png)");
				}
			});
			
			
			/*返回顶部*/
			$("#go-top").click(function(){
		        //点击“回到顶部”，滚动到顶部，并带动画效果
		         $("html,body").animate({scrollTop:0},"slow");
		    });
			/*主页按钮*/
			$("#home").click(function(){
				ajaxPage();
				/*显示文章和分页按钮*/
				$(".page-list").slideDown("slow");
				$(".qzon-class").slideDown("slow");
				$(".next-more").slideDown("slow");
				/*将已存在的部分页面消失*/
				$(".d-html-more").html("");
			});
			
			/******归档按钮*******/
			$("#page_classify").click(function() {
				/*隐藏文章和分页按钮*/
				$(".page-list").slideUp("slow");
				$(".qzon-class").slideUp("slow");
				$(".next-more").slideUp("slow");
				pageClassify();
			});
			
			/*友情链接按钮*/
			$("#more-link").click(function(){
				/*隐藏文章和分页按钮*/
				$(".page-list").slideUp("slow");
				$(".qzon-class").slideUp("slow");
				$(".next-more").slideUp("slow");
				/*将友情链接部分页面加载进来*/
				$(".d-html-more").load("visitor/moreLink.html");
			});
			/*书签按钮*/
			$("#bookmarks").click(function(){
				/*隐藏文章和分页按钮*/
				$(".page-list").slideUp("slow");
				$(".qzon-class").slideUp("slow");
				$(".next-more").slideUp("slow");
				/*将书签部分页面加载进来*/
				$(".d-html-more").load("visitor/bookmarks.html");
			});
			/*留言按钮*/
			$("#leave-word").click(function(){
				/*隐藏文章和分页按钮*/
				$(".page-list").slideUp("slow");
				$(".qzon-class").slideUp("slow");
				$(".next-more").slideUp("slow");
				/*将留言部分页面加载进来*/
				$(".d-html-more").load("visitor/leave-word.html");
			});
			/*关于我*/
			$("#about-me").click(function(){
				/*隐藏文章和分页按钮*/
				$(".page-list").slideUp("slow");
				$(".qzon-class").slideUp("slow");
				$(".next-more").slideUp("slow");
				/*将关于我部分页面加载进来*/
				$(".d-html-more").load("visitor/about-me.html");
				ajaxLoad();
			});
	$("#refresh-page").click(function() {
		ajaxPage();
		/*显示文章和分页按钮*/
		$(".page-list").slideDown("slow");
		$(".qzon-class").slideDown("slow");
		$(".next-more").slideDown("slow");
		/*将已存在的部分页面消失*/
		$(".d-html-more").html("");
	});
				
	/*****************文章分页*****************/
		var thisPage = 0;
		/*分页--上一页*/
		var first = 0;
		var max = 5;
		$("#go-last").click(function() {
			if(thisPage==0){
				alert("已经是第一页了");
				$("#go-last").hover(function() {
					 $(this).css({"background-color":"#4F4F4F","color":"red"});
				},function() {
					$(this).css({"background-color":"#4F4F4F","color":"#00fd5f"});
				});
			}
			if(thisPage!=0){
				thisPage = --thisPage;
			}
			firstResult = 0;
			maxResult = 5;
			first = (thisPage)*5;
			$.ajax({
				type:"post",
				url:"/StruggleBlog/ajax_getPages.go",
				data:{
					firstResult:first,
					maxResult:max
				},
				dataType:"json",
				success:function(rs){
					if(rs!=""){
						ajaxPage(first,max);
					}
				}
			});
		});
		/*分页--下一页*/
		var firstResult = 0;
		var maxResult = 5;
		$("#go-next").click(function() {
			first = 0;
			max = 5;
			firstResult = firstResult +5;
			$.ajax({
				type:"post",
				url:"/StruggleBlog/ajax_getPages.go",
				data:{
					firstResult:firstResult,
					maxResult:maxResult
				},
				dataType:"json",
				success:function(rs){
					if(rs!=""){
						ajaxPage(firstResult,maxResult);
						thisPage = ++thisPage;
					} else {
						alert("已经没有更多了");
						$("#go-next").hover(function() {
							 $(this).css({"background-color":"#4F4F4F","color":"red"});
						},function() {
							$(this).css({"background-color":"#4F4F4F","color":"#00fd5f"});
						});
					}
				}
			});
		});
	});
/************************侧滑抽屉结束********************/
/**********************异步请求博主信息************************/
function ajaxLoad() {
	$.ajax({
		type:"post",
		url:"/StruggleBlog/ajax_getInfo.go",
		dataType:"json",
		success:function(info) {
			$(".blog_name").text(info.biName);
			$(".hd-img").attr("src","img/"+info.biHeader);
			/*签名*/
			$(".cont").text(info.biAutograph);
			var index = info.biAutograph.indexOf("，");
			$(".cont-l").text(info.biAutograph.substring(0,index));
			$(".cont-r").text(info.biAutograph.substring(index+1));
			$("#me").html(info.biAbout);
		}
	});
}
			
/*******************文章异步载入*************************/
function ajaxPage(a,b) {
	if(a==null&&b==null){
		a = 0;
		b = 5;
	}
	$.ajax({
		type:"post",
		url:"/StruggleBlog/ajax_getPages.go",
		dataType:"json",
		data:{
			firstResult:a,
			maxResult:b
		},
		success:function(pages) {
			if(pages.length != 0) {
				var html = "";
				for(var i=0;i<pages.length;i++){
					var time = pages[i].pageTime.replace(/T/, "&nbsp;");
					/*奇数行使用图左文右*/
					 if((i+1)%2 == 0){
						 html += "<div class='qzon-class'>"+
						 	"<div class='page-cover-right'>"+
							"<a href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><img src='"+pages[i].imgAdds.imgAdd+"' width='100%' /></a>"+
							"</div>"+
							"<div class='page-left'>"+
								"<a class='a-title' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><h1>"+pages[i].pageTitle+"</h1></a>"+
								"<p>发表于:"+time+"&nbsp;/&nbsp;"+pages[i].type.TName+"</p>"+
								"<p class='zy'>"+pages[i].pageRemark+"</p>"+
								"<a class='readAll right' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'>阅读全文</a>"+
							"</div>"+
							"</div>";
					}else { //  偶数行使用图右文左
						html += "<div class='qzon-class'>"+
				 		"<div class='page-cover-left'>"+
							"<a href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><img src='"+pages[i].imgAdds.imgAdd+"' width='100%' /></a>"+
						"</div>"+
						"<div class='page-right'>"+
							"<a class='a-title' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><h1>"+pages[i].pageTitle+"</h1></a>"+
							"<p>发表于:"+time+"&nbsp;/&nbsp;"+pages[i].type.TName+"</p>"+
							"<p class='zy'>"+pages[i].pageRemark+"</p>"+
							"<a class='readAll left' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'>阅读全文</a>"+
						"</div>"+
						"</div>";
					}
				}
				$("#page-list").html(html);
			}
		}
	});
}
	/****************回车检索文章******************/
function enterSousuo() {
	$("#sou_page_index").keydown(function(event){ 
	    if(event.which==13) {
	    	var like = $("#sou_page_index").val();
	    	if(like==""){
	    		alert("请输入查询内容");
	    		ajaxPage();
	    	} else {
	    		$.ajax({
	    			type : "post",
	    			url : "/StruggleBlog/ajax_getAllPages.go",
	    			dataType : "json",
	    			data : {
	    				like : like
	    			},
	    			success:function(pages) {
						if(pages.length != 0) {
							var html = "";
							for(var i=0;i<pages.length;i++){
								var time = pages[i].pageTime.replace(/T/, "&nbsp;");
								/*奇数行使用图左文右*/
								 if((i+1)%2 == 0){
									 html += "<div class='qzon-class'>"+
									 	"<div class='page-cover-right'>"+
										"<a href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><img src='"+pages[i].imgAdds.imgAdd+"' width='100%' /></a>"+
										"</div>"+
										"<div class='page-left'>"+
											"<a class='a-title' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><h1>"+pages[i].pageTitle+"</h1></a>"+
											"<p>发表于:"+time+"&nbsp;/&nbsp;"+pages[i].type.TName+"</p>"+
											"<p class='zy'>"+pages[i].pageRemark+"</p>"+
											"<a class='readAll right' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'>阅读全文</a>"+
										"</div>"+
										"</div>";
								}else { //  偶数行使用图右文左
									html += "<div class='qzon-class'>"+
							 		"<div class='page-cover-left'>"+
										"<a href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><img src='"+pages[i].imgAdds.imgAdd+"' width='100%' /></a>"+
									"</div>"+
									"<div class='page-right'>"+
										"<a class='a-title' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'><h1>"+pages[i].pageTitle+"</h1></a>"+
										"<p>发表于:"+time+"&nbsp;/&nbsp;"+pages[i].type.TName+"</p>"+
										"<p class='zy'>"+pages[i].pageRemark+"</p>"+
										"<a class='readAll left' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="+pages[i].pageId+"&oper=view'>阅读全文</a>"+
									"</div>"+
									"</div>";
								}
							}
							$("#page-list").html(html);
						} else {
							 if(confirm("没有查到相关内容，要去百度试试吗？")){
								location.assign("http://www.baidu.com/s?wd="+like);
							     }else {
							      alert("换个关键字试试！");
							    }
						}
					}
	    		});
	    	}
	    }
	 });
}
			
/**********************文章归档*********************************/
function pageClassify() {
	$.ajax({
		type:"post",
		url:"/StruggleBlog/ajax_getAllPages.go",
		dataType:"json",
		data : {
			like : ""
		},
		success:function(pages) {
			if(pages.length != 0) {
				var html = "<h1 id='class-h' align='center'>文章归档</h1>";
				for(var i=0;i<pages.length;i++){
					var time = pages[i].pageTime.replace(/T/, "&nbsp;").toString();
					html +="<p class='classify' align='left'  >"+time+"_______________________▲△__."
						+"<a class='read' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="
						+ pages[i].pageId
						+ "&oper=view'>"
						+ pages[i].pageTitle
						+ "</a></p>";
				}
				$(".d-html-more").html(html);
			}
		}
	});
}
			