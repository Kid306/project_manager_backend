<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<base href="<%=basePath%>">	
	<meta name="description" content="Common form elements and layouts" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />	
	<!-- jsp文件头和头部 -->
	<title>查询</title>
	<%@ include file="${parentPath}/main/cssResource.jsp"%>   
	</head>
<body>
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<div class="row">
		<div class="col-sm-12">
							 <form id="${smallEntityName}Form" class="form-horizontal">
		         				<#list columnList as column>
		         				<#if column_index%3==0><div class="form-group"></#if>
									<label class="col-sm-1" for="${column.camelsColumnName}">${column.remarks}</label>
									<div class="col-sm-3">
								<#if column.simpleJavaClassName=='String'> 
										<input type="text"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  placeholder="${column.remarks}" maxlength=${column.columnSize} class="col-sm-10"  />
								<#elseif column.simpleJavaClassName=='Date'>
										<div class="input-group">
											<input  type="text"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}" dateISO=true  placeholder="${column.remarks}"  class="form-control date-picker" class="col-sm-10" data-date-format="yyyy-mm-dd">
											<span class="input-group-addon">
											<i class="fa fa-calendar bigger-110"></i>
											</span>
										</div>
								<#elseif column.simpleJavaClassName=='boolean'>
										<label class="class="col-sm-10"">
										<input  type="checkbox"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" chencked="${r'${data.'}${column.camelsColumnName}${r'}'}" value="${r'${data.'}${column.camelsColumnName}${r'}'}" class="ace ace-switch ace-switch-2">
										<span class="lbl">${column.remarks}</span>
										</label>
								<#elseif column.simpleJavaClassName=='Number'>
										<input type="text"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  placeholder="${column.remarks}"  number=true maxlength=${column.columnSize} class="col-sm-10" />
								<#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
										<input type="text"  id="${column.camelsColumnName}"  name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  placeholder="${column.remarks}"  digits=true maxlength=${column.columnSize} class="col-sm-10"/>
								<#else>
										<input type="text"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  placeholder="${column.remarks}"  number=true maxlength=${column.columnSize} class="col-sm-10"/>
								</#if>
									</div>
								<#if (((column_index-2)%3) ==0  && (column_index>0)) || (!column_has_next)></div></#if>
								</#list>    
							</form>
		</div><!--col-->
	</div><!--row-->
    <table id="${smallEntityName}Table" class="table  table-bordered  dataTable">
        <thead>
        <tr><th class="center">
				<label class="position-relative">
					<input type="checkbox" class="ace">
					<span class="lbl"></span>
				</label>
			</th>
			<th class="center">操&nbsp;&nbsp;作</th>
			<#list columnList as column>
			<th>${column.remarks}</th>
	  		</#list>
	  		
        </tr>
        </thead>
        <tbody></tbody>
        <!-- tbody是必须的 -->
    </table>
</div><!--main-container-->

<%@ include file="${parentPath}/main/jsResource.jsp"%>


