<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout">

	<div data-options="region:'center',border:false">
		<div id="index_tabs" class="easyui-tabs" data-options="fit:true">
			<div title="基础数据" data-options="href:'api_base.jsp'"
				style="padding: 1px"></div>
			<div title="登录" data-options="href:'api_login.jsp'"
				style="padding: 1px"></div>
			<div title="获取在线设备" data-options="href:'api_equip_list.jsp'"
				style="padding: 1px"></div>
			<div title="指令列表" data-options="href:'api_command_list.jsp'"
				style="padding: 1px"></div>
			<div title="发送指令" data-options="href:'api_send_command.jsp'"
				style="padding: 1px"></div>	
				
			
		</div>
	</div>
</body>
</html>