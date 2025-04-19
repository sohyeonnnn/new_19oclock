package com.refactoring.ilgusi.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
	@Id
	@Column(name = "C_NO")
	private Integer cNo;
	@Column(name = "C_NAME")
	private String cName;
	@Column(name = "C_DIVISION_NO")
	private Integer cDivisionNo;
	@Column(name = "IMG_URL")
	private String imgUrl;

	@Override
	public String toString() {
		return "Category{" +
				"cNo=" + cNo +
				", cName='" + cName + '\'' +
				", cDivisionNo=" + cDivisionNo +
				", imgUrl='" + imgUrl + '\'' +
				'}';
	}
}
