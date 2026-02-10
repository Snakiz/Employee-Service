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
import com.employeeDetails.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO savedEmployee = employeeService.registerEmployee(employeeDTO);

        String response =
                "Employee added successfully..!!\n\n" +
                        "Details :\n" +
                        "EmployeeId: " + savedEmployee.getEmployeeId() + "\n" +
                        "EmployeeName: " + savedEmployee.getEmployeeName() + "\n" +
                        "EmployeeMailId: " + savedEmployee.getEmployeeMailId() + "\n" +
                        "EmployeeDesignation: " + savedEmployee.getEmployeeDesignation() + "\n" +
                        "EmployeeSalary: " + savedEmployee.getEmployeeSalary() + "\n" +
                        "ReportingManager: " + savedEmployee.getReportingManager() + "\n" +
                        "EmployeeTrainingDetails: " + savedEmployee.getEmployeeTrainingDetails();

        return ResponseEntity.ok(response);
    }


    // Controller method to fetch employee details by ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<String> getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeByEmployeeId(employeeId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    // Controller method to add employee trainning details by ID
    @PostMapping("/addTrainingDetails")
    public ResponseEntity<?> addEmployeeTrainingDetails(@RequestBody EmployeeTrainingDTO trainingDTO) {
        return employeeService.addEmployeeTrainingDetails(trainingDTO);
    }


}
