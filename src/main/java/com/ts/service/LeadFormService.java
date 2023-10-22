package com.ts.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.model.LeadForm;
import com.ts.repository.LeadFormRepository;

@Service
public class LeadFormService {

	@Autowired
	LeadFormRepository lr;

	public String addLead(String leadName, String email, String mobileNo, String address, String degree, String field,
			String collegeName, String passingYear, String courseName, String mode, String experience,
			String yearsOfExperience, String designation, String courseDoneFromOtherInstitute, String instituteName,
			String reasonForChanging, String interest, String approach, String referenceName, String batchCode,
			String status, String reasonPostpone) {

		LeadForm lf = new LeadForm();

		if (leadName == null || leadName.isEmpty() || email == null || email.isEmpty() || mobileNo == null
				|| mobileNo.isEmpty() || address == null || address.isEmpty() || degree == null || degree.isEmpty()
				|| field.isEmpty() || mode.isEmpty() || experience.isEmpty() || courseDoneFromOtherInstitute.isEmpty()
				|| interest == null || approach.isEmpty() || status.isEmpty()) {
			return "Please Enter all details.";
		}

		String[] nameWords = leadName.split(" ");
		StringBuilder formattedName = new StringBuilder();

		for (String word : nameWords) {
			if (!word.isEmpty()) {
				if (formattedName.length() > 0) {
					formattedName.append(" ");
				}
				formattedName.append(word.substring(0, 1).toUpperCase());
				formattedName.append(word.substring(1).toLowerCase());
			}
		}

		lf.setLeadName(formattedName.toString());

		lf.setEmail(email);
		String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
		Pattern emailRegex = Pattern.compile(emailPattern);
		Matcher emailMatcher = emailRegex.matcher(email);

		if (!emailMatcher.matches()) {
			return "Invalid email address.";
		}

		lf.setMobileNo(mobileNo);
		String mobilePattern = "^[6789]\\d{9}$";
		Pattern mobileRegex = Pattern.compile(mobilePattern);
		Matcher mobileMatcher = mobileRegex.matcher(mobileNo);

		if (!mobileMatcher.matches()) {
			return "Invalid mobile number.";
		}

		lf.setAddress(address);
		lf.setDegree(degree);
		lf.setField(field);
		lf.setCollegeName(collegeName);
		lf.setPassingYear(passingYear);
		lf.setCourseName(courseName);
		lf.setMode(mode);
		lf.setExperience(experience);

		if ("IT".equalsIgnoreCase(lf.getExperience())) {
			lf.setYearsOfExperience(yearsOfExperience);
			lf.setDesignation(designation);
		} else {
			lf.setYearsOfExperience(null);
			lf.setDesignation(null);
		}

		lf.setCourseDoneFromOtherInstitute(courseDoneFromOtherInstitute);

		if ("Yes".equalsIgnoreCase(lf.getCourseDoneFromOtherInstitute())) {
			lf.setInstituteName(instituteName);
			lf.setReasonForChanging(reasonForChanging);
		} else {
			lf.setInstituteName(null);
			lf.setReasonForChanging(null);
		}

		lf.setInterest(interest);
		lf.setApproach(approach);

		if ("Reference".equalsIgnoreCase(lf.getApproach())) {
			lf.setReferenceName(referenceName);
			lf.setBatchCode(batchCode);
		} else {
			lf.setReferenceName(null);
			lf.setBatchCode(null);
		}

		lf.setStatus(status);

		if ("PostPone".equalsIgnoreCase(lf.getStatus())) {
			lf.setReasonPostpone(reasonPostpone);
		} else {
			lf.setReasonPostpone(null);
		}

		if ("Cancel".equalsIgnoreCase(lf.getStatus())) {
			lf.setReasonPostpone(reasonPostpone);
		} else {
			lf.setReasonPostpone(null);
		}

		if (lr.findByEmail(email).isPresent()) {
			return "Lead is already registered with email id: " + email;
		} else {
			lr.save(lf);
			return "Lead has been added successfully";
		}

	}

