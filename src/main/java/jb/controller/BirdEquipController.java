package jb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.absx.F;
import jb.net.mina.core.DeviceStanzaHandler;
import jb.pageModel.BirdEquip;
import jb.pageModel.Colum;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.BirdEquipServiceI;
import jb.util.Hex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * BirdEquip管理控制器
 *
 * @author John
 */
@Controller
@RequestMapping("/birdEquipController")
public class BirdEquipController extends BaseController {

    @Autowired
    private BirdEquipServiceI birdEquipService;


    /**
     * 跳转到BirdEquip管理页面
     *
     * @return
     */
    @RequestMapping("/manager")
    public String manager(HttpServletRequest request) {
        return "/birdequip/birdEquip";
    }

    /**
     * 获取BirdEquip数据表格
     *
     * @param birdEquip
     * @return
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public DataGrid dataGrid(BirdEquip birdEquip, PageHelper ph) {
        return birdEquipService.dataGrid(birdEquip, ph);
    }

    /**
     * 获取BirdEquip数据表格excel
     *
     * @param birdEquip
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @RequestMapping("/download")
    public void download(BirdEquip birdEquip, PageHelper ph, String downloadFields, HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {
        DataGrid dg = dataGrid(birdEquip, ph);
        downloadFields = downloadFields.replace("&quot;", "\"");
        downloadFields = downloadFields.substring(1, downloadFields.length() - 1);
        List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
        downloadTable(colums, dg, response);
    }

    /**
     * 跳转到添加BirdEquip页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request) {
        BirdEquip birdEquip = new BirdEquip();
        birdEquip.setId(UUID.randomUUID().toString());
        return "/birdequip/birdEquipAdd";
    }


    /**
     * 跳到发送指令页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/messagePage")
    public String messagePage(HttpServletRequest request, String id) {
        request.setAttribute("id", id);
        return "/birdequip/birdEquipSendMessage";
    }

    /**
     * 发送指令
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/sendMessage")
    @ResponseBody
    public Json sendMessage(HttpServletRequest request, String id, String command,Integer commandType,String voice) {
        Json j = new Json();
        String[] ids = id.split(",");
        if(1==commandType){
        	String gbkVoic = "音量设置"+voice;
        	try {
				command = Hex.encodeHexStr(gbkVoic.getBytes("GBK"),false);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	for (String username : ids) {
	            DeviceStanzaHandler.sendMessage(username, command);
	        }
        	
        }else{
	        for (String username : ids) {
	            DeviceStanzaHandler.sendMessage(username, command);
	        }
        }
        return j;
    }

    /**
     * 跳到分组页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/groupPage")
    public String groupPage(HttpServletRequest request, String id) {
        request.setAttribute("id", id);
        return "/birdequip/birdEquipGroup";
    }

    /**
     * 分组
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/group")
    @ResponseBody
    public Json group(HttpServletRequest request, String id, String groupType) {
        Json j = new Json();
        String[] ids = id.split(",");
        for (String username : ids) {
            BirdEquip birdEquip = new BirdEquip();
            birdEquip.setId(username);
            birdEquip.setGroupType(groupType);
            birdEquipService.edit(birdEquip);
        }

        return j;
    }

    /**
     * 添加BirdEquip
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(BirdEquip birdEquip) {
        Json j = new Json();
        birdEquipService.add(birdEquip);
        j.setSuccess(true);
        j.setMsg("添加成功！");
        return j;
    }

    /**
     * 跳转到BirdEquip查看页面
     *
     * @return
     */
    @RequestMapping("/view")
    public String view(HttpServletRequest request, String id) {
        BirdEquip birdEquip = birdEquipService.get(id);
        request.setAttribute("birdEquip", birdEquip);
        return "/birdequip/birdEquipView";
    }

    /**
     * 跳转到BirdEquip修改页面
     *
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, String id) {
        BirdEquip birdEquip = birdEquipService.get(id);
        request.setAttribute("birdEquip", birdEquip);
        return "/birdequip/birdEquipEdit";
    }

    /**
     * 修改BirdEquip
     *
     * @param birdEquip
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(BirdEquip birdEquip) {
        Json j = new Json();
        birdEquipService.edit(birdEquip);
        j.setSuccess(true);
        j.setMsg("编辑成功！");
        return j;
    }

    /**
     * 删除BirdEquip
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(String id) {
        Json j = new Json();
        birdEquipService.delete(id);
        j.setMsg("删除成功！");
        j.setSuccess(true);
        return j;
    }

}
