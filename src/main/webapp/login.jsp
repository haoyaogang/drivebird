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
</head>
<body>

<script type="text/javascript" charset="utf-8">
	var loginDialog;
	$(function() {
		loginDialog = $('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			buttons : [ {
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ]
		});

		$('#loginDialog input').keyup(function(event) {
			if (event.keyCode == '13') {
				loginFun();
			}
		});
		/* userLoginCombobox.combobox('textbox').keyup(function(event) {
			if (event.keyCode == '13') {
				loginFun();
			}
		});
		userLoginCombogrid.combogrid('textbox').keyup(function(event) {
			if (event.keyCode == '13') {
				loginFun();
			}
		}); */
	});
	function loginFun() {		
			var form = loginDialog.find('form');//选中的tab里面的form

			if (form.form('validate')) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/userController/login', form.serialize(), function(result) {
					if (result.success) {						
						window.location.href="index.jsp";
						$('#loginDialog').dialog('close');
					} else {
						$.messager.alert('错误', result.msg, 'error');
					}
					parent.$.messager.progress('close');
				}, "JSON");
			}
	
	}
</script>
<div id="loginDialog" title="用户登录"
	style="width: 330px; height: 220px; overflow: hidden; display: none;">
	<form method="post">
				<table class="table table-hover table-condensed">
					<tr>
						<th>登录名</th>
						<td><input name="name" type="text" placeholder="请输入登录名"
							class="easyui-validatebox" data-options="required:true"
							value="John"></td>
					</tr>
					<tr>
						<th>密码</th>
						<td><input name="pwd" type="password" placeholder="请输入密码"
							class="easyui-validatebox" data-options="required:true"
							value="123456"></td>
					</tr>
				</table>
			</form>
</div>
</body>
</html>