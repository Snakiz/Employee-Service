package com.employeeDetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeDetails.DTO.EmployeeDTO;
import com.employeeDetails.DTO.EmployeeTrainingDTO;
import com.employeeDetails.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
 
	 @Autowired
	    private EmployeeService employeeService;

	    @PostMapping("/register")
	    public EmployeeDTO registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
	        return employeeService.registerEmployee(employeeDTO);
	    }
	
	    // Controller method to fetch employee details by ID
	    @GetMapping("/{employeeId}")
	    public ResponseEntity<?> getEmployeeById(@PathVariable Long employeeId) {
	        return employeeService.getEmployeeByEmployeeId(employeeId);
	    }
	    
	 // Controller method to add employee trainning details by ID
	    @PostMapping("/addTrainingDetails")
	    public ResponseEntity<?> addEmployeeTrainingDetails(@RequestBody EmployeeTrainingDTO employeeId) {
	        return employeeService.addEmployeeTrainingDetails( employeeId);
	    }
	    
}
