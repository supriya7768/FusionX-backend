package com.ts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String leadName;
	private String email;
	private String mobileNo;
	private String address;
	private String degree;
	private String field;
	private String passingYear;
	private String collegeName;
	private String courseName;
	private String mode;
	private String experience;
	private String yearsOfExperience;
	private String designation;
	private String courseDoneFromOtherInstitute;
	private String instituteName;
	private String reasonForChanging;
	private String interest;
	private String approach;
	private String referenceName;
	private String batchCode;
	private String status;
	private String reasonPostpone;
}
