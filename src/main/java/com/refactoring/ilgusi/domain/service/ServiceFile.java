package com.refactoring.ilgusi.domain.service;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ServiceFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileNo;
	@Column
	private int sNo;
	@Column
	private String filename;
	@Column
	private String filepath;

	@Override
	public String toString() {
		return "ServiceFile{" +
				"fileNo=" + fileNo +
				", sNo=" + sNo +
				", filename='" + filename + '\'' +
				", filepath='" + filepath + '\'' +
				'}';
	}
}
