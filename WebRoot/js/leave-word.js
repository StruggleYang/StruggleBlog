
		$(document).ready(function(){
			$("#word").click(function() {
				// 异步请求cookie
				getCookies();
			});
			// 异步请求留言数据
			loadWords();
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
			
			var thisPage = 0;
			/*分页--上一页*/
			var first = 0;
			var max = 5;
			$("#lw-up").click(function() {
				if(thisPage==0){
					alert("已经是第一页了");
					$("#lw-up").hover(function() {
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
					url:"/StruggleBlog/visitor_getAllWord.go",
					data:{
						firstResult:first,
						maxResult:max
					},
					dataType:"json",
					success:function(rs){
						if(rs!=""){
							loadWords(first,max);
						}
					}
				});
			});
			/*分页--下一页*/
			var firstResult = 0;
			var maxResult = 5;
			$("#lw-next").click(function() {
				first = 0;
				max = 5;
				firstResult = firstResult +5;
				$.ajax({
					type:"post",
					url:"/StruggleBlog/visitor_getAllWord.go",
					data:{
						firstResult:firstResult,
						maxResult:maxResult
					},
					dataType:"json",
					success:function(rs){
						if(rs!=""){
							loadWords(firstResult,maxResult);
							thisPage = ++thisPage;
						} else {
							alert("已经没有更多了");
							$("#lw-next").hover(function() {
								 $(this).css({"background-color":"#4F4F4F","color":"red"});
							},function() {
								$(this).css({"background-color":"#4F4F4F","color":"#00fd5f"});
							});
						}
					}
				});
			});
			
			/*分页--首页*/
			$("#lw-first").click(function() {
				first = 0;
				max = 5;
				thisPage = 0;
				firstResult = 0;
				maxResult = 5;
				var a = null;
				var b = null;
				loadWords(a,b);
				$("#lw-next,#lw-up").hover(function() {
					 $(this).css({"background-color":"#00fd5f","color":"white"});
				},function() {
					$(this).css({"background-color":"#4F4F4F","color":"#00fd5f"});
				});
			});
		});
		
		
		/*留言提交*/
		function postWord() {
			var word=$("#word").val();
			var name=$("#name").val();
			var email=$("#email").val();
			var urls=$("#url").val(); 
			if(urls=="Http://"){
				urls = "";
			}
			$.ajax({
				type:"post",
				url:"/StruggleBlog/visitor_postWord.go",
				data:{
					"lw.lwName":name,
					"lw.lwEmail":email,
					"lw.lwUrl":urls,
					"lw.lwWord":word
				},
				success:function(result){
					if(result.indexOf("成功") != -1){
						$("input[type='reset']").click(); 
						loadWords();
						alert(result);
					}else {
						alert(result);
					}
				}
			});
		}
		/* 留言载入 */
		function loadWords(a,b) {
			if(a==null&&b==null){
				a = 0;
				b = 5;
			}
			$.ajax({
				type:"post",
				url:"/StruggleBlog/visitor_getAllWord.go",
				data:{
					firstResult:a,
					maxResult:b
				},
				dataType:"json",
				success:function(words){
					var html = "";
					if(words!="") {
						for(var a=0;a < words.length;a++){
							var time = words[a].lwTime.replace(/T/, "&nbsp;");
							var reply = words[a].lwReply;
							if(reply==null){
								reply = "";
							}else {
								reply = "博主已回复:"+reply;
							}
							if(reply==null){
								reply = "";
							}
							var aa = words[a].lwUrl;
							if(aa == null){
								aa = "#";
							}
							html +="<li><span><a href='"+aa+"' target='_blank'>"+words[a].lwName+"</a>&nbsp;|&nbsp;"+time+"：</span><p id='lww'>"+words[a].lwWord+"</p><p>"+reply+"</p></li>";
						}
						$("#lw").html(html);
					}else {
						$("#msg").text("呃-_-，什么鬼都没有！");
					}
				}
			});
		}
		/********请求cookie*********/
		function getCookies() {
			$.ajax({
				type:"post",
				url:"/StruggleBlog/visitor_getCookies.go",
				success:function(vInfo){
					if (vInfo != ""&&vInfo != null) {
						var info = vInfo.split("|----|");
						$("#name").val(info[0]);
						$("#email").val(info[1]);
						$("#url").val(info[2]);
					}
				}
			});
		}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		