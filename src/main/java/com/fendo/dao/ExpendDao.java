package com.fendo.dao;
import java.util.List;

import com.fendo.entity.Expend;

public interface ExpendDao {
	
	
	/**保存图片路径*/
	int insert(Expend expend);
	/**查询照片路径**/
	List<String> selectFileUrlByExpendId(long expendId);
}
