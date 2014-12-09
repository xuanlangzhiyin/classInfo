
/*!
 * @名称：common.js
 * @功能：一般通用js
 */



/**
 * 页面初始化
 * 加载初始化函数
 */
$(document).ready(function(){
	autoFooter();//footer始终置底
	tableGap();
	
});


/**
 * 页面常驻函数
 */
$(document).ready(function(){


	//切换添加学生框
	
	$("#addStudent").toggle(function(){
		$(this).addClass("active");
		$(".searchBox").hide();
		$("#addStudentBox").slideDown(500);
	},
	function(){
		$(this).removeClass("active");
		$("#addStudentBox").slideUp(500);
	});
});

/**
 * 获取窗口大小
 * 写入容器类
 * 使footer始终置底
 */
function autoFooter(){
	var height =window.screen.availHeight;
	$("#wapper").css("min-height",height);
}

/**
 * 自动设置表格间隔显示
 */
function tableGap(){
	$("tr").removeClass("cBlue");
	for (var i = $("table").length - 1; i >= 0; i--) {
			$("table").eq(i).find("tr:odd").addClass("cBlue");
	};
}


/**
 * TAB标签切换显示
 * 首先隐藏标签内容项，默认显示第一页
 * i为需要显示的标签页，e为点击的标签项（this）
 */
$(document).ready(function(){  
	$(".tabCon").hide();
	var tabid ="#tab"+$("#activeTab").attr("pid");
	$(tabid).show();
})	
/**
 * TAB标签切换显示
 * @param i  i为需要显示的标签页
 * @param e  e为点击的标签项（this）
 */
function showTab(i,e){
	var $this=$(e);
	$(".tabTitle span").removeClass("active");
	$this.addClass("active");
	var tabId="#tab"+i;
	$(".tabCon").hide();
	$(tabId).fadeIn(800);
}


/**
 * #main-box 中切换显示 .boxContext中的内容
 * @param e 为点击项（this）
 */	
function slideBox(e){
	var $this=$(e);
	if($this.next(".boxContext").css("display") == "none"){
		$this.addClass("active");
	}else{
		$this.removeClass("active");
	}
	$this.next(".boxContext").slideToggle();
}

$(document).ready(function(){  
	$(".groupContext").hide();
});	
function slideTable(e){
	var $this=$(e);
	if($this.parent().next(".groupContext").css("display") == "none"){
		$this.html("━");
	}else{
		$this.html("╋");
	}
	$this.parent().next(".groupContext").slideToggle();
}
function slidePitem(e){
		var $this=$(e);
	if($this.parent().next(".boxFrom").css("display") == "none"){
		$this.html("━");
	}else{
		$this.html("╋");
	}
	$this.parent().next(".boxFrom").slideToggle();
}


/**
 * 绑定表格分页与查询操作
 * @param tableid     分页表格ID
 * @param pagerClass  表格对应分页标识class
 */
function tablePager(tableid,pagerClass){
	// define pager options for #studentList4viewBox
	var pagerOptions = {
		// target the pager markup - see the HTML block below
		container: $(pagerClass),
		// output string - default is '{page}/{totalPages}'; possible variables: {page}, {totalPages}, {startRow}, {endRow} and {totalRows}
		output: '{page}/{totalPages}',
		// if true, the table will remain the same height no matter how many records are displayed. The space is made up by an empty
		// table row set to a height to compensate; default is false
		fixedHeight: true,
		// remove rows from the table to speed up the sort of large tables.
		// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
		removeRows: false,
		// go to page selector - select dropdown that sets the current page
		cssGoto: '.gotoPage'
	};

	// Initialize tablesorter
	// ***********************
	$(tableid)
		.tablesorter({
			theme: 'blue',
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
			widthFixed: true,
			widgets: ['zebra', 'filter']
		})

		// initialize the pager plugin
		// ****************************
		.tablesorterPager(pagerOptions);

		// Destroy pager / Restore pager
		// **************
		$('button:contains(Destroy)').click(function(){
			// Exterminate, annhilate, destroy! http://www.youtube.com/watch?v=LOqn8FxuyFs
			var $t = $(this);
			if (/Destroy/.test( $t.text() )){
				$(tableid).trigger('destroy.pager');
				$t.text('Restore Pager');
			} else {
				$(tableid).tablesorterPager(pagerOptions);
				$t.text('Destroy Pager');
			}
			return false;
		});

		// Disable / Enable
		// **************
		$('.toggle').click(function(){
			var mode = /Disable/.test( $(this).text() );
			$(tableid).trigger( (mode ? 'disable' : 'enable') + '.pager');
			$(this).text( (mode ? 'Enable' : 'Disable') + 'Pager');
			return false;
		});
		$(tableid).bind('pagerChange', function(){
			// pager automatically enables when table is sorted.
			$('.toggle').text('Disable');
		});

	
}

