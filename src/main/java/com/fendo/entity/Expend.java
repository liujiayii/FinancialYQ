package com.fendo.entity;

public class Expend {

	
		private Long id;
		private Long expend_id;
		private String img_url;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getExpend_id() {
			return expend_id;
		}
		public void setExpend_id(Long expend_id) {
			this.expend_id = expend_id;
		}
		public String getImg_url() {
			return img_url;
		}
		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}
		@Override
		public String toString() {
			return "Expend [id=" + id + ", expend_id=" + expend_id + ", img_url=" + img_url + "]";
		}
}
