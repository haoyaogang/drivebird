<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TbirdEquip" %>
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
					<th><%=TbirdEquip.ALIAS_ADDTIME%></th>	
					<td>
						${birdEquip.addtime}							
					</td>							
					<th><%=TbirdEquip.ALIAS_NAME%></th>	
					<td>
						${birdEquip.name}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TbirdEquip.ALIAS_STATUS%></th>	
					<td>
						${birdEquip.status}							
					</td>							
					<th><%=TbirdEquip.ALIAS_EQUIP_TYPE%></th>	
					<td>
						${birdEquip.equipType}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TbirdEquip.ALIAS_GROUP%></th>	
					<td>
						${birdEquip.group}							
					</td>							
					<th><%=TbirdEquip.ALIAS_LOCATION%></th>	
					<td>
						${birdEquip.location}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TbirdEquip.ALIAS_REMARK%></th>	
					<td>
						${birdEquip.remark}							
					</td>							
					<th><%=TbirdEquip.ALIAS_CHANGETIME%></th>	
					<td>
						${birdEquip.changetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TbirdEquip.ALIAS_UPDATETIME%></th>	
					<td>
						${birdEquip.updatetime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>