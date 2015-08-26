<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TbirdCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/birdEquipController/sendMessage',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		$('input[type="radio"]').click(function(){
			var val = $(this).val();
			if(val == 0){
				$("#row1").show();
				$("#row2").hide()
			}else{
				$("#row2").show();
				$("#row1").hide()
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">	
		<form id="form" method="post">		
			<input type="hidden" name="id" value = "${id}"/>		
			<table class="table table-hover table-condensed">
				<tr>

					<th>指令类型</th>
					<td >
						<input type="radio" name="commandType" value=0 id="commandType01" checked="checked"><label for="commandType01" style="display:inline;">常规</label>
						<input type="radio" name="commandType" value=1 id="commandType02"><label for="commandType02" style="display:inline;">音量</label>
					</td>


				</tr>
				<tr id="row1">
											
					<th><%=TbirdCommand.ALIAS_COMMAND%></th>	
					<td>
							<jb:selectSql dataType="SL01" name="command"></jb:selectSql>	
					</td>


				</tr>
				<tr id="row2" style="display: none;">

					<td colspan="2">
						<input class="span2" name="voice" id="voice" type="hidden"/>
						<input  class="easyui-slider" value="12" style="width:300px" data-options="
				showTip:true,
				rule: [0,'|',25,'|',50,'|',75,'|',100],
				tipFormatter: function(value){
					return value;
				},
				onChange: function(value){
					$('#voice').val(value);
				}">		</td>

				</tr>

			</table>		
		</form>
	</div>
</div>