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
	private Long id;
	private String leadName;
	private long mobile;
	private String email;
	private String qualification;
	private String collegeName;
	private int passingYear;
	private String courseName;
	private String interest;
	private String mode;
	private String approach;
	private boolean expierience;
	private String status;
	private boolean courseDone;
	private String remark;

}
