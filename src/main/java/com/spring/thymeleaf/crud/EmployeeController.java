package com.spring.thymeleaf.crud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
	
	public Map<String, Employee> employees = new HashMap<>();
	
	Employee employee1 = new Employee("E101", "Ian", "Almeida", 35);	
	Employee employee2 = new Employee("E102", "Iane", "Maza", 32);
	
	public EmployeeController() {
		employees.put("E101", employee1);
		employees.put("E102", employee2);
	}

	@GetMapping("/employee")
	public String getAllEmployees(Model model) {
		//adiciono ao modelo do spring, os atributos do modelo que são representados por employees e os valores
		//retornados por employees.values();
		model.addAttribute("employees", employees.values());
		return "employee-list";
	}
	
	@GetMapping("/employee/add")
	public String getEmployeeForm() {
		
		return "employee-form";
		}
	
	@PostMapping("/employee/add")
	public String addEmployee(@ModelAttribute("addEmployee") Employee employee, Model model) {
		employees.put(employee.getId(), employee);
		model.addAttribute("employees", employees.values());
		return "employee-list";
	}
	
	@GetMapping("/employee/edit/{id}")
	public String editEmployee(@PathVariable("id") String empId, Model model) {
		model.addAttribute("editEmployee", employees.get(empId));
		
		return "employee-edit-form";
	}
	
	@PostMapping("/employee/edit/{id}")
	public String updateEmployee(@PathVariable("id")String empId, @ModelAttribute("editEmployee") 
	Employee employee, Model model) {
		
		employees.put(empId, employee);
		model.addAttribute("employees", employees.values());
		return "employee-list";
	}
	
	@GetMapping("/employee/delete/{id}")
	public String deleteEmployee(@PathVariable("id") String empId, Model model) {
		employees.remove(empId);
		model.addAttribute("employees", employees.values());
		return "employee-list";
	}
}
