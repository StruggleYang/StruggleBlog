
		$(document).ready(function(){
			var word = null;
			var name = null;
			var email = null;
			var urls = null;
			$("#inputs").hide();
			$("#word").click(function() {
				$("#inputs").slideDown("slow");
			});
			/*输入验证*/
			$("#su").click(function (){
				if($("#word").val()==""&($("#name").val() == ""||$("#name").val()=="Name*") &($("#email").val() == ""||$("#email").val()=="Email*")){
					alert("写点什么呗！")
				}
				else if ($("#word").val() == "" || $("#name").val() == "" ||$("#name").val()=="Name*"||$("#email").val() == ""||$("#email").val()=="Email*") {
					alert("貌似有些东西还没填！");
				}else if(!(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test($("#email").val()))){
					alert("貌似邮箱格式不正确哟!");
				}else if(!($("#url").val()=="Http://"||$("#url").val()=="")||$("#url").val()==null){
					/*如果网址填了，则正则*/
					var pattern = /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+/;
					var urlss = $("#url").val();
					if(!(pattern.test(urlss))){
						alert("网址格式不对呢！")
					}else{
						postWord();
					}
				}else{
					postWord();
				}
				
			});
			$("#email").blur(function(){ 	
				/*邮箱验证*/
				var x = $("#email").val();
				var check = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
				if (!check.test(x)) {
					alert("貌似邮箱格式不正确!");
				}
			});
			
			/*返回顶部*/
			$("#go-top").click(function(){
		        //点击“回到顶部”，滚动到顶部，并带动画效果
		         $("html,body").animate({scrollTop:0},"slow");
		    });
		/*评论提交*/
		function postWord() {
			var word=$("#word").val();
			var name=$("#name").val();
			var email=$("#email").val();
			var urls=$("#url").val(); 
			if(urls=="Http://"){
				urls = "";
			}
			var pid = $("#pid").val();
			$.ajax({
				type:"post",
				url:"/StruggleBlog/visitor_postComments.go",
				data:{
					"com.CUname":name,
					"com.CEmail":email,
					"com.CUrl":urls,
					"com.CComment":word,
					"com.page.pageId":pid
				},
				success:function(result){
					if(result.indexOf("成功") != -1){
						$("input[type='reset']").click(); 
						alert(result);
						location.reload();
					}else {
						alert(result);
					}
				}
			});
		}
		
		$("#colse-h").click(function() {
			$(".history").slideToggle("slow");
		});
		/*上一页*/
		$("#po-up").click(function() {
			ajaxPage("up");
		});
		/*下一页*/
		$("#po-next").click(function() {
			ajaxPage("next");
		});
		
		/*下一篇文章*/
		function ajaxPage(type) {
			/*=获取到当前文章的id*/
			var pid = $("#pid").val();
			$.ajax({
				type:"post",
				url:"/StruggleBlog/ajax_getOtherPage.go",
				data:{
					/*请求的类型（上一页和下一页）*/
					getType:type,
					/*将当前的文章id传入后台进行逻辑判断*/
					thisPid:pid,
					/*查询的文章状态*/
					state:"do"
				},
				success:function(other) {
					if(other != "no") {
						/*跳转到下一页或者上一页*/
						window.location.href = other;
					} else {
						alert("已经没有更多了！");
					}
				}
			});
		}
		
		
	});
