package jb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jb.absx.F;
import jb.interceptors.TokenManage;
import jb.pageModel.Json;
import jb.pageModel.SessionInfo;
import jb.pageModel.User;
import jb.service.UserServiceI;
import jb.util.Constants;
import jb.util.DateUtil;
import jb.util.EmailSendUtil;
import jb.util.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账户管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiAccountController")
public class ApiAccountController extends BaseController {
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private TokenManage tokenManage;
	
	
	/**
	 * 用户登录
	 * @param account
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Json login(User user, HttpSession session, HttpServletRequest request) {
		Json j = new Json();
		user = userService.login(user);
		if (user != null) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tokenId", tokenManage.buildToken(user.getId(),user.getName()));
			result.put("userId", user.getId());
			j.setObj(result);
			j.setSuccess(true);
			j.setMsg("登陆成功！");
		} else {
			j.setMsg("用户名或密码错误！");
		}
		return j;
	}
	
	/**
	 * 退出登录
	 * @param account
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public Json logout(HttpServletRequest request) {
		Json j = new Json();
		String tokenId = request.getParameter(TokenManage.TOKEN_FIELD);	
		tokenManage.removeToken(tokenId);
		j.setSuccess(true);
		j.setMsg("退出登录成功！");
		return j;
	}
	
	/**
	 * 用户注册
	 * @param account
	 * @param headImageFile
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/register")
	public Json register(DiveAccount account, @RequestParam(required=false) MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();
		try {
			account.setIcon(uploadFile(request, account.getUserName(), iconFile));
			account = accountService.reg(account);	
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tokenId", tokenManage.buildToken(account.getId(),account.getUserName()));
			result.put("accountId", account.getId());
			j.setObj(result);
			j.setSuccess(true);
			j.setMsg("注册成功");
			
			final DiveAccount hx = account;
			TokenListener.executors.execute(new Runnable() {
				public void run() {
					// 注册环信
					if(!F.empty(HuanxinUtil.createUser(hx.getId(), hx.getHxPassword()))) {
						DiveAccount a = new DiveAccount();
						a.setId(hx.getId());
						a.setHxStatus("1");
						accountService.edit(a);
						
						if(!F.empty(hx.getRecommend())) {
							HuanxinUtil.addFriend(hx.getId(), hx.getRecommend());
						}
					}
				}
			});
			
			
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 发送密码邮件
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/sendPassword")
	public Json sendPassword(DiveAccount account) {
		Json j = new Json();
		try {
			account = accountService.get(account);
			if(account != null) {
				EmailSendUtil.sendPassword(account.getEmail(), account.getHxPassword());
				j.setMsg("邮件发送成功");
				j.success();
			} else {
				j.setMsg("邮箱不存在！");
				j.fail();
			}
		} catch (Exception e) {
			j.setMsg("邮件发送失败");
		}
		return j;
	}
	
	*//**
	 * 修改密码
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/updatePass")
	public Json updatePass(DiveAccount account, String oldPass, HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			if(!F.empty(HuanxinUtil.resetPass(s.getId(), account.getPassword()))) {
				account.setId(s.getId());
				DiveAccount a = accountService.get(s.getId());
				oldPass = MD5Util.md5(oldPass);
				if(!oldPass.equals(a.getPassword())) {
					j.setMsg("旧密码不正确！");
				} else {
					account.setHxPassword(account.getPassword());
					account.setPassword(MD5Util.md5(account.getPassword()));
					accountService.edit(account);
					j.setSuccess(true);
					j.setMsg("密码修改成功");
				}
			} else {
				j.setMsg("密码修改失败");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 个人主页
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/personHome")
	public Json personHome(HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			DiveAccount account = accountService.personHome(s.getId());
			j.setSuccess(true);
			j.setObj(account);
			j.setMsg("个人主页查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 个人信息
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/personInfo")
	public Json personInfo(String accountId, HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			j.setObj(accountService.get(F.empty(accountId) ? s.getId():accountId));
			j.setSuccess(true);
			j.setMsg("个人信息查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 个人信息修改
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/updatePersonInfo")
	public Json updatePersonInfo(DiveAccount account, String birthdayStr, HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			account.setId(s.getId());
			if(!accountService.emailExists(account)) {
				if(!F.empty(birthdayStr)) {
					account.setBirthday(DateUtil.parse(birthdayStr, Constants.DATE_FORMAT_YMD));
				}
				accountService.edit(account);			
				j.setSuccess(true);
				j.setMsg("个人信息修改成功");
			} else {
				j.setMsg("邮箱已被使用！");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 头像上传
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/headImgUpload")
	public Json headImgUpload(DiveAccount account, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			String icon = uploadFile(request, s.getName(), iconFile);
			account.setIcon(icon);
			account.setId(s.getId());
			accountService.edit(account);
			j.setSuccess(true);
			j.setMsg("头像上传成功");
			j.setObj(account.getIcon());
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 潜水认证信息查询
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/certificateInfo")
	public Json certificateInfo(HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			j.setObj(certificateAuthorityService.getInfoByAccountId(s.getId()));
			j.setSuccess(true);
			j.setMsg("潜水认证信息查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 潜水认证信息新增/修改
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/updateCertificateInfo")
	public Json updateCertificateInfo(DiveCertificateAuthority ca, String authDateStr, 
			@RequestParam(required=false) MultipartFile rightSideFile, @RequestParam(required=false) MultipartFile reverseSideFile, 
			HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			ca.setRightSide(uploadFile(request, s.getName(), rightSideFile));
			ca.setReverseSide(uploadFile(request, s.getName(), reverseSideFile));
			ca.setAuthDate(DateUtil.parse(authDateStr, Constants.DATE_FORMAT_YMD));
			ca.setAccountId(s.getId());
			ca.setStatus("AS02"); // 审核中
			int r = certificateAuthorityService.saveOrUpdate(ca);
			if(r == -1) {
				j.setMsg("潜水认证信息修改失败");
			} else {
				j.setObj(ca);
				j.setSuccess(true);
				j.setMsg("潜水认证信息修改成功");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	*//**
	 * 收藏主页
	 * @param lvAccount
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/collectHome")
	public Json collectHome(HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			j.setObj(collectService.countCollectNum(s.getId()));
			j.setSuccess(true);
			j.setMsg("收藏数量查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	private SessionInfo getSessionInfo(HttpServletRequest request){
		SessionInfo s = tokenManage.getSessionInfo(request);
		return s;		
	}*/
	
}
