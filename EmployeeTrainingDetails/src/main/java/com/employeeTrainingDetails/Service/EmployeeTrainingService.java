package com.employeeTrainingDetails.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employeeTrainingDetails.DTOs.EmployeeTrainingDTOResponse;
import com.employeeTrainingDetails.Entity.Employee;
import com.employeeTrainingDetails.Entity.EmployeeTrainning;
import com.employeeTrainingDetails.Repository.EmployeeRepository;
import com.employeeTrainingDetails.Repository.EmployeeTrainingDetailsRepository;

@Service
public class EmployeeTrainingService {

	@Autowired
	private EmployeeTrainingDetailsRepository employeeTrainingRepo;

	@Autowired
	private EmployeeRepository employeeRepository;

	public ResponseEntity<?> getCoursesByEmployeeId(Long employeeId) {

		Optional<Employee> e = employeeRepository.findByEmployeeId(employeeId);

		if (e.isPresent()) {
			Employee emp = e.get();
			List<EmployeeTrainning> trainingDetails = emp.getTrainingDetails();
			if (!trainingDetails.isEmpty()) {
				List<EmployeeTrainingDTOResponse> response = new ArrayList<EmployeeTrainingDTOResponse>();
				EmployeeTrainingDTOResponse dto = null;
				for (EmployeeTrainning employeeTrainingDTOResponse : trainingDetails) {

					dto = new EmployeeTrainingDTOResponse();
					dto.setCourseDuration(employeeTrainingDTOResponse.getCourseDuration());
					dto.setCourseName(employeeTrainingDTOResponse.getCourseName());
					dto.setStatus(employeeTrainingDTOResponse.getStatus());
					dto.setTotalTimeSpend(employeeTrainingDTOResponse.getTotalTimeSpend());
					response.add(dto);
				}
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("", HttpStatus.OK);
			}
		
		} else {
			return new ResponseEntity<>("Employee with id not found",HttpStatus.NOT_FOUND);
		}
	}
}
