package com.refactoring.ilgusi.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
	@Id
	@Column(name = "CATEGORY_CD")
	private Integer categoryCd;
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	@Column(name = "PARENT_CATEGORY_CD")
	private Integer ParentCategoryCd;
	@Column(name = "IMG_URL")
	private String imgUrl;

	@Override
	public String toString() {
		return "Category{" +
				"categoryCd=" + categoryCd +
				", categoryNm='" + categoryName + '\'' +
				", ParentCategoryCd=" + ParentCategoryCd +
				", imgUrl='" + imgUrl + '\'' +
				'}';
	}
}
