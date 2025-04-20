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
		<title>修改</title>
		<base href="<%=basePath%>">	
		<meta name="description" content="Common form elements and layouts" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="${parentPath}/main/cssResource.jsp"%>
	</head>

	<body class="no-skin">
		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
					<div class="page-header">
						<h4>
							${baseRequestUri}${smallModulePathExt}/${tableRemarks}
							<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								新增${tableRemarks}
							</small>
						</h4>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-sm-12">
							<!-- PAGE CONTENT BEGINS -->
							<form id="entityForm"  class="form-horizontal" role="form">
								<!-- #section:elements.form -->
								<#list primaryKeysList as column>
								<#if column_index%2==0><div class="form-group"></#if>
									<label class="col-sm-2 control-label no-padding-right" for="${column.camelsColumnName}">${column.remarks}</label>
									<div class="col-sm-4">
								<#if column.simpleJavaClassName=='String'> 
										<input type="text" readonly  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"   maxlength=${column.columnSize}   class="col-xs-10 col-sm-10" />
								<#elseif column.simpleJavaClassName=='Date'>
										 <div class="input-group col-sm-5">
											<input  type="text" readonly id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="<spring:eval expression="data.${column.camelsColumnName}" />" dateISO=true class="form-control date-picker"  class="col-xs-10 col-sm-10" data-date-format="yyyy-mm-dd">
											<span class="input-group-addon">
											<i class="fa fa-calendar bigger-110"></i>
											</span>
										</div>
								<#elseif column.simpleJavaClassName=='boolean'>
										<label class="col-sm-5">
										<input  type="checkbox"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" chencked="${r'${data.'}${column.camelsColumnName}${r'}'}" value="${r'${data.'}${column.camelsColumnName}${r'}'}" class="ace ace-switch ace-switch-5">
										<span class="lbl">${column.remarks}</span>
										</label>
								<#elseif column.simpleJavaClassName=='Number'>
										<input type="text"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  number=true maxlength=${column.columnSize}  class="col-xs-10 col-sm-10" />
								<#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
										<input type="text"  id="${column.camelsColumnName}"  name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  digits=true maxlength=${column.columnSize}    class="col-xs-10 col-sm-10"/>
								<#else>
										<input type="text"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  number=true maxlength=${column.columnSize}   class="col-xs-10 col-sm-10"/>
								</#if>
									</div>
								<#if (((column_index-1)%2) ==0  && (column_index>0)) || (!column_has_next)></div></#if>
								</#list>
								
								<#list columnExcludePkList as column>
								<#if column_index%2==0><div class="form-group"></#if>
									<label class="col-sm-2 control-label no-padding-right" for="${column.camelsColumnName}">${column.remarks}</label>
									<div class="col-sm-4">
								<#if column.simpleJavaClassName=='String'> 
										<input type="text" id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  placeholder="${column.remarks}" <#if column.isNullAble=='NO'>required</#if>  maxlength=${column.columnSize}  class="col-xs-10 col-sm-10"  />
								<#elseif column.simpleJavaClassName=='Date'>
										 <div class="input-group col-sm-5">
											<input  type="text"  id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="<spring:eval expression="data.${column.camelsColumnName}" />" dateISO=true  placeholder="${column.remarks}" class="form-control date-picker"  class="col-xs-10 col-sm-10" data-date-format="yyyy-mm-dd">
											<span class="input-group-addon">
											<i class="fa fa-calendar bigger-110"></i>
											</span>
										</div>
								<#elseif column.simpleJavaClassName=='boolean'>
										<label class="col-sm-5">
										<input  type="checkbox" id="${column.camelsColumnName}" name="${column.camelsColumnName}" chencked="${r'${data.'}${column.camelsColumnName}${r'}'}" value="${r'${data.'}${column.camelsColumnName}${r'}'}"  placeholder="${column.remarks}" class="ace ace-switch ace-switch-5">
										<span class="lbl">${column.remarks}</span>
										</label>
								<#elseif column.simpleJavaClassName=='Number'>
										<input type="text" id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}" <#if column.isNullAble=='NO'>required=true </#if> number=true maxlength=${column.columnSize}  placeholder="${column.remarks}"   class="col-xs-10 col-sm-10"/>
								<#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
										<input type="text" id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}" <#if column.isNullAble=='NO'>required=true </#if> digits=true maxlength=${column.columnSize}  placeholder="${column.remarks}"  class="col-xs-10 col-sm-10"/>
								<#else>
										<input type="text" id="${column.camelsColumnName}" name="${column.camelsColumnName}" value="${r'${data.'}${column.camelsColumnName}${r'}'}" <#if column.isNullAble=='NO'>required=true </#if> number=true maxlength=${column.columnSize} placeholder="${column.remarks}"   class="col-xs-10 col-sm-10"/>
								</#if>
									</div>
								<#if (((column_index-1)%2) ==0  && (column_index>0)) || (!column_has_next)></div></#if>
								</#list>
								
								
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button id="submitBtn"  class="btn btn-info" type="submit">
											<i class="ace-icon fa fa-check bigger-110"></i>
											提交
										</button>

										&nbsp; &nbsp; &nbsp;
										<button class="btn"    id="resetBtn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i>
											重置
										</button>
									</div>
								</div>
							</div><!--form-->
							<div class="hr hr-24"></div>
						</div><!-- /.col -->
					</div><!-- /.row -->
		</div><!-- /.main-container -->
		<%@ include file="${parentPath}/main/jsResource.jsp"%>
		<script type="text/javascript">
		
		$(document).ready(function() {
		 	var validator = $("#entityForm").validate({
				 submitHandler: function(form) {  //通过之后回调 
				     var param = $("#entityForm").serialize(); 
				     $.ajax({ 
						url : "${baseRequestUri}${smallModulePathExt}/${smallEntityName}/update${entityName}ByPk.json", 
						type : "post", 
						dataType : "json", 
						data: param, 
						success : function(result) { 
        					showTips(result.tips);
							if(result.tips.isOk()){
								top.closeActiveTab();
							}
						} 
				     }); 
				 }
		    });
		    $("#resetBtn").click(function() {
		        validator.resetForm();
		    });

		});
		
		/**日期控件初始化*/
    	$('.date-picker').datepicker({language:"zh-CN"});
    	
		</script>
	</body>
</html>
