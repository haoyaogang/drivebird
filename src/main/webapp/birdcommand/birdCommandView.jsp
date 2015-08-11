<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TbirdCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TbirdCommand.ALIAS_ADDTIME%></th>	
					<td>
						${birdCommand.addtime}							
					</td>							
					<th><%=TbirdCommand.ALIAS_COMMAND%></th>	
					<td>
						${birdCommand.command}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TbirdCommand.ALIAS_EQUIP_TYPE%></th>	
					<td>
						${birdCommand.equipType}							
					</td>							
					<th><%=TbirdCommand.ALIAS_REMARK%></th>	
					<td>
						${birdCommand.remark}							
					</td>							
				</tr>		
		</table>
	</div>
</div>