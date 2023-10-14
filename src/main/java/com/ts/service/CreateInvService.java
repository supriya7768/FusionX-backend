package com.ts.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.model.CreateInvModel;
import com.ts.repository.CreateInvRepository;

@Service
public class CreateInvService {

	@Autowired
	CreateInvRepository cinvrepository;

	@Temporal(TemporalType.DATE)
	private Date dueDate;

	public String createInvoice(String name, String email, String mobile, String address, String courseName,
			Date paymentDate, String status, Double subAmount, Double paidAmount, LocalDate dueDate) {
		CreateInvModel cim = new CreateInvModel();
		String[] nameWords = name.split(" ");
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

		cim.setName(formattedName.toString());
//		cim.setName(name);
		cim.setEmail(email);
		cim.setMobile(mobile);
		cim.setAddress(address);
		cim.setCourseName(courseName);
		cim.setInvoiceDate(new Date());
		cim.setPaymentDate(paymentDate);
		cim.setStatus(status);

		cim.setSubAmount(subAmount);

		double taxes = subAmount * (0.18);
		cim.setTaxes(taxes);

		cim.setPaidAmount(paidAmount);

		double total = subAmount + taxes;
		cim.setTotalAmount(total);

		if (total == paidAmount) {
			cim.setDueAmount(0);
			cim.setDueDate(null);
		} else {
			cim.setDueDate(dueDate);
			double due = total - paidAmount;
			cim.setDueAmount(due);
		}

		if (name == null || name.isEmpty() || email == null || email.isEmpty() || mobile == null || mobile.isEmpty()
				|| address == null || address.isEmpty() || courseName == null || courseName.isEmpty()
				|| status.isEmpty() || status == null || subAmount == null || paidAmount == null
				|| paymentDate == null) {
			return "Please Enter all details.";
		}

		String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
		Pattern emailRegex = Pattern.compile(emailPattern);
		Matcher emailMatcher = emailRegex.matcher(email);

		if (!emailMatcher.matches()) {
			return "Invalid email address.";
		}

		String mobilePattern = "^[6789]\\d{9}$";
		Pattern mobileRegex = Pattern.compile(mobilePattern);
		Matcher mobileMatcher = mobileRegex.matcher(mobile);

		if (!mobileMatcher.matches()) {
			return "Invalid mobile number.";
		}

		if (cinvrepository.findByEmail(email).isPresent()) {
			return "Invoice is already created with email id: " + email;
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
