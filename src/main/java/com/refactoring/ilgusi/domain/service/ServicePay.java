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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_pay_seq_gen")
	@SequenceGenerator(name = "service_pay_seq_gen", sequenceName = "SERVICE_PAY_SEQ", allocationSize = 1)
	private Integer payNo;
	@Column
	private int tradeNo;
	@Column
	private int payPrice;
	@Column
	private String payDate;

}
