var index;// 弹出层的返回
var layers;
layui.use('layer', function() {
	layers = layui.layer;
});
/** ******************载入请求类型信息******************* */
$(document).ready(function() {
	getAllType("do","");
	ajaxPage("do","");
	getAllLink("do","");
	loadWords(0,30);
	/* 载入layui表单 */
	layui.use('form');
	/** ******点击发表文章弹出层编写文章******* */
	$("#but-new-page").click(function() {
		index = layer.open({
			type : 2,
			title : info + "--请发布文章吧！",
			content : "/StruggleBlog/admin/NewPage.html",
			maxmin : true, // 开启最大化最小化按钮
			shadeClose : true,
			area : [ '950px', '600px' ],
		});
	});
	
	/*************文章查询按钮***************/
	$("#sou-p-but").click(function() {
		var like = $("#sousuo-page").val();
		ajaxPage("do",like);
	});
	/***************类别查询按钮*******************/
	$("#sou-t-but").click(function() {
		var like = $("#sousuo-type").val();
		getAllType("do",like);
	});
	/*****************链接查询按钮*****************/
	$("#sou-l-but").click(function() {
		var like = $("#sousuo-links").val();
		getAllLink("do",like)
	});
	/*****************类别添加按钮*****************/
	$("#insert-t-but").click(function() {
		var tName = $("#sousuo-type").val();
		if(tName!=null&&tName!="") { 
			insertTypeOne(tName);
		}else {
			layers.open({
				title:'提示',
				content : "请先填写类别名称",
				btn : '宝宝知道了'	
			});
		}
	});
	/******************链接添加按钮************************/
	$("#insert-l-but").click(function() {
		var lName = $("#sousuo-links").val();
		if(lName!=null&&lName!="") {
			/*********layui一个弹出输入框********/
			layers.prompt({
				  formType: 0,
				  value: "",
				  title: '请添加此链接的地址',
				}, function(value, index, elem){  // 确认按钮回调
					$("#sousuo-links").val("");
				  /**********将获取到的值传入修改**********/
					insertlink(lName,value);
				  layer.close(index);
				}); 
		} else {
			layers.open({
				title:'提示',
				content : "请先填写链接名称",
				btn : '宝宝知道了'	
			});
		} 
	});
	
});
/** ******************获取所有文章类型***************** */
function getAllType(state,like) {
	$.ajax({
		type : "post",
		url : "/StruggleBlog/ajax_getAllType.go",
		data : {
			state : state,
			like :like
		},
		dataType : "json",
		success : function(types) {
			if (types.length != 0) {
				var html = "<option value='0'>请选择类型</option>";
				var manage = "";
				for (var a = 0; a < types.length; a++) {
					html += "<option value='" + types[a].TId + "'>"
							+ types[a].TName + "</option>";
	
					var state = types[a].TState;
					if (state == "do") {
						state = "可读写";
					} else {
						state = "回收站"
					}
					manage += "<tr>";
					manage += "<td>" + (a + 1) + "</td>";
					manage += "<td>"
							+ "<span class ='tname'>"+types[a].TName+"</span>"
							+ "<input type = 'hidden' class = 'tid' value = '"
							+ types[a].TId + "'></td>";
					manage += "<td>" + state + "</td>";
					manage += "<td><a class='update-type' href='javascript:void(0);'>修改</a>&nbsp;|&nbsp;"
							+ "<a class = 'deleteType' href='javascript:void(0);'>删除</a></td>";
					manage += "</tr>";
				}
			} else {
				layers.open({
					title : "提示",
					content : "什么鬼都没",
					btn : '宝宝知道了'
				});
			}
			layui.use('layer', function() {
				$("#page-type").html(html);
				$("#types-m").html(manage);
				var layer = layui.layer;
				// 拿到元素数组
				var Tids = $(".tid");
				var deleteTypes = $(".deleteType");
				$(".deleteType").click(function() {
					/* 获取当前点击的标签对象在该对象数组中的位置 */
					var index = deleteTypes.index($(this));
					// 获取当前位置所对应行的id内容
					var Tid = Tids.eq(index).val();
					layer.confirm('是否确定要删除?此类型将移入回收站内,如果此分类下没有文章，你才可以彻底删除它', {
						icon : 0,
						title : '提示'
					}, function() {
						deleteType(Tid);
						layer.close();
					});
				});
			});
			
			/******************类别修改按钮*********************/
			// 拿到元素数组
			var Tids = $(".tid");
			var Tnames = $(".tname")
			var updateTypes = $(".update-type");
			$(".update-type").click(function() {
				/* 获取当前点击的标签对象在该对象数组中的位置 */
				var index = updateTypes.index($(this));
				// 获取当前位置所对应行的id内容
				var Tid = Tids.eq(index).val();
				var Tname = Tnames.eq(index).text();
				/*********layui一个弹出输入框********/
				layers.prompt({
					  formType: 0,
					  value: Tname,
					  title: '请修改此类型',
					}, function(value, index, elem){  // 确认按钮回调
					  /**********将获取到的值传入修改**********/
					  updateTypeOne(Tid,value);
					  layer.close(index);
					}); 
			});
		}
	});
}
/**************添加类型*****************/
function insertTypeOne(tName) {
	$.ajax({
		type : "post",
		url : "/StruggleBlog/ajax_insertType.go",
		data : {
			"newT.TName" : tName
		},
		success : function(msg) {
			layers.open({
				title : "提示",
				content : msg,
				btn : '宝宝知道了'
			});
			getAllType("do","");
		}
	});
}
/***************修改类型****************/
function updateTypeOne(tId,tName) {
	 $.ajax({
		 type:"post",
		 url:"/StruggleBlog/ajax_updateType.go",
		 data:{
			"tp.TId" :tId,
			"tp.TName":tName
		 },
		 success:function(rs) {
			 layers.open({
					title : "提示",
					content : rs,
					btn : '宝宝知道了'
				});
				getAllType("do","");
		 }
	 });
}
/** ****************layuiTab生成********************** */
layui.use('element', function() {
	var element = layui.element();
	element.init(); // 这样element对动态生成的元素才会重新有效
	element.on('tab(filter)', function(data) {
		console.log(this); // 当前Tab标题所在的原始DOM元素
		console.log(data.index); // 得到当前Tab的所在下标
		console.log(data.elem); // 得到当前的Tab大容器
	});
});
/** ************************读取消息****************** */

