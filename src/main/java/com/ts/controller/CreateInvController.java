package com.ts.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.CreateInvModel;
import com.ts.service.CreateInvService;

@RestController
@CrossOrigin("*")
public class CreateInvController {

	@Autowired
	CreateInvService cinvservice;

	@PostMapping("/createInvoice")
	public ResponseEntity<String> createInvoice(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("mobile") String mobile, @RequestParam("address") String address,
			@RequestParam("courseName") String courseName,
			@RequestParam("paymentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date paymentDate,
			@RequestParam("status") String status,
			@RequestParam(value = "dueDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
			@RequestParam("subAmount") Double subAmount, @RequestParam("paidAmount") Double paidAmount) {

		String invoice = cinvservice.createInvoice(name, email, mobile, address, courseName, paymentDate, status, subAmount, paidAmount, dueDate);
	    return ResponseEntity.ok(invoice);
	}

//	@PostMapping("/createInvoice")
//	public CreateInvModel createInvoice(@RequestBody String name, String email, String mobile){
//		return 
//	}

	@GetMapping("/getAllInvoices")
	public List<CreateInvModel> getAllInvoices(CreateInvModel getAllInvoices) {
		return cinvservice.getAllInvoices(getAllInvoices);
	}

	@GetMapping("/getInvoiceByEmail")
	public String getUserByEmail(@RequestParam String email) {
		return cinvservice.getUserByEmail(email);
	}
}
