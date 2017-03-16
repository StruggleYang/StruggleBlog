/**
 * 友情链接js
 */
$(document).ready(function () {
	loadLink();
	$("#go_line").click(function() {
		$(".d-html-more").load("visitor/about-me.html");
		ajaxLoad();
	});
});

function loadLink() {
	$.ajax({
		type:"post",
		url:"/StruggleBlog/ajax_getAllLinks.go",
		data:{
			state:"do",
			like:""
		},
		dataType:"json",
		success:function(links) {
			var  html = "";
			if(links.length != 0) {
				for(var i = 0;i<links.length;i++){
					html +="<li><a href='"+links[i].linkUrl+"' target='_blank'>"+links[i].linkName+"</a></li>";
				}
				$("#link-li").html(html);
			} 
		}
	});
}