var msg = $("#getMsg").val();
var info = $("#getInfo").val();
/** **********引入layer************ */
layui.use('layer', function() {
	var layer = layui.layer;
	if ("" != msg && msg != null) {
		layer.open({
			title : info + "--你好",
			content : msg + "点击确认关闭此窗口",
			btn : '没问题',
			yes : function() {
				// 关闭当前打开的页面
				var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
				if (index != undefined) {
					parent.layer.close(index); // 再执行关闭
				} else {
					layer.closeAll('dialog');
				}
			}
		});
	}
	if ("" != info && info != null) {
		if ("" == msg || msg == null) {
			// 创建一个日期对象
			var dd = new Date();
			var hour = dd.getHours();// 获取当前时
			if (hour > 0 && hour <= 6) {
				layer.open({
					title : info + "-深夜好",
					content : "夜深了，就不要敲代码了，早点休息吧！",
					shadeClose:true,
					time: 3000,
					btn : '宝宝知道了'
				});
			} else if (hour > 6 && hour <= 9) {
				layer.open({
					title : info + "-早上好",
					content : "早上要吃早餐，心态要好，代码不能忘了敲，加油！",
					shadeClose:true,
					time: 3000,
					btn : '宝宝知道了'
				});
			} else if (hour > 9 && hour <= 12) {
				layer.open({
					type : 0,
					title : info + "-上午好",
					content : "上午要保持好心情，代码不能忘了敲，加油！",
					shadeClose:true,
					time: 3000,
					btn : '宝宝知道了'
				});
			} else if (hour > 12 && hour <= 15) {
				layer.open({
					type : 0,
					title : info + "-中午好",
					content : "中午的话需要一点休息，下午才能更好的奋斗咯，加油！",
					shadeClose:true,
					time: 3000,
					btn : '宝宝知道了'
				});
			} else if (hour > 15 && hour <= 18) {
				layer.open({
					type : 0,
					title : info + "-下午好",
					content : "一天的工作快要结束了，出去走走吧，加油！",
					shadeClose:true,
					time: 3000,
					btn : '宝宝知道了'
				});
			} else if (hour > 18 && hour <= 21) {
				layer.open({
					type : 0,
					title : info + "-傍晚好",
					content : "没有出去散步吗，人是需要休息的，找本书看吧！",
					shadeClose:true,
					time: 3000,
					btn : '宝宝知道了'
				});
			} else if (hour > 21 && hour <= 24) {
				layer.open({
					type : 0,
					title : info + "-晚上好",
					content : "一天的快要结束了，总结一下这一天吧，加油！",
					shadeClose:true,
					time: 3000,
					btn : '宝宝知道了'
				});
			}
		}
	}
});
/** ***************生成wangEnditor富文本编辑器****************** */
// 获取元素
var div = document.getElementById('editor');
// 生成编辑器
var editor = new wangEditor(div);
editor.create();

