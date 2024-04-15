package com.employeeDetails.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.employeeDetails.DTO.EmployeeDTO;
import com.employeeDetails.DTO.EmployeeTrainingDTO;
import com.employeeDetails.DTO.EmployeeTrainingDTOResponse;
import com.employeeDetails.Entity.Employee;
import com.employeeDetails.Entity.EmployeeTrainning;
import com.employeeDetails.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private final RestTemplate restTemplate = new RestTemplate();

	public EmployeeDTO registerEmployee(EmployeeDTO employeeDTO) {
		// Convert DTO to entity
		Employee employee = new Employee();
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setEmployeeMailId(employeeDTO.getEmployeeMailId());
		employee.setEmployeeDesignation(employeeDTO.getEmployeeDesignation());
		employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
		employee.setReportingManager(employeeDTO.getReportingManager());

		// Save the employee
		Employee savedEmployee = employeeRepository.save(employee);

		// Convert saved entity back to DTO and return
		return convertToDTO(savedEmployee);
	}

	// Method to fetch employee details by ID
	public ResponseEntity<?> getEmployeeByEmployeeId(Long employeeId) {
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId);

		if (optionalEmployee.isPresent()) {
			EmployeeDTO employeeDTO = convertToDTO(optionalEmployee.get());

			String baseUrl = "http://localhost:9090/EmployeeTraining/getByEmployeeId/" + employeeId;

			ResponseEntity<EmployeeTrainingDTOResponse[]> responseEntity = restTemplate.getForEntity(baseUrl,
					EmployeeTrainingDTOResponse[].class);

			if (!(responseEntity.getBody() == null)) {
				for (int i = 0; i < responseEntity.getBody().length; i++) {
					employeeDTO.getEmployeeTrainingDetails().add(responseEntity.getBody()[i]);
				}
			}

			return ResponseEntity.ok(employeeDTO); // Return 200 OK with the employee DTO
		} else {
			// Return 404 Not Found with an error message
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + employeeId + " not found");
		}
	}

	private EmployeeDTO convertToDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setEmployeeName(employee.getEmployeeName());
		employeeDTO.setEmployeeMailId(employee.getEmployeeMailId());
		employeeDTO.setEmployeeDesignation(employee.getEmployeeDesignation());
		employeeDTO.setEmployeeSalary(employee.getEmployeeSalary());
		employeeDTO.setReportingManager(employee.getReportingManager());
		return employeeDTO;
	}

	public ResponseEntity<?> addEmployeeTrainingDetails(EmployeeTrainingDTO employeeId) {
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId.getEmpID());

		if (optionalEmployee.isPresent()) {
			EmployeeTrainning empTraining = new EmployeeTrainning();
			empTraining.setCourseDuration(employeeId.getCourseDuration());
			empTraining.setCourseName(employeeId.getCourseName());
			empTraining.setStatus(employeeId.getStatus());
			empTraining.setTotalTimeSpend(employeeId.getTotalTimeSpend());

			Employee e = optionalEmployee.get();
			e.getTrainingDetails().add(empTraining);
			empTraining.setEmployee(e);
			employeeRepository.save(e);
			return ResponseEntity.ok("Training Details added");

		} else {
			// Return 404 Not Found with an error message
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + employeeId + " not found");
		}
	}
}
