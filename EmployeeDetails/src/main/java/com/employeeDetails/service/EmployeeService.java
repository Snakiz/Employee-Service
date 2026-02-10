package com.employeeDetails.service;

import org.springframework.http.ResponseEntity;

import com.employeeDetails.DTO.EmployeeDTO;
import com.employeeDetails.DTO.EmployeeTrainingDTO;

public interface EmployeeService {

    EmployeeDTO registerEmployee(EmployeeDTO employeeDTO);

    ResponseEntity<String> getEmployeeByEmployeeId(Long employeeId);

    ResponseEntity<String> getAllEmployees();

    ResponseEntity<?> addEmployeeTrainingDetails(EmployeeTrainingDTO trainingDTO);
}
