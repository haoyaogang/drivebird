<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	
	function logoutFun(b) {
		$.getJSON('${pageContext.request.contextPath}/userController/logout', {
			t : new Date()
		}, function(result) {
			if (b) {
				location.replace('${pageContext.request.contextPath}/');
			} else {
				$('#sessionInfoDiv').html('');
			}
		});
	}

</script>
<div id="sessionInfoDiv"
	style="position: absolute; right: 0px; top: 0px;"
	class="alert alert-info">
	<c:if test="${sessionInfo.id != null}">[<strong>${sessionInfo.name}</strong>]，欢迎你！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！</c:if>
</div>
<div style="position: absolute; right: 0px; bottom: 0px;">
	<a 	href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">注销</a>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="logoutFun(true);">退出系统</div>
</div>