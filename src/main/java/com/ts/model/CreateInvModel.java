package com.ts.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreateInvModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;
	private String name;
	private String email;
	private String mobile;
	private String address;
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	private String status;
	private String courseName;
	private double dueAmount;
	private double totalAmount;

}
