package jb.controller;

import jb.interceptors.TokenManage;
import jb.pageModel.BirdEquip;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.BirdEquipServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 设备管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiEquipController")
public class ApiEquipController extends BaseController {
	@Autowired
	private BirdEquipServiceI birdEquipService;
	
	@Autowired
	private TokenManage tokenManage;
	
	/**
	 * 获取BirdEquip数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(BirdEquip birdEquip, PageHelper ph) {
		return birdEquipService.dataGrid(birdEquip, ph);
	}
	
}
