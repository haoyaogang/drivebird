<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TbirdEquip" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>BirdEquip管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/birdEquipController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/birdEquipController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/birdEquipController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/birdEquipController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			checkbox : false,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100,
				checkbox : true
				}, {
				field : 'name',
				title : '<%=TbirdEquip.ALIAS_NAME%>',
				width : 100		
				}, {
				field : 'statusZh',
				title : '<%=TbirdEquip.ALIAS_STATUS%>',
				width : 50		
				}, {
				field : 'groupTypeZh',
				title : '<%=TbirdEquip.ALIAS_GROUP_TYPE%>',
				width : 50		
				}, {
				field : 'dtutype',
				title : '<%=TbirdEquip.ALIAS_DTUTYPE%>',
				width : 50		
				}, {
				field : 'pwd',
				title : '<%=TbirdEquip.ALIAS_PWD%>',
				width : 50		
				},{
				field : 'voice',
				title : '<%=TbirdEquip.ALIAS_VOICE%>',
				width : 50
				}, {
				field : 'equipTypeZh',
				title : '<%=TbirdEquip.ALIAS_EQUIP_TYPE%>',
				width : 50		
				}, {
				field : 'changetime',
				title : '<%=TbirdEquip.ALIAS_CHANGETIME%>',
				width : 50		
				}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/link.png');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前数据？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/birdEquipController/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑数据',
			width : 780,
			height : 320,
			href : '${pageContext.request.contextPath}/birdEquipController/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function openSendMessage(id) {
		var ids = new Array();
		if (id == undefined) {
			var rows = dataGrid.datagrid('getChecked');
			//id = rows[0].id;
			for(var i = 0 ;i<rows.length;i++){
				ids.push(rows[0].id);
			}
		}else{
			ids.push(id);
		}
		if(ids.length == 0){
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要发送指令的记录！'
			});
			return;
		}
		
		parent.$.modalDialog({
			title : '发送指令',
			width : 780,
			height : 320,
			href : '${pageContext.request.contextPath}/birdEquipController/messagePage?id=' + ids.join(","),
			buttons : [ {
				text : '发送',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function groupEquip(id) {
		var ids = new Array();
		if (id == undefined) {
			var rows = dataGrid.datagrid('getChecked');
			//id = rows[0].id;
			for(var i = 0 ;i<rows.length;i++){
				ids.push(rows[0].id);
			}
		}else{
			ids.push(id);
		}
		if(ids.length == 0){
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要分组的记录！'
			});
			return;
		}
		parent.$.modalDialog({
			title : '分组',
			width : 780,
			height : 320,
			href : '${pageContext.request.contextPath}/birdEquipController/groupPage?id=' + ids.join(","),
			buttons : [ {
				text : '分组',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function viewFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '查看数据',
			width : 780,
			height : 320,
			href : '${pageContext.request.contextPath}/birdEquipController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 320,
			href : '${pageContext.request.contextPath}/birdEquipController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	function downloadTable(){
		var options = dataGrid.datagrid("options");
		var $colums = [];		
		$.merge($colums, options.columns); 
		$.merge($colums, options.frozenColumns);
		var columsStr = JSON.stringify($colums);
	    $('#downloadTable').form('submit', {
	        url:'${pageContext.request.contextPath}/birdEquipController/download',
	        onSubmit: function(param){
	        	$.extend(param, $.serializeObject($('#searchForm')));
	        	param.downloadFields = columsStr;
	        	param.page = options.pageNumber;
	        	param.rows = options.pageSize;
	        	
       	 }
        }); 
	}
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>								
							<th><%=TbirdEquip.ALIAS_NAME%></th>	
							<td>
											<input type="text" name="name" maxlength="72" class="span2"/>
							</td>
							<th><%=TbirdEquip.ALIAS_STATUS%></th>	
							<td>
											<jb:select dataType="ST" name="status"></jb:select>	
							</td>
							<th><%=TbirdEquip.ALIAS_GROUP_TYPE%></th>	
							<td>
											<jb:select dataType="GP" name="groupType"></jb:select>	
							</td>
							<th><%=TbirdEquip.ALIAS_EQUIP_TYPE%></th>	
							<td>
											<jb:select dataType="ET" name="equipType"></jb:select>	
							</td>
							
						</tr>	
					
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/birdEquipController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/birdEquipController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/birdEquipController/messagePage')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'sound',plain:true" onclick="openSendMessage();">发送指令</a>		
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/birdEquipController/groupPage')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'tux',plain:true" onclick="groupEquip();">分组</a>		
		</c:if>
	</div>	
</body>
</html>