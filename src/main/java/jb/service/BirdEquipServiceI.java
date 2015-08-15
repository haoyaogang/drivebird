package jb.service;

import jb.pageModel.BirdEquip;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface BirdEquipServiceI {

	/**
	 * 获取BirdEquip数据表格
	 * 
	 * @param birdEquip
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(BirdEquip birdEquip, PageHelper ph);

	/**
	 * 添加BirdEquip
	 * 
	 * @param birdEquip
	 */
	public void add(BirdEquip birdEquip);
	
	
	/**
	 * 退出
	 * @param birdEquip
	 * @return
	 */
	public boolean logout(BirdEquip birdEquip);
	
	/**
	 * 登录
	 * @param birdEquip
	 * @return
	 */
	public boolean login(BirdEquip birdEquip);

	/**
	 * 获得BirdEquip对象
	 * 
	 * @param id
	 * @return
	 */
	public BirdEquip get(String id);

	/**
	 * 修改BirdEquip
	 * 
	 * @param birdEquip
	 */
	public void edit(BirdEquip birdEquip);

	/**
	 * 删除BirdEquip
	 * 
	 * @param id
	 */
	public void delete(String id);

}