	public String getLeadByEmail(String email) {
		if (lr.findByEmail(email).isPresent()) {
			return "Email id is already present";
		} else
			return email;
	}

	public List<LeadForm> getAllLeads(LeadForm getAllLeads) {
		return lr.findAll();
	}

	public List<LeadForm> searchLeads(String query) {
		return lr.searchByCriteria(query);
	}

	public String updateLead(String leadName, String email, String mobileNo, String address, String degree,
			String field, String collegeName, String passingYear, String courseName, String mode, String experience,
			String yearsOfExperience, String designation, String courseDoneFromOtherInstitute, String instituteName,
			String reasonForChanging, String interest, String approach, String referenceName, String batchCode,
			String status, String reasonPostpone) {
		LeadForm lf = lr.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("Lead not found with email: " + email));
		if (leadName == null || leadName.isEmpty() || email == null || email.isEmpty() || mobileNo == null
				|| mobileNo.isEmpty() || address == null || address.isEmpty() || degree == null || degree.isEmpty()
				|| field.isEmpty() || mode.isEmpty() || experience.isEmpty() || courseDoneFromOtherInstitute.isEmpty()
				|| interest == null || approach.isEmpty() || status.isEmpty()) {
			return "Please Enter all details.";
		}

		String[] nameWords = leadName.split(" ");
		StringBuilder formattedName = new StringBuilder();

		for (String word : nameWords) {
			if (!word.isEmpty()) {
				if (formattedName.length() > 0) {
					formattedName.append(" ");
				}
				formattedName.append(word.substring(0, 1).toUpperCase());
				formattedName.append(word.substring(1).toLowerCase());
			}
		}

		lf.setLeadName(formattedName.toString());

		lf.setMobileNo(mobileNo);
		String mobilePattern = "^[6789]\\d{9}$";
		Pattern mobileRegex = Pattern.compile(mobilePattern);
		Matcher mobileMatcher = mobileRegex.matcher(mobileNo);

		if (!mobileMatcher.matches()) {
			return "Invalid mobile number.";
		}

		lf.setAddress(address);
		lf.setDegree(degree);
		lf.setField(field);
		lf.setCollegeName(collegeName);
		lf.setPassingYear(passingYear);
		lf.setCourseName(courseName);
		lf.setMode(mode);
		lf.setExperience(experience);

		if ("IT".equalsIgnoreCase(lf.getExperience())) {
			lf.setYearsOfExperience(yearsOfExperience);
			lf.setDesignation(designation);
		} else {
			lf.setYearsOfExperience(null);
			lf.setDesignation(null);
		}

		lf.setCourseDoneFromOtherInstitute(courseDoneFromOtherInstitute);

		if ("Yes".equalsIgnoreCase(lf.getCourseDoneFromOtherInstitute())) {
			lf.setInstituteName(instituteName);
			lf.setReasonForChanging(reasonForChanging);
		} else {
			lf.setInstituteName(null);
			lf.setReasonForChanging(null);
		}

		lf.setInterest(interest);
		lf.setApproach(approach);

		if ("Reference".equalsIgnoreCase(lf.getApproach())) {
			lf.setReferenceName(referenceName);
			lf.setBatchCode(batchCode);
		} else {
			lf.setReferenceName(null);
			lf.setBatchCode(null);
		}

		lf.setStatus(status);

		if ("PostPone".equalsIgnoreCase(lf.getStatus())) {
			lf.setReasonPostpone(reasonPostpone);
		} else {
			lf.setReasonPostpone(null);
		}

		if ("Cancel".equalsIgnoreCase(lf.getStatus())) {
			lf.setReasonPostpone(reasonPostpone);
		} else {
			lf.setReasonPostpone(null);
		}

		lr.save(lf);
		return "Lead has been updated successfully";

	}

}
