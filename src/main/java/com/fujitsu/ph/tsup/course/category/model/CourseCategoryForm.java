/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.model;

/**
 * @author a.batongbaca
 * @version Creation: 0.01 Date: 2021-02-28
 *
 */
public class CourseCategoryForm {

	private Long id;
	private String category;
	private String detail;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
