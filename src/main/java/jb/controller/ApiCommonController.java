package jb.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jb.pageModel.Bug;
import jb.pageModel.Json;
import jb.service.BugServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiCommon")
public class ApiCommonController extends BaseController {
	
	@Autowired
	private BugServiceI bugService;
	
	/**
	 * app错误日志上传
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload_errorlog")
	public Json uploadErrorlog(Bug bug, @RequestParam MultipartFile logFile, HttpServletRequest request) {
		Json j = new Json();
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar calendar = Calendar.getInstance();  
			String dirName = "errorlog/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
			bug.setFilePath(uploadFile(request, dirName, logFile, format.format(calendar.getTime())));
			bug.setTypeId("0"); // 错误
			bug.setId(UUID.randomUUID().toString());
			bugService.add(bug);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}
}
