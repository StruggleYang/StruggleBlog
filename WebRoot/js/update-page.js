
$(document).ready(function() {
	getAllType();
});
/*****************生成wangEnditor富文本编辑器*******************/
//获取元素
var div = document.getElementById('editor');
// 生成编辑器
var editor = new wangEditor(div);
editor.create();

/*此处保留，去掉会发生一些不可描述问题  */
layui.use('form', function(){
	  var form = layui.form(); //只有执行了这一步，部分表单元素才会修饰成功
});
/* 监听提交 */
$("#up-to-service").click(function() {
	var ii = editor.$txt.html();  // 获得文章内容
	var word = $("#getWord").val($.trim(ii)); // 将文章内容放到隐藏域中提交，解决wangEditor与layui的不兼容
	if($("#page-title").val()==""||$("#page-type").val()==0||$("#page-zy").val()==""||word.val()=="<p><br></p>"){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.msg('请填写完整');
			});
	}else {
		if($("#files").val()==""||$("#files").val()==null){
			$("#ifnull").val("fileNull");
		}
		$("#go-page").submit();
	}
	
});
/*取消按钮*/
$("#close").click(function() {
	 if(confirm("确定要退出吗？将不保存任何内容")) {
		 window.history.back(-1);
        } else {
           alert("哈哈，是点错了吧，下次小心点！");
        }
	
});

/**********************异步所有类型**********************/
function getAllType() {
	$.ajax({
		type:"post",
		url:"/StruggleBlog/ajax_getAllType.go",
		dataType:"json",
		data:{
			state:"do"
		},
		success:function(types) {
			var html = "<option value='0'>请选择类型</option>";
			var dl_html = "<dd lay-value='0' class='layui-this'>请选择类型</dd>";
			if(types.length>0) {
				for(var a=0;a<types.length;a++){
					html +="<option value='"+types[a].TId+"'>"+types[a].TName+"</option>";
					dl_html +="<dd lay-value='"+types[a].TId+"' class=''>"+types[a].TName+"</dd>";
				}
			}
			$("#page-type").html(html);
			$("#page-type-dl").html(dl_html);
			var isSed = $("#thisTid").val();
			$("#page-type option[value='"+isSed+"']").attr("selected","selected");
		}
	});
}
