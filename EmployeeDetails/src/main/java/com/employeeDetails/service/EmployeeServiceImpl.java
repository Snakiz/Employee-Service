package com.employeeDetails.service;

import java.util.List;
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
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
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

    @Override
    public ResponseEntity<String> getEmployeeByEmployeeId(Long employeeId) {

        Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId);

        if (optionalEmployee.isPresent()) {

            EmployeeDTO employeeDTO = convertToDTO(optionalEmployee.get());

            String baseUrl = "http://localhost:9090/EmployeeTraining/getByEmployeeId/" + employeeId;

            ResponseEntity<EmployeeTrainingDTOResponse[]> responseEntity =
                    restTemplate.getForEntity(baseUrl, EmployeeTrainingDTOResponse[].class);

            if (responseEntity.getBody() != null) {
                for (EmployeeTrainingDTOResponse training : responseEntity.getBody()) {
                    employeeDTO.getEmployeeTrainingDetails().add(training);
                }
            }

            StringBuilder response = new StringBuilder();

            response.append("Employee Details Found..!!\n\n");
            response.append("Details :\n");
            response.append("EmployeeId: ").append(employeeDTO.getEmployeeId()).append("\n");
            response.append("EmployeeName: ").append(employeeDTO.getEmployeeName()).append("\n");
            response.append("EmployeeMailId: ").append(employeeDTO.getEmployeeMailId()).append("\n");
            response.append("EmployeeDesignation: ").append(employeeDTO.getEmployeeDesignation()).append("\n");
            response.append("EmployeeSalary: ").append(employeeDTO.getEmployeeSalary()).append("\n");
            response.append("ReportingManager: ").append(employeeDTO.getReportingManager()).append("\n");
            response.append("EmployeeTrainingDetails: ").append(employeeDTO.getEmployeeTrainingDetails().toString());

            return ResponseEntity.ok(response.toString());

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee with ID " + employeeId + " not found");
        }
    }

    @Override
    public ResponseEntity<String> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            return ResponseEntity.ok("No employees found");
        }

        StringBuilder response = new StringBuilder();

        response.append("All Employees Details\n\n");

        for (Employee emp : employees) {

            EmployeeDTO dto = convertToDTO(emp);

            // Call Training Microservice
            String baseUrl = "http://localhost:9090/EmployeeTraining/getByEmployeeId/" + dto.getEmployeeId();

            ResponseEntity<EmployeeTrainingDTOResponse[]> trainingResponse =
                    restTemplate.getForEntity(baseUrl, EmployeeTrainingDTOResponse[].class);

            if (trainingResponse.getBody() != null) {
                for (EmployeeTrainingDTOResponse t : trainingResponse.getBody()) {
                    dto.getEmployeeTrainingDetails().add(t);
                }
            }

            response.append("Employee Id: ").append(dto.getEmployeeId()).append("\n");
            response.append("Name: ").append(dto.getEmployeeName()).append("\n");
            response.append("Email: ").append(dto.getEmployeeMailId()).append("\n");
            response.append("Designation: ").append(dto.getEmployeeDesignation()).append("\n");
            response.append("Salary: ").append(dto.getEmployeeSalary()).append("\n");
            response.append("Manager: ").append(dto.getReportingManager()).append("\n");
            response.append("Training: ").append(dto.getEmployeeTrainingDetails()).append("\n");
            response.append("-------------------------------------\n");
        }

        return ResponseEntity.ok(response.toString());
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

    public ResponseEntity<?> addEmployeeTrainingDetails(EmployeeTrainingDTO trainingDTO) {

        Long empId = trainingDTO.getEmployeeId();

        Optional<Employee> optionalEmployee =
                employeeRepository.findByEmployeeId(empId);

        if (optionalEmployee.isPresent()) {

            EmployeeTrainning empTraining = new EmployeeTrainning();
            empTraining.setCourseDuration(trainingDTO.getCourseDuration());
            empTraining.setCourseName(trainingDTO.getCourseName());
            empTraining.setStatus(trainingDTO.getStatus());
            empTraining.setTotalTimeSpend(trainingDTO.getTotalTimeSpend());

            Employee e = optionalEmployee.get();
            e.getTrainingDetails().add(empTraining);
            empTraining.setEmployee(e);

            employeeRepository.save(e);

            return ResponseEntity.ok("Training Details added successfully for employeeId: " + empId);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee with ID " + empId + " not found");
        }
    }

}
