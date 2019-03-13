package com.fendo.entity;
/**
 * 
 *
 * @ClassName: Receipt

 * @description 图片收据表
 *
 * @author lishaozhang
 * @createDate 2019年3月8日
 */
public class Receipt {
	/**主键id*/
	private Long id;
	/**收入表id（伪外键）*/
	private Long income_id;
	/**图片路径*/
	private String file_url;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIncome_id() {
		return income_id;
	}
	public void setIncome_id(Long income_id) {
		this.income_id = income_id;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", income_id=" + income_id + ", file_url=" + file_url + "]";
	}
	
	

}
