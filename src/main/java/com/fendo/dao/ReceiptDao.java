package com.fendo.dao;

import java.util.List;

import com.fendo.entity.Receipt;
/**
 * 
 *
 * @ClassName: ReceiptDao

 * @description 收入票据图片dao
 *
 * @author lishaozhang
 * @createDate 2019年3月8日
 */
public interface ReceiptDao {
    
	/**保存图片路径*/
	int insert(Receipt receipt);
    /**查询照片路径**/
	List<String> selectFile_urlByIncomeId(long id);
	List<String> selectFileUrlByIncomeId(long id);

}
