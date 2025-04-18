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
	private Integer cNo;
	@Column
	private String cName;
	@Column
	private Integer cDivisionNo;
}