<script type="text/javascript">


    var ${smallEntityName}Table;
    var ${smallEntityName}Validator;
    $(document).ready(function () {
        ${smallEntityName}Table = $('#${smallEntityName}Table').DataTable({
        	bAutoWidth: false,
        	"processing": true,
        	ajax:{
        		"url":"${baseRequestUri}${smallModulePathExt}/${smallEntityName}/selectList${entityName}ByPage.json",
        		"data":function(d){
        			var param = $("#${smallEntityName}Form").serialize(); 
        			return param;
        		}
        	},
            serverSide: false,
            "iDisplayLength" : 10, //默认显示的记录数 
            columns: [
				{"data": null},//序号列
				{"data": null},//操作按钮列
				<#list columnList as column>
				{"data":"${column.camelsColumnName}"}<#if column_has_next>,</#if>
		  		</#list>
                
            ],
            columnDefs: [
				{
				    "searchable": false,
				    "orderable": false,
				    "targets": [ 0 ],
				    "class":"center",
				    width:10,
				    render: function (a, b, c, d) {
				    	var html='<label class="position-relative">'+
									'<input type="checkbox" class="ace">'+
									'<span class="lbl"></span>'+
								  '</label>';
						return html;
				    }
				},              
                {
                	"searchable": false,
				    "orderable": false,
                    targets:[ 1 ],
                    "class":"center",
                    render: function (a, b, c, d) {
                    	
	                var html='<div class="btn-group">'+
								'<a class="green" href="javascript:void(0)"  onclick="toEdit${entityName}Page(<#list primaryKeysList as column>\''+c.${column.camelsColumnName}+'\'<#if column_has_next>,</#if></#list>)">'+
									'<i class="ace-icon fa fa-pencil bigger-130"></i>'+
								'</a>&nbsp;'+
								'<a class="red" href="javascript:void(0)"  onclick="doDelete${entityName}(<#list primaryKeysList as column>\''+c.${column.camelsColumnName}+'\'<#if column_has_next>,</#if></#list>)">'+
									'<i class="ace-icon fa fa-trash-o bigger-130"></i>'+
								'</a>'+
							'</div>';
             
                        return html;
                    }
                }

            ],
            "language": {
                "lengthMenu": "_MENU_ 条记录每页",
                "zeroRecords": "没有找到记录",
                "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty": "无记录",
                "infoFiltered": "(从 _MAX_ 条记录过滤)",
                "search" : "过滤",
                "paginate": {
                	"first":"首页",
                    "previous": "上一页",
                    "next": "下一页",
                    "last":"尾页"
                }
            },
        	dom:"<ftr><ip>",
            initComplete: function () {
                $("#${smallEntityName}Table_filter").append('<button id="toAddBtn" onclick="toAdd${entityName}Page()" class="btn  btn-default btn-sm pull-left"><i class="ace-icon fa fa-plus-circle bigger-110 green"></i>新增</button>');
                $("#${smallEntityName}Table_filter").append('<button id="batchDeleteBtn" onclick="doBatchDelete${entityName}()" class="btn  btn-danger btn-sm pull-left"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</button>');
                $("#${smallEntityName}Table_filter").append('<button id="searchBtn" onclick="doSearch${entityName}()" class="btn  btn-purple btn-sm pull-left"><i class="ace-icon fa fa-search icon-on-right bigger-110"></i>查询</button>');
            	$("#${smallEntityName}Table_info").addClass("col-sm-6");
                $("#${smallEntityName}Table_paginate").addClass("col-sm-6");
            }
        });
       
         //设置查询表单查询事件
	    ${smallEntityName}Validator = $("#${smallEntityName}Form").validate({
			 submitHandler: function(form) {  //通过之后回调 
				 ${smallEntityName}Table.ajax.reload();	     
			 }
	    });
    });
    
    /**处理单行被选中的情形 已提升为公共函数,如有特殊处理在自行放开.
    $(document).on('click', 'tbody tr' , function(){
    		var tr=$(this);
    		tr.find('td:first-child input:checkbox')
    		.each(function(){
    				this.checked=!this.checked;
    				tr.toggleClass('selected');
    		});
	});
    **/
    
    /**根据条件查询表格数据**/
    function doSearch${entityName}(){
    	if(${smallEntityName}Validator.valid()){
    		${smallEntityName}Table.ajax.reload();
    	}
    }
    
    /**根据主键删除一批数据**/
    function doBatchDelete${entityName}(){
    	if(confirm("确定要删除选中的"+ ${smallEntityName}Table.rows('.selected').data().length +'条数据吗?'))
    	{
    		showTips("处理中.....",5);
    		var obj=${smallEntityName}Table.rows('.selected').data();
        	var url="${baseRequestUri}${smallModulePathExt}/${smallEntityName}/batchDelete${entityName}.json";
        	<#list primaryKeysList as column>
        	var ${column.camelsColumnName}=new Array(obj.length);
        	</#list>
        	<#list primaryKeysList as column>
        	for(var i=0;i<obj.length;i++){
        		${column.camelsColumnName}[i]=obj[i].${column.camelsColumnName};
        	}
        	</#list>
        	var data={<#list primaryKeysList as column>"${column.camelsColumnName}":${column.camelsColumnName}<#if column_has_next>,</#if></#list>};
        	
        	$.post(url,data,function(result){
        		showTips(result.tips);
    			if(result.tips.isOk()){
        			${smallEntityName}Table.ajax.reload();
        		}
    		});
    	}
    }
    
	/**根据主键删除一条数据**/
    function doDelete${entityName}(<#list primaryKeysList as column>${column.camelsColumnName}<#if column_has_next>,</#if></#list>){
    	if(confirm("确定要删除吗?")){
	    	var url="${baseRequestUri}${smallModulePathExt}/${smallEntityName}/delete${entityName}ByPk.json";
	    	var data={<#list primaryKeysList as column>"${column.camelsColumnName}":${column.camelsColumnName}<#if column_has_next>,</#if></#list>};
	    	$.post(url,data,function(result){
	    		showTips(result.tips);
	    		if(result.tips.isOk()){
	        		${smallEntityName}Table.ajax.reload();
	        	}
			});
		}
    }
    function toAdd${entityName}Page(){
    	top.addTab("新增${tableRemarks}","${baseRequestUri}${smallModulePathExt}/${smallEntityName}/add${entityName}");
    }
    function toEdit${entityName}Page(<#list primaryKeysList as column>${column.camelsColumnName}<#if column_has_next>,</#if></#list>){
    	top.addTab("修改${tableRemarks}","${baseRequestUri}${smallModulePathExt}/${smallEntityName}/edit${entityName}?"+<#list primaryKeysList as column>"${column.camelsColumnName}="+${column.camelsColumnName}<#if column_has_next>"+</#if></#list>);
    }
    function toDetail${entityName}Page(<#list primaryKeysList as column>${column.camelsColumnName}<#if column_has_next>,</#if></#list>){
    	top.addTab("${tableRemarks}明细","${baseRequestUri}${smallModulePathExt}/${smallEntityName}/detail${entityName}?"+<#list primaryKeysList as column>"${column.camelsColumnName}="+${column.camelsColumnName}<#if column_has_next>"+</#if></#list>);
    }
    
 	/**日期控件初始化*/
    $('.date-picker').datepicker({language:"zh-CN"});
</script>
</body>
</html>



