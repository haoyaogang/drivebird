package jb.service;

import jb.pageModel.BirdCommand;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface BirdCommandServiceI {

	/**
	 * 获取BirdCommand数据表格
	 * 
	 * @param birdCommand
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(BirdCommand birdCommand, PageHelper ph);

	/**
	 * 添加BirdCommand
	 * 
	 * @param birdCommand
	 */
	public void add(BirdCommand birdCommand);

	/**
	 * 获得BirdCommand对象
	 * 
	 * @param id
	 * @return
	 */
	public BirdCommand get(String id);

	/**
	 * 修改BirdCommand
	 * 
	 * @param birdCommand
	 */
	public void edit(BirdCommand birdCommand);

	/**
	 * 删除BirdCommand
	 * 
	 * @param id
	 */
	public void delete(String id);

}
