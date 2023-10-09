package com.ts.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private String courseName;
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
	private double subAmount;
	private double paidAmount;
	private double dueAmount;
	private double taxes;
	private double totalAmount;

}
