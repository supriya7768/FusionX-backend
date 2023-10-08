package com.ts.service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.model.CreateInvModel;
import com.ts.repository.CreateInvRepository;


@Service
public class CreateInvService {

	@Autowired
	CreateInvRepository cinvrepository;

	public String createInvoice(String name, String email, String mobile, String address, Date dueDate, String status,
			String courseName, Double dueAmount, Double totalAmount) {
		CreateInvModel cim = new CreateInvModel();
		cim.setName(name);
		cim.setEmail(email);
		cim.setMobile(mobile);
		cim.setAddress(address);
		cim.setInvoiceDate(new Date());
		cim.setDueDate(dueDate);
		cim.setStatus(status);
		cim.setCourseName(courseName);
		cim.setDueAmount(dueAmount);
		cim.setTotalAmount(totalAmount);

		if (name == null || name.isEmpty() || email == null || email.isEmpty() || mobile == null || mobile.isEmpty()
				|| address == null || address.isEmpty() || dueDate == null || status.isEmpty() || status == null
				|| courseName == null || courseName.isEmpty() || dueAmount == null || totalAmount == null) {
			return "Please Enter all details.";
		}

		String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
		Pattern emailRegex = Pattern.compile(emailPattern);
		Matcher emailMatcher = emailRegex.matcher(email);

		if (!emailMatcher.matches()) {
			return "Invalid email address.";
		}

		String mobilePattern = "^[789]\\d{9}$"; // First digit is 7, 8, or 9 and followed by 9 digits
		Pattern mobileRegex = Pattern.compile(mobilePattern);
		Matcher mobileMatcher = mobileRegex.matcher(mobile);

		if (!mobileMatcher.matches()) {
			return "Invalid mobile number.";
		}

		if (cinvrepository.findByEmail(email).isPresent()) {
			return "Invoice already created with Invoice number";
		} else {
			cinvrepository.save(cim);
			return "Invoice has been generated successfully for this Invoice Number.";
		}
	}

	public List<CreateInvModel> getAllInvoices(CreateInvModel getAllInvoices) {
		return cinvrepository.findAll();
	}

	public String getUserByEmail(String email) {
		if (cinvrepository.findByEmail(email).isPresent()) {
			return "Email id is already present";
		} else
			return email;
	}

}
