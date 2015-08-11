package jb.util;

import jb.listener.Application;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailSendUtil {
	
	private static final String SV210 = "SV210";
	private static final String SV211 = "SV211";
	private static final String SV212 = "SV212";

	/**
	 * 普通邮件发送 sendMessage
	 * 
	 * @param hostName 服务器地址
	 * @param tuUser 接收者
	 * @param fromUser 发送者
	 * @param password 密码
	 * @param title 标题
	 * @param content 内容
	 */
	public static boolean sendMessage(String toUser, String title, String content) {
		SimpleEmail email = new SimpleEmail();
		try {
			email.setCharset("UTF-8");
			email.setHostName(Application.getString(SV210));
			email.addTo(toUser);
			email.setFrom(Application.getString(SV211));
			email.setAuthentication(Application.getString(SV211), Application.getString(SV212));
			email.setSubject(title);
			email.setMsg(content);
			email.send();
			System.out.println("-------------------邮件发送成功！Congratulations, mail sent successfully!-------------------");
			return true;
		} catch (EmailException e) {
			System.out.println("-------------------邮件发送失败！Would you please change the password of email failed!-------------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 含有(css样式或图片)邮件发送 sendHtmlMessage
	 * 
	 * @param hostName 服务器地址
	 * @param tuUser 接收者
	 * @param fromUser 发送者
	 * @param password 密码
	 * @param title 标题
	 * @param content 内容
	 */	
	public static boolean sendHtmlMessage(String toUser, String title, String content) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF-8");
			email.setHostName(Application.getString(SV210));
			email.addTo(toUser);
			email.setFrom(Application.getString(SV211));
			email.setAuthentication(Application.getString(SV211), Application.getString(SV212));
			email.setSubject(title);
			email.setHtmlMsg("<html>"+content+"</html>");
			email.setTextMsg(title);
			email.send();
			System.out.println("邮件发送成功！Congratulations, mail sent successfully! ");
			return true;
		} catch (EmailException e) {
			System.out.println("邮件发送失败！Would you please change the password of email failed!");
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 * @throws EmailException 
	 */
	public static boolean sendPassword(String toUser, String password) {
		String title = "潜伴账号密码安全邮件（系统邮件，请勿回复。）";
		String content="<div style='font-family:Verdana; padding:2px;'>";
		content += "<div style='color:#666666;font-size:14px;'>";
		content += "<div style='margin-top:10px; margin-left:40px;'>尊敬的用户您好：</div>";
		content += "<div style='margin-top:25px; margin-left:40px;'>您的潜伴账号密码为：<font style='color:red;'>123456</font>。请妥善保管！</div>";
		content += "<div style='margin-top:25px; margin-left:40px;'>潜伴团队 敬上</div>";		
		content += "<div style='margin-top:5px; margin-left:40px;'><a href='javascript:void(0);' target='_blank' style='color: #006699;'>www.dive.com</a></div>";
		content += "</div></div>";
		return sendHtmlMessage(toUser, title, content);
	}
}
