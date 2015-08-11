package jb.controller;

import jb.interceptors.TokenManage;
import jb.pageModel.BirdCommand;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
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
	
}