/* 监听提交 */
$("#up-to-service").click(
		function() {
			var ii = editor.$txt.html(); // 获得文章内容
			var word = $("#getWord").val($.trim(ii)); // 将文章内容放到隐藏域中提交，解决wangEditor与layui的不兼容
			if ($("#page-title").val() == "" || $("#page-type").val() == ""
					|| $("#page-zy").val() == "" || word.val() == "<p><br></p>"
					|| $("#files").val() == "") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.open({
						type : 0,
						title : "警告",
						content : "请将检查文章是否编写完整！",
						btn : '宝宝知道了'
					});
				});
			} else {
				$("#go-page").submit()
			}
		});
/* 重置按钮 */
$("#reset-page").click(function() {
	if (confirm("确定要清空所有内容吗？将不保存任何内容")) {
		$("#page-title").val("");
		$("#page-type").val(0);
		$("#page-zy").val("");
		editor.clear();
		word.val("");
	} else {
		alert("哈哈，是点错了吧，下次小心点！");
	}

});
/**********************异步所有文章********************* */

function ajaxPage(state,like) {
	$.ajax({
		type : "post",
		url : "/StruggleBlog/ajax_getAllPages.go",
		dataType : "json",
		data : {
			state : state,
			like : like
		},
		success : function(pages) {
			if (pages.length != 0) {
				var html = "";
				for (var i = 0; i < pages.length; i++) {
					var time = pages[i].pageTime.replace(/T/, "&nbsp;");
					var state = pages[i].pageState;
					if (state == "do") {
						state = "可读写";
					} else {
						state = "回收站"
					}
					html += "<tr>";
					html += "<td>" + (i + 1) + "</td>";
					html += "<td> <a class='readAll' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="
							+ pages[i].pageId
							+ "&oper=view' target='_blank'>"
							+ pages[i].pageTitle
							+ "</a>"
							+ "<input type = 'hidden' class = 'pid' value = '"
							+ pages[i].pageId + "'></td>";
					html += "<td>" + pages[i].type.TName + "</td>";
					html += "<td>" + time + "</td>";
					html += "<td>" + pages[i].pageRead + "</td>";
					html += "<td>" + state + "</td>";
					html += "<td><a class='update-page' href='/StruggleBlog/ajax_getPageOne.go?po.pageId="
							+ pages[i].pageId
							+ "&oper=update' >修改</a>&nbsp;|&nbsp;"
							+ "<a class = 'deleteOne' href='javascript:void(0);'>删除</a></td>";
					html += "</tr>";

				}
				layui.use('layer', function() {
					var layer = layui.layer;
					$("#tbody").html(html);
					// 拿到元素数组
					var Pids = $(".pid");
					var deleteOnes = $(".deleteOne");
					$(".deleteOne").click(function() {
						/* 获取当前点击的标签对象在该对象数组中的位置 */
						var index = deleteOnes.index($(this));
						// 获取当前位置所对应行的id内容
						var Pid = Pids.eq(index).val();
						layer.confirm('是否确定要删除?文章将移入回收站内,当即游客不可访问!', {
							icon : 0,
							title : '提示'
						}, function() {
							deletePage(Pid);
							layer.close();
						});
					});
				});
			} else {
				layers.open({
					title : "提示",
					content : "什么鬼都没",
					btn : '宝宝知道了'
				});
			}
		}
	});
}
/** ****************删除文章操作******************** */
function deletePage(Pid) {
	$.ajax({
		type : "post",
		url : "/StruggleBlog/page_deletePage.go",
		data : {
			"page.pageId" : Pid,
			oper : "delete"
		},
		success : function(rs) {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg(rs);
			});
			ajaxPage("do","");
		}
	});
}

