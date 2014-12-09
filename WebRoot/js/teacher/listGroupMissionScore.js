    /**
	 * 
	 * @param data
	 * @param leaderid
	 * @param groupid
	 */
	function addAStudentToGroup(data,leaderid,groupid){
		var credit;
		if(data.credit==null)
			credit="未评分";
		else credit=data.credit;
		var inputdata ='';
		var tableId='#groupTable-'+groupid+' tbody';
		if(data.id==leaderid){
		 inputdata = '\
			<tr id="tr-'+leaderid+'">\
				<td>&nbsp</td>\
				<td>'+data.studentCode+'</td>\
				<td>'+data.name+'</td>\
				<td>'+data.studentGrade+'</td>\
				<td>'+data.stuClass+'</td>\
				<td>组长</td>\
				<td>'+credit+'</td>\
				<td>\
				</td>\
			</tr>\
		';
		 $(tableId).prepend(inputdata) ;
		}else{
			inputdata = '\
				<tr id="tr-'+data.id+'">\
					<td>&nbsp</td>\
					<td>'+data.studentCode+'</td>\
					<td>'+data.name+'</td>\
					<td>'+data.studentGrade+'</td>\
					<td>'+data.stuClass+'</td>\
					<td>组员</a></td>\
					<td>'+credit+'</td>\
					</td>\
				</tr>\
				';
			$(tableId).append(inputdata) ;
		}
	}

	/**
	 * 获取小组成员列表
	 * @param groupid
	 * @param e
	 */
	function getGroupDetail(groupid,courseid,cmissionid,e){
		var $this=$(e);
		var inputdata = '{"cmgroupid":'+groupid+',"courseid":'+courseid+',"cmissionid":'+cmissionid+'}';
		if($this.parent().next(".groupContext").css("display") == "none"){
			$.ajax({
				cache : true,
				type:"POST",
				url : "/classInfo/tch/getCmgroupMemberDetialScore.action",//此处不能使用单引号代替双引号
				//dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				data : inputdata,
				error: function (request, message, ex) {
				    alert(request.responseText);
				},
				success : function(data) {
					if(data.ajaxResult=="success"){
//						resetForm();
						var groupTableId= "#groupTable-"+data.cmgroupid;
						$(groupTableId).find("tr").not("[name='tableTitle']").remove();
							
						for(var i = data.cmstudentDetailViews.length-1;i>=0;i--){
							addAStudentToGroup(data.cmstudentDetailViews[i],data.leaderid,data.cmgroupid);
						}
//						autoIndex();
//					   	tableGap();
					}
				}
				});
			$this.html("━");
		}else{
			$this.html("╋");
		}
		$this.parent().next(".groupContext").slideToggle();
	}
	
	

