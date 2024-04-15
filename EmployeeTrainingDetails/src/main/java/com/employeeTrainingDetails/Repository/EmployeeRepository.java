package com.employeeTrainingDetails.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeTrainingDetails.Entity.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmployeeId(Long employeeId);

}
