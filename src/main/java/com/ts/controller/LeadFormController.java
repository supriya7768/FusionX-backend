package com.ts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.service.LeadFormService;

@CrossOrigin("*")
@RestController
public class LeadFormController {

	@Autowired
	LeadFormService ls;

	@PostMapping("/add")
	public ResponseEntity<String> addLead(@RequestParam("leadName") String leadName,
			@RequestParam("email") String email, @RequestParam("mobileNo") String mobileNo,
			@RequestParam("address") String address, @RequestParam("degree") String degree,
			@RequestParam("field") String field, @RequestParam("collegeName") String collegeName,
			@RequestParam("passingYear") String passingYear, @RequestParam("courseName") String courseName,
			@RequestParam("mode") String mode, @RequestParam("experience") String experience,
			@RequestParam("yearsOfExperience") String yearsOfExperience,
			@RequestParam("designation") String designation,
			@RequestParam("courseDoneFromOtherInstitute") String courseDoneFromOtherInstitute,
			@RequestParam("instituteName") String instituteName,
			@RequestParam("reasonForChanging") String reasonForChanging, @RequestParam("interest") String interest,
			@RequestParam("approach") String approach, @RequestParam("referenceName") String referenceName,
			@RequestParam("batchCode") String batchCode, @RequestParam("status") String status,
			@RequestParam("reasonPostpone") String reasonPostpone) {
		String lead = ls.addLead(leadName, email, mobileNo, address, degree, field, collegeName, passingYear,
				courseName, mode, experience, yearsOfExperience, designation, courseDoneFromOtherInstitute,
				instituteName, reasonForChanging, interest, approach, referenceName, batchCode, status, reasonPostpone);
		return ResponseEntity.ok(lead);
	}

	@GetMapping("/getLeadByEmail")
	public ResponseEntity<String> getLeadByEmail(@RequestParam String email) {
		if (email != null && !email.isEmpty()) {
			String response = ls.getLeadByEmail(email);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body("Email parameter is missing or empty.");
		}
	}

}
