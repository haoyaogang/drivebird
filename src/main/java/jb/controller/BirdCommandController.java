package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.BirdCommand;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.BirdCommandServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * BirdCommand管理控制器
 *
 * @author John
 */
@Controller
@RequestMapping("/birdCommandController")
public class BirdCommandController extends BaseController {

    @Autowired
    private BirdCommandServiceI birdCommandService;


    /**
     * 跳转到BirdCommand管理页面
     *
     * @return
     */
    @RequestMapping("/manager")
    public String manager(HttpServletRequest request) {
        return "/birdcommand/birdCommand";
    }

    /**
     * 获取BirdCommand数据表格
     *
     * @return
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public DataGrid dataGrid(BirdCommand birdCommand, PageHelper ph) {
        return birdCommandService.dataGrid(birdCommand, ph);
    }

    /**
     * 获取BirdCommand数据表格excel
     *
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @RequestMapping("/download")
    public void download(BirdCommand birdCommand, PageHelper ph, String downloadFields, HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {
        DataGrid dg = dataGrid(birdCommand, ph);
        downloadFields = downloadFields.replace("&quot;", "\"");
        downloadFields = downloadFields.substring(1, downloadFields.length() - 1);
        List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
        downloadTable(colums, dg, response);
    }

    /**
     * 跳转到添加BirdCommand页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request) {
        BirdCommand birdCommand = new BirdCommand();
        birdCommand.setId(UUID.randomUUID().toString());
        return "/birdcommand/birdCommandAdd";
    }

    /**
     * 添加BirdCommand
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(BirdCommand birdCommand) {
        Json j = new Json();
        birdCommandService.add(birdCommand);
        j.setSuccess(true);
        j.setMsg("添加成功！");
        return j;
    }

    /**
     * 跳转到BirdCommand查看页面
     *
     * @return
     */
    @RequestMapping("/view")
    public String view(HttpServletRequest request, String id) {
        BirdCommand birdCommand = birdCommandService.get(id);
        request.setAttribute("birdCommand", birdCommand);
        return "/birdcommand/birdCommandView";
    }

    /**
     * 跳转到BirdCommand修改页面
     *
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, String id) {
        BirdCommand birdCommand = birdCommandService.get(id);
        request.setAttribute("birdCommand", birdCommand);
        return "/birdcommand/birdCommandEdit";
    }

    /**
     * 修改BirdCommand
     *
     * @param birdCommand
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(BirdCommand birdCommand) {
        Json j = new Json();
        birdCommandService.edit(birdCommand);
        j.setSuccess(true);
        j.setMsg("编辑成功！");
        return j;
    }

    /**
     * 删除BirdCommand
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(String id) {
        Json j = new Json();
        birdCommandService.delete(id);
        j.setMsg("删除成功！");
        j.setSuccess(true);
        return j;
    }

}
