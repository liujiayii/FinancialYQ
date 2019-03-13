package com.fendo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fendo.dao.BranchOfficeDao;
import com.fendo.dao.ExpendDao;
import com.fendo.dao.SpendDao;
import com.fendo.entity.Expend;
import com.fendo.entity.Spend;
import com.fendo.service.SpendService;
import com.fendo.utils.UploadFile;

/**
 * 
 * @author han
 *
 * @createDate 2018年8月2日-下午4:40:49
 */
@Service
public  class SpendServiceImpl implements SpendService {
	@Autowired
	private SpendDao spendDao;
	@Autowired
	private BranchOfficeDao branchofficeDao;
	@Autowired
	private ExpendDao expendDao;
	/* 查询所有支出列表 */
	@Override
	public List<Spend> findAllType() {
		List<Spend> spendList = spendDao.findAllType();
		if (spendList != null) {
			System.out.println("已查询到所有的支出列表" + spendList);
		}
		return spendList;
	}

	/* 添加支出信息 */
	@Override
	public Map<String,Object> toAddSpend(Spend spend) {
		Map<String,Object> map = new HashMap<String,Object>();
		int rows = spendDao.toAddSpend(spend);
		if (rows >= 1) {
			map.put("code",1);
			map.put("id",spend.getId());
			map.put("msg", "添加成功");
			return map;
		}else{
			map.put("code", -1);
			map.put("msg", "添加失败");
			return map;
		}
	}

	/**
	 * 通过id查找
	 */
	@Override
	public Spend findById(long id) {
		Spend spend = spendDao.findById(id);
		if (spend != null) {
			System.out.println("id查询到" + spend.toString());
		}
		return spend;
	}

	/**
	 * 通过功能
	 */
	@Override
	public int updatePasszc(Spend spend) {
		int rows = spendDao.updatePasszc(spend);
		if (rows >= 1) {
			System.out.println("通过成功！！");
		}
		return rows;
	}

	@Override
	public List<Spend> findAllTypebytime(String time) {

		return spendDao.findAllTypebytime(time);
	}

	@Override
	public List<Spend> findAllTypebyarea(String area) {

		return spendDao.findAllTypebyarea(area);
	}

	/* 修改支出费用 */
	@Override
	public int toUpdateSpend(Spend spend) {
		int rows = spendDao.toUpdateSpend(spend);
		System.out.println(rows);
		return spendDao.toUpdateSpend(spend);
	}

	@Override
	public Map<String,Object> toAddSpend(Long id , List<MultipartFile> files) {
		// 上传图片并将信息保存到数据库
		Map<String,Object> map = new HashMap<String,Object>();

			Map<String, String> update;
			String url = "";
			try {
				update = UploadFile.update(files.get(0));
				url = update.get("Path");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Expend expend = new Expend();
			expend.setImg_url(url);
			expend.setExpend_id(id);
			int rows = expendDao.insert(expend);
			if(rows >=1){
				map.put("code", 1);
				map.put("msg", "添加图片成功");
			}else{
				map.put("code", -1);
				map.put("msg", "添加图片失败");
		}
		return map;
	}

}
