package jb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jb.interceptors.TokenManage;
import jb.net.mina.core.DeviceStanzaHandler;
import jb.pageModel.BirdCommand;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.User;
import jb.service.BirdCommandServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 指令控制器
 * 
 * @author John
 * 
 */
/**
 * @author huangzhi
 *
 */
@Controller
@RequestMapping("/api/apiCommandController")
public class ApiCommandController extends BaseController {
	@Autowired
	private BirdCommandServiceI birdCommandService;
	
	@Autowired
	private TokenManage tokenManage;
	
	/**
	 * 获取BirdCommand数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(BirdCommand birdCommand, PageHelper ph) {
		return birdCommandService.dataGrid(birdCommand, ph);
	}
	
	
	/**
	 * 发送指令接口
	 * @param id
	 * @param command
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendCommand")
	public Json sendCommand(String id,String command) {
		Json j = new Json();
		try{
			DeviceStanzaHandler.sendMessage(id, command);
			j.success();
		}catch(Exception e){
			j.fail();
		}
		return j;
	}
	
}
