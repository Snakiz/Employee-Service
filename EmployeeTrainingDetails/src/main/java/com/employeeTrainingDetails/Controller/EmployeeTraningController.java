package com.employeeTrainingDetails.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeTrainingDetails.Service.EmployeeTrainingService;

@RestController
@RequestMapping("/EmployeeTraining")
public class EmployeeTraningController {
    
	@Autowired
	private EmployeeTrainingService employeeTrainingService;
	
	@GetMapping("/getByEmployeeId/{employeeId}")
	private ResponseEntity<?> getTrainingDetailsByEmployeeId(@PathVariable long employeeId)

	{
		return employeeTrainingService.getCoursesByEmployeeId(employeeId);
	}

}
