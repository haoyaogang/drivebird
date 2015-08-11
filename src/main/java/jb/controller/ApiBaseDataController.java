package jb.controller;

import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.BasedataServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础数据
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiBaseDataController")
public class ApiBaseDataController extends BaseController {
	
	@Autowired
	private BasedataServiceI basedataService;
	
	
	
	/**
	 * 获取基础数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/basedata")
	@ResponseBody
	public Json basedata(PageHelper ph,String dataType) {
		Json j = new Json();
		try{
			j.setObj(basedataService.getBaseDatas(dataType));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
}
