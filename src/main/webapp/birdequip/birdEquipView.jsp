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
											
					<th><%=TbirdEquip.ALIAS_NAME%></th>	
					<td>
						${birdEquip.name}							
					</td>	
					<th><%=TbirdEquip.ALIAS_GROUP_TYPE%></th>	
					<td>
						${birdEquip.groupTypeZh}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TbirdEquip.ALIAS_DTUTYPE%></th>	
					<td>
						${birdEquip.dtutype}							
					</td>	
					<th><%=TbirdEquip.ALIAS_STATUS%></th>	
					<td>
						${birdEquip.statusZh}							
					</td>							
											
				</tr>		
					
				<tr>	
					<th><%=TbirdEquip.ALIAS_PWD%></th>	
					<td>
						${birdEquip.pwd}							
					</td>							
					<th><%=TbirdEquip.ALIAS_LOCATION%></th>	
					<td>
						${birdEquip.location}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TbirdEquip.ALIAS_REMARK%></th>	
					<td colspan="3">
						${birdEquip.remark}							
					</td>							
												
				</tr>	
				<tr>	
					<th><%=TbirdEquip.ALIAS_EQUIP_TYPE%></th>	
					<td>
						${birdEquip.equipTypeZh}							
					</td>							
					<th><%=TbirdEquip.ALIAS_CHANGETIME%></th>	
					<td>
						${birdEquip.changetime}							
					</td>						
				</tr>		
				<tr>	
					<th><%=TbirdEquip.ALIAS_ADDTIME%></th>	
					<td>
						${birdEquip.addtime}							
					</td>	
					<th><%=TbirdEquip.ALIAS_UPDATETIME%></th>	
					<td>
						${birdEquip.updatetime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>