/***********************删除类型操作****************************/
function deleteType(Tid) {
	$.ajax({
		type : "post",
		url : "/StruggleBlog/ajax_deleteType.go",
		data : {
			tyId : Tid,
			oper : "delete"
		},
		success : function(rs) {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg(rs);
			});
			getAllType("do","");
		}
	});
}
/****************************异步所有链接*****************************/
function getAllLink(state,like) {
	$.ajax({
		type:"post",
		url:"/StruggleBlog/ajax_getAllLinks.go",
		data:{
			state:state,
			like:like
		},
		dataType:"json",
		success:function(links) {
			if(links.length!=0) {
				var  html = "";
				for(var i = 0;i<links.length;i++){
					var state = links[i].linkState;
					if (state == "do") {
						state = "可读写";
					} else {
						state = "回收站"
					}
					html += "<tr>";
					html += "<td>" + (i+1) + "</td>";
					html += "<td>"
							+ "<a class='links-a' href='"+links[i].linkUrl+"' target='_blank'><span class ='lName'>"+links[i].linkName+"</span></a>"
							+ "<input type = 'hidden' class = 'lid' value = '"
							+ links[i].linkId + "'></td>";
					html += "<td class='lUrl'><a class='links-a' href='"+links[i].linkUrl+"' target='_blank'>"+links[i].linkUrl+"</a></td>"
					html += "<td>" + state + "</td>";
					html += "<td><a class='update-link' href='javascript:void(0);'>修改</a>&nbsp;|&nbsp;"
							+ "<a class = 'delete-link' href='javascript:void(0);'>删除</a></td>";
					html += "</tr>";
				}
				$("#links-m").html(html);
				/******修改链接*******/
				var lIds = $(".lid");
				var lNames = $(".lName");
				var lUrls = $(".lUrl");
				var update_links = $(".update-link");
				var delete_links = $(".delete-link");
				$(".update-link").click(function() {
					var index = update_links.index($(this)); // 当前点击元素
					var lId = lIds.eq(index).val();
					var lName = lNames.eq(index).text();
					var lUrl = lUrls.eq(index).text();
					var thisLname = "";
					layers.prompt({
						  formType: 0,
						  value: lName,
						  title: '修改链接名称',
						}, function(value, index, elem){  // 确认按钮回调
							thisLname = value;
							layers.prompt({
								  formType: 0,
								  value: lUrl,
								  title: '修改链接地址',
								}, function(value, index, elem){  // 确认按钮回调
								  updateLinkOne(lId,thisLname,value)
								  layer.close(index);
								}); 
							 layer.close(index);
						}); 
				});
				$(".delete-link").click(function() {
					var index = delete_links.index($(this));
					var lId = lIds.eq(index).val();
					layers.confirm('是否确定要删除?链接将移入回收站内,当即游客不可访问!', {
						icon : 0,
						title : '提示'
					}, function() {
						deleteLink(lId);
						layers.close();
					});
				});
			} 
		}
	});
}
/***************************添加链接*********************************/
function insertlink(lName,lUrl) {
	$.ajax({
		type :"post",
		url :"/StruggleBlog/ajax_insertLink.go",
		data:{
			"newLink.linkName":lName,
			"newLink.linkUrl":lUrl
		},
		success:function(rs) {
			layers.open({
				title : "提示",
				content : rs,
				btn : '宝宝知道了'
			});
			getAllLink("do","");
		}
	});
}
/****************************修改链接***********************/
function updateLinkOne(lId,lName,lUrl) {
	 $.ajax({
		 type:"post",
		 url:"/StruggleBlog/ajax_updateLink.go",
		 data:{
			"lk.linkId" :lId,
			"lk.linkName":lName,
			"lk.linkUrl":lUrl
		 },
		 success:function(rs) {
			 layers.open({
					title : "提示",
					content : rs,
					btn : '宝宝知道了'
				});
			 	getAllLink("do","");
		 }
	 });
}
/**************************删除链接********************************/
function deleteLink(lId) {
	$.ajax({
		type:"post",
		url:"/StruggleBlog/ajax_deleteLink.go",
		data:{
			cuLinkId:lId
		},
		success:function(rs) {
		 layers.open({
				title : "提示",
				content : rs,
				btn : '宝宝知道了'
			});
		 	getAllLink("do","");
		}
	});
}
/***********************留言异步**************************/
/* 留言载入 */
function loadWords(a,b) {
	if(a==null&&b==null){
		a = 0;
		b = 30;
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
						reply = "已回复:"+reply;
					}
					if(reply==null){
						reply = "";
					}
					var aa = words[a].lwUrl;
					if(aa == null){
						aa = "#";
					}
					html +="<li><span><a href='"+aa+"' target='_blank'>"+words[a].lwName+"</a>&nbsp;|&nbsp;"+time+"：</span><p id='lww'>"+words[a].lwWord+"" +
							"。&nbsp;&nbsp;&nbsp;<a class='lw-r' href='javascript:;'>回复</a>&nbsp;|&nbsp;<a class='lw-d' href='javascript:;'>删除</a></p><p>"+reply+"</p></li>";
				}
				$("#lw").html(html);
			}else {
				$("#lw").text("呃-_-，什么鬼都没有！");
			}
		}
	});
}


