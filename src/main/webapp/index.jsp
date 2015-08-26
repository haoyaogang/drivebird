<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.listener.Application"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title><%=Application.getString("SV001")%></title>
<jsp:include page="inc.jsp"></jsp:include>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/themes/default/easyui.css"
	type="text/css">
<script type="text/javascript">
	var index_layout;
	var controlform;
	var dataGrid;
	$.ajax({ url: '${pageContext.request.contextPath}/birdCommandController/dataGrid', dataType: "json",data:{rows:10000}, success: function(data){
        var buttonCommand = $('#buttonCommand');
        
		$(data.rows).each(function(i){
			var _this = data.rows[i];
			buttonCommand.append('<span class="badge badge-info"  onclick="sendCommand(\''+_this.command+'\')">'+_this.name+'</span>');
        });
      }});
	
	function sendCommand(command){
		$('#commandType').val(0);
		$('#command').val(command);
		controlform.submit();
	}
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
		controlform = $('#controlform').form({
			url : '${pageContext.request.contextPath}/birdEquipController/sendMessage',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				
				var equipId = $('#equipId').val();
				if(!equipId){
					//alert(equipId);
					parent.$.messager.alert('提示', "请选择设备", 'info');
					isValid = false;
				}
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					
				} else {
					$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/birdEquipController/dataGrid?rows=10000',
			
			border : false,
			pagination : false,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : false,
			rownumbers : false,
			singleSelect : true,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100,
				checkbox : false,
				hidden:true
				}, {
				field : 'name',
				title : '设备名称',
				width : 192	
				}
			 ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				$(this).datagrid('tooltip');
			},
			onClickRow:function(index,row){
				$("#equipId").val(row.id);
				var voiceDecode = row.voiceDecode;
				if(voiceDecode){
					voiceDecode = voiceDecode.replace("音量","");
					voiceDecode = voiceDecode.split("#");
					$('#slider').slider('setValue',voiceDecode[0]);
				}				
			}	
		});
	});
</script>
</head>
<body>

	<div id="index_layout">
		<div
			data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north1.jsp'"
			style="height: 70px; overflow: hidden;" class="logo"></div>
		<div data-options="region:'west',split:true,border:true" style="width: 200px; overflow: hidden;">
			<table id="dataGrid"></table></div>
		<div data-options="region:'center'" style="overflow: hidden;">
			<div class="easyui-panel" title="音量控制" style="width:700px;height:200px;padding:10px;">
			<form id="controlform" method="post">		
				<input type="hidden" name="id" id="equipId" />	
				<input type="hidden" name="commandType" id="commandType" value = 0/>	
				<input type="hidden" name="command" id="command" value =""/>	
				<input type="hidden" name="voice" id="voice" value = 0/>					
			</form>
			<br>
		        <input  class="easyui-slider" id="slider" value="12" style="width:300px" data-options="
				showTip:true,
				rule: [0,'|',25,'|',50,'|',75,'|',100],
				tipFormatter: function(value){
					return value;
				},
				onSlideEnd: function(value){
					$('#voice').val(value);
					$('#commandType').val(1);
					controlform.submit();
				},
				onChange: function(value){
					
				}">	
   			 </div>
   			 
   			 <div class="easyui-panel" title="常规控制" style="width:700px;height:200px;padding:10px;">
		        <div style="padding:5px;" id="buttonCommand">			        
			    </div>
   			 </div>
		</div>
		<div
			data-options="region:'south',border:false"
			style="height: 30px; overflow: hidden;"></div>
	</div>
</body>
</html>