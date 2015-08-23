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
     *
     * @param user
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
            result.put("tokenId", tokenManage.buildToken(user.getId(), user.getName()));
            result.put("userId", user.getId());
            j.setObj(result);
            j.setSuccess(true);
            j.setMsg("登陆成功！");
        } else {
            j.fail();
            j.setMsg("用户名或密码错误！");
        }
        return j;
    }

    /**
     * 退出登录
     *
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

}
