package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Customer;
import com.model.Loan;
import com.model.Transaction;
import com.service.CustomerService;
import com.service.LoanService;
import com.service.MailService;
import com.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/customer/login")
	public String login()
	{
		return "customerlogin";
	}
	
	@PostMapping("/customer/login")
	public String customerLogin(Customer customer,Model model,HttpSession session)
	{
		
		Customer dbCustomer = customerService.loginValidation(customer);
		
		if(dbCustomer==null) {
			model.addAttribute("msg","invalid account number or password");
			return "customerlogin";
		}
		
		else if (!dbCustomer.isStatus()) {
			
			model.addAttribute("status","Ypur Account is Deactivated");
			return "customerlogin";
			
		}
		
		else if (dbCustomer.isFirstLogin()) {
			session.setAttribute("accountNumber", dbCustomer.getAccountNumber());
			return "resetPassword";
		}
		
		else {
			session.setAttribute("tempCustomer", dbCustomer);
			String otp=customerService.generateOTP();
			session.setAttribute("otp", otp);
			mailService.sendMail(dbCustomer.getEmail(),otp);
			return "otpverification";
		}
		
	}
	
	@GetMapping("/customer/resetpassword")
	public String resetPassword(HttpSession session) {
		
		if(session.getAttribute("accountNumber")==null) {

		    return "redirect:/customer/login";

		}
		
		return "resetPassword";
		
	}
	
	@PostMapping("/customer/resetpassword")
	public String resetPassword2(Customer customer,HttpSession session) {
		
		if(session.getAttribute("accountNumber")==null) {

		    return "redirect:/customer/login";

		}
		
		String accountNumber = (String)session.getAttribute("accountNumber");
		String password = customer.getPassword();
		customerService.updateCustomerAfterReset(accountNumber,password);
		session.invalidate();
		return "customerlogin";
		
	}
	
	@PostMapping("/customer/otp-verification")
	public String verifyOTP(@RequestParam("enteredOTP") String enteredOTP,Model model,HttpSession session) {
		
		if(session.getAttribute("tempCustomer")==null) {

		    return "redirect:/customer/login";

		}
			
		String realOTP = (String)session.getAttribute("otp");
		
		if(realOTP!=null && realOTP.equals(enteredOTP)) {
			
			Customer customer = (Customer) session.getAttribute("tempCustomer");
			
			session.setAttribute("customer", customer);
			session.removeAttribute("tempCustomer");
			session.removeAttribute("otp");
			
			return "customerdashboard";
		}
		else {
			model.addAttribute("msg","Invalid OTP");
			return "otpverification";
		}
		
	}
	
	@GetMapping("/customer/dashboard")
	public String dashboard(Model model,HttpSession session) {
		
		if(session.getAttribute("customer")==null) {

		    return "redirect:/customer/login";

		}
		
		Customer customer = (Customer)session.getAttribute("customer");
		String accountNumber = customer.getAccountNumber();
		Customer latestCustomer = customerService.getByAccountNumber(accountNumber);
		
		model.addAttribute("balance",latestCustomer.getBalance());
		model.addAttribute("name",customer.getName());
		model.addAttribute("accountNumber",customer.getAccountNumber());
		
		
		return "customerdashboard";
	}
	
	@GetMapping("/customer/applyLoan")
	public String applyLoan(HttpSession session) {
		
		if(session.getAttribute("customer")==null) {

		    return "redirect:/customer/login";

		}
		
		return "applyLoan";
		
	}
	
	
	@PostMapping("/customer/applyLoan")
	public String applyLoanPost(Loan loan,Model model,HttpSession session) {
		
		if(session.getAttribute("customer")==null) {

		    return "redirect:/customer/login";

		}
		
		model.addAttribute("msg","Loan Applies Successfully");
		Customer customer = (Customer)session.getAttribute("customer");
		loan.setCustomer(customer);
		loanService.applyLoan(loan);
		
		return "applyLoan";
		
	}
	
	@GetMapping("/customer/fundTransfer")
	public String fundTransfer(HttpSession session) {
		
		if(session.getAttribute("customer")==null) {

		    return "redirect:/customer/login";

		}
		
		return "fundTransfer";
		
	}
	
	@PostMapping("/customer/fundTransfer")
	public String fundTransferPost(@RequestParam("accountNumber") String accountNumber, @RequestParam("amount") long amount, Model model,HttpSession session) {
		
		if(session.getAttribute("customer")==null) {

		    return "redirect:/customer/login";

		}
		
		Customer customer = (Customer) session.getAttribute("customer");
		String updateFundTransfer = customerService.updateFundTransfer(customer, accountNumber, amount);
		
		model.addAttribute("msg",updateFundTransfer);
		
		if(updateFundTransfer.equals("Fund Transfer Success!!!")) {
			
			transactionService.storeTransaction(customer,accountNumber,amount);
			
		}
		
		
		
		return "fundTransfer";
		
	}
	
	@GetMapping("/customer/miniStatement")
	public String openMiniStatement(Model model,HttpSession session) {
		
		if(session.getAttribute("customer")==null) {

		    return "redirect:/customer/login";

		}
		
		Customer loggedCustomer = (Customer) session.getAttribute("customer");
		
		List<Transaction> allTransactions = transactionService.getAllTransactions(loggedCustomer);
		
		model.addAttribute("transactions",allTransactions);
		
		return "miniStatement";
	}
	
	@GetMapping("/customer/viewLoanStatus")
	public String viewLoanStatus(Model model,HttpSession session) {
		
		if(session.getAttribute("customer")==null) {

		    return "redirect:/customer/login";

		}
		
		Customer customer = (Customer) session.getAttribute("customer");
		
		List<Loan> allLoans = loanService.getAllLoansByCustomer(customer);
		
		model.addAttribute("loans",allLoans);
		
		return "loanStatus";
		
	}
	
	@GetMapping("/customer/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("customer");
		
		return "customerlogin";
		
	}

}
