package com.fendo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fendo.dao.IncomeDao;
import com.fendo.dao.ReceiptDao;
import com.fendo.entity.Expend;
import com.fendo.entity.Income;
import com.fendo.entity.Receipt;
import com.fendo.service.IncomeService;
import com.fendo.utils.UploadFile;

/**
 * 
 * @author han
 *
 * @createDate 2018年8月2日-下午4:40:49
 */
@Service
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	private IncomeDao incomeDao;
	
	@Autowired
	private ReceiptDao receiptDao;

	/* 查询所有收入列表 */
	@Override
	public List<Income> findAllType() {
		List<Income> incomeList = incomeDao.findAllType();
		if (incomeList != null) {
			System.out.println("已查询到所有的收入列表" + incomeList);
		}
		return incomeList;
	}

	/* 添加收入信息 */
	@Override
    @Transactional
	public Map<String,Object> toAddIncome(Income income) {
		Map<String,Object> map = new HashMap<String,Object>();
		int rows = incomeDao.toAddIncome(income);
		if (rows >= 1) {
			map.put("code",1);
			map.put("id",income.getId());
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
	public Income findById1(long id) {
		Income income = incomeDao.findById1(id);
		if (income != null) {
			System.out.println("通过id查询到" + income.toString());
		}
		return income;
	}

	/**
	 * 通过功能
	 */
	@Override
	public int updatePasssr(Income income) {
		int rows = incomeDao.updatePasssr(income);
		if (rows >= 1) {
			System.out.println("通过成功");
		}
		return rows;
	}

	
	@Override
	public void deletePasssr(long id) {
	
		incomeDao.deletePasssr(id);
	}

	
	@Override
	public List<Income> findAllTypebytime(String time) {
		
		return incomeDao.findAllTypebytime(time);
	}

	@Override
	public List<Income> findAllTypebyarea(String area) {
		
		return incomeDao.findAllTypebyarea(area);
	}
	/*修改收入费用*/
	@Override
	public int toUpdateIncome(Income income) {
		int rows = incomeDao.toUpdateIncome(income);
		System.out.println(rows);
		return incomeDao.toUpdateIncome(income);
	}

	/*通过条件查询*/
	@Override
	public List<Income> findBySelective(Income income) {
		
		return incomeDao.findBySelective(income);
	}

	@Override
	public Map<String, Object> toAddSpend(Long id, List<MultipartFile> files) {
		// // 上传图片并将信息保存到数据库
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

		Receipt receipt = new Receipt();
		receipt.setFile_url(url);
		receipt.setIncome_id(id);
		int rows = receiptDao.insert(receipt);
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
