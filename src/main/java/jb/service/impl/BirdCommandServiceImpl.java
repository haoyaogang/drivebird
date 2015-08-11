package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.BirdCommandDaoI;
import jb.model.TbirdCommand;
import jb.pageModel.BirdCommand;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.BirdCommandServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class BirdCommandServiceImpl extends BaseServiceImpl<BirdCommand> implements BirdCommandServiceI {

	@Autowired
	private BirdCommandDaoI birdCommandDao;

	@Override
	public DataGrid dataGrid(BirdCommand birdCommand, PageHelper ph) {
		List<BirdCommand> ol = new ArrayList<BirdCommand>();
		String hql = " from TbirdCommand t ";
		DataGrid dg = dataGridQuery(hql, ph, birdCommand, birdCommandDao);
		@SuppressWarnings("unchecked")
		List<TbirdCommand> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TbirdCommand t : l) {
				BirdCommand o = new BirdCommand();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(BirdCommand birdCommand, Map<String, Object> params) {
		String whereHql = "";	
		if (birdCommand != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(birdCommand.getCommand())) {
				whereHql += " and t.command = :command";
				params.put("command", birdCommand.getCommand());
			}		
			if (!F.empty(birdCommand.getEquipType())) {
				whereHql += " and t.equipType = :equipType";
				params.put("equipType", birdCommand.getEquipType());
			}		
			if (!F.empty(birdCommand.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", birdCommand.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(BirdCommand birdCommand) {
		TbirdCommand t = new TbirdCommand();
		BeanUtils.copyProperties(birdCommand, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		birdCommandDao.save(t);
	}

	@Override
	public BirdCommand get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TbirdCommand t = birdCommandDao.get("from TbirdCommand t  where t.id = :id", params);
		BirdCommand o = new BirdCommand();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(BirdCommand birdCommand) {
		TbirdCommand t = birdCommandDao.get(TbirdCommand.class, birdCommand.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(birdCommand, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		birdCommandDao.delete(birdCommandDao.get(TbirdCommand.class, id));
	}

}
