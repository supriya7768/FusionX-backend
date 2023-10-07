package com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.model.LeadForm;
import com.ts.repository.LeadFormRepository;

@Service
public class LeadFormService {
	
	@Autowired
	LeadFormRepository lr;

	public LeadForm save(LeadForm leadForm) {
		return lr.save(leadForm);
	}

}