function graytablePager(tableid,pagerClass){
	// define pager options for #studentList4viewBox
	var pagerOptions = {
		// target the pager markup - see the HTML block below
		container: $(pagerClass),
		// output string - default is '{page}/{totalPages}'; possible variables: {page}, {totalPages}, {startRow}, {endRow} and {totalRows}
		output: '{page}/{totalPages}',
		// if true, the table will remain the same height no matter how many records are displayed. The space is made up by an empty
		// table row set to a height to compensate; default is false
		fixedHeight: true,
		// remove rows from the table to speed up the sort of large tables.
		// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
		removeRows: false,
		// go to page selector - select dropdown that sets the current page
		cssGoto: '.gotoPage'
	};

	// Initialize tablesorter
	// ***********************
	$(tableid)
		.tablesorter({
			theme: 'gray',
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
			widthFixed: true,
			widgets: ['zebra', 'filter']
		})

		// initialize the pager plugin
		// ****************************
		.tablesorterPager(pagerOptions);

		// Destroy pager / Restore pager
		// **************
		$('button:contains(Destroy)').click(function(){
			// Exterminate, annhilate, destroy! http://www.youtube.com/watch?v=LOqn8FxuyFs
			var $t = $(this);
			if (/Destroy/.test( $t.text() )){
				$(tableid).trigger('destroy.pager');
				$t.text('Restore Pager');
			} else {
				$(tableid).tablesorterPager(pagerOptions);
				$t.text('Destroy Pager');
			}
			return false;
		});

		// Disable / Enable
		// **************
		$('.toggle').click(function(){
			var mode = /Disable/.test( $(this).text() );
			$(tableid).trigger( (mode ? 'disable' : 'enable') + '.pager');
			$(this).text( (mode ? 'Enable' : 'Disable') + 'Pager');
			return false;
		});
		$(tableid).bind('pagerChange', function(){
			// pager automatically enables when table is sorted.
			$('.toggle').text('Disable');
		});

	
}



/**
 * 全选
 * @param
 */
function checkAll(e){
	$(":checkbox:visible").attr("checked",true);
	var temp=$("#delCheckAll").is(":hidden");
	if(temp==true){
		$("#chkAll").hide();
		$("#delCheckAll").show();}

}
/**
 * 取消全选
 * @param
 * 
 */
function delCheck(){
   $(":checkbox:visible").attr("checked",false);
   var temp=$("#chkAll").is(":hidden");
   if (temp==true){	
	   $("#chkAll").show();
		$("#delCheckAll").hide();}

}
/**
 * 删除多余p
 * @param
 * 
 */
function delP(){
	$("p").each(function(){
    	if($(this).text() == ""){
    		$(this).remove();}
	});
}
/**
 * 切换tab
 * @param
 * 
 */
function changeTab(){
  	if(window.location.pathname.indexOf('viewCourse')>-1){
  		$("#viewCourse").addClass("on");
  	}
  	else if (window.location.pathname.indexOf('listCmission')>-1){
  		$("#viewMission").addClass("on");
  		
  	}
  	else{
  		$("#viewEvaluate").addClass("on");
  	}
	
}
