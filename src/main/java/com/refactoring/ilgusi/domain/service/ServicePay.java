package com.refactoring.ilgusi.domain.service;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ServicePay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pNo;
	@Column
	private int tNo;
	@Column
	private int pPrice;
	@Column
	private String pDate;

	@Override
	public String toString() {
		return "ServicePay{" +
				"pNo=" + pNo +
				", tNo=" + tNo +
				", pPrice=" + pPrice +
				", pDate='" + pDate + '\'' +
				'}';
	}
}
