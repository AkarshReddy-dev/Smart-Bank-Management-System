package com.controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
	
	
import com.model.Admin;
import com.model.Customer;
import com.model.Loan;
import com.model.Transaction;
import com.service.AdminService;
import com.service.CustomerService;
import com.service.LoanService;
import com.service.TransactionService;

import jakarta.servlet.http.HttpSession;
	
	@Controller
	public class AdminController {
		
		@Autowired
		private AdminService adminService;
		
		@Autowired
		private LoanService loanService;
		
		@Autowired
		private CustomerService customerService;
		
		@Autowired
		private TransactionService transactionService;
		
		@GetMapping("/admin/login")
		public String adminLogin() {
			
			return "adminlogin";
		}
		
		@PostMapping("/admin/login")
		public String adminDashBoard(Admin admin,Model model,HttpSession session) {
			
			Admin validAdmin = adminService.loginValidation(admin);
			
			if(validAdmin==null) {
				
				model.addAttribute("msg","invalid admin CREDENTIALS");				
				return "adminlogin";
				
			}
			else {
				
				session.setAttribute("admin", validAdmin);		
				return "admindashboard"; 
				
			}
					
			
		}
		
		@GetMapping("/admin/dashboard")
		public String adminDashBoard(Model  model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			
			return "admindashboard";
			
			}
		}
		
		@GetMapping("/admin/createCustomer")
		public String createCustomer(Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			
			return "createCustomer";
			
			}
		}
		
		
		@PostMapping("/admin/createCustomer")
		public String createCustomer2(Customer customer,Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			adminService.createCustomer(customer);
			
			model.addAttribute("msg", "Succces!!! Account Created Succssfully");
			model.addAttribute("accountNumber",customer.getAccountNumber());
			model.addAttribute("TPassword",customer.getPassword());
			
			
			return "createCustomer";
			
			}
			
		
		}
		
		@GetMapping("/admin/manageLoans")
		public String manageLoan(Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			
			List<Loan> allLoans = loanService.getAllPendingLoans();
			model.addAttribute("loans",allLoans);
			return "manageLoans";
			
			}
			
		}
		
		@PostMapping("/admin/updateLoan")
		public String manageLoan(@RequestParam("loanId") int id,@RequestParam("status") String status,Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			
			loanService.updateStatus(id,status);
			
			List<Loan> allLoans = loanService.getAllPendingLoans();
			model.addAttribute("loans",allLoans);
			
			return  "manageLoans";
			
			}
		}
		
		@GetMapping("/admin/manageCustomer")
		public String manageCustomer(Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			
			List<Customer> allCustomers = customerService.getAllCustomers();
			
			model.addAttribute("customers",allCustomers);
			
			return "manageCustomer";
			
			}
			
		}
		
		@PostMapping("/admin/manageCustomer")
		public String manageCustomerStatus(@RequestParam("customerId") long id,@RequestParam("status") boolean status,Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
				
				Customer customer = customerService.getByCustomerId(id);
				
				customer.setStatus(status);
				
				customerService.updateStatus(customer);
				
				return "redirect:/admin/manageCustomer";
				
			}
			
			
		}
		
		@GetMapping("/admin/generateReports")
		public String transactionReports(Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			
			return "transactionReports";
			
			}
			
		}
		
		@PostMapping("/admin/generateReports")
		public String printTransactionReports(@RequestParam String month,@RequestParam int year,@RequestParam String reportType, Model model,HttpSession session) {
			
			if(session.getAttribute("admin")==null) {
				
				return "redirect:/admin/login";
				
			}
			else {
			
			if(reportType.equals("Monthly")) {
				
				List<Transaction> transactionsByMonth = transactionService.getTransactionsByMonth(month);
				model.addAttribute("transactions", transactionsByMonth);
				
			}
			
			if(reportType.equals("Annual")) {
				
				List<Transaction> transactionsByYear = transactionService.getTransactionsByYear(year);
				model.addAttribute("transactions", transactionsByYear);
				
			}
			
			return "transactionReports";
			
			}
			
		}
		
		@GetMapping("/admin/logout")
		private String logout(HttpSession session) {
			
			session.removeAttribute("admin");
			
			return "customerlogin";
			
		}
		
		
	
	}
