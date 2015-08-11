package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.BirdEquipDaoI;
import jb.model.TbirdEquip;
import jb.pageModel.BirdEquip;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.BirdEquipServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class BirdEquipServiceImpl extends BaseServiceImpl<BirdEquip> implements BirdEquipServiceI {

	@Autowired
	private BirdEquipDaoI birdEquipDao;

	@Override
	public DataGrid dataGrid(BirdEquip birdEquip, PageHelper ph) {
		List<BirdEquip> ol = new ArrayList<BirdEquip>();
		String hql = " from TbirdEquip t ";
		DataGrid dg = dataGridQuery(hql, ph, birdEquip, birdEquipDao);
		@SuppressWarnings("unchecked")
		List<TbirdEquip> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TbirdEquip t : l) {
				BirdEquip o = new BirdEquip();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(BirdEquip birdEquip, Map<String, Object> params) {
		String whereHql = "";	
		if (birdEquip != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(birdEquip.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", birdEquip.getName());
			}		
			if (!F.empty(birdEquip.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", birdEquip.getStatus());
			}		
			if (!F.empty(birdEquip.getEquipType())) {
				whereHql += " and t.equipType = :equipType";
				params.put("equipType", birdEquip.getEquipType());
			}		
				
			if (!F.empty(birdEquip.getLocation())) {
				whereHql += " and t.location = :location";
				params.put("location", birdEquip.getLocation());
			}		
			if (!F.empty(birdEquip.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", birdEquip.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(BirdEquip birdEquip) {
		TbirdEquip t = new TbirdEquip();
		BeanUtils.copyProperties(birdEquip, t);
		if(F.empty(t.getId()))
		t.setId(UUID.randomUUID().toString());
		birdEquipDao.save(t);
	}

	@Override
	public BirdEquip get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TbirdEquip t = birdEquipDao.get("from TbirdEquip t  where t.id = :id", params);
		BirdEquip o = new BirdEquip();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(BirdEquip birdEquip) {
		TbirdEquip t = birdEquipDao.get(TbirdEquip.class, birdEquip.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(birdEquip, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		birdEquipDao.delete(birdEquipDao.get(TbirdEquip.class, id));
	}

}
