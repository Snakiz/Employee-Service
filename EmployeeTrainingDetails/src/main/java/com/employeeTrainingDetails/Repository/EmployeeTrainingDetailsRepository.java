package com.employeeTrainingDetails.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employeeTrainingDetails.Entity.EmployeeTrainning;

public interface EmployeeTrainingDetailsRepository extends JpaRepository<EmployeeTrainning, Integer> {

	Optional<List<EmployeeTrainning>> getByEmployeeId(Long employeeId);

	@Query(value = "select et1_0.id,et1_0.course_duration,et1_0.course_name,et1_0.emp_id,et1_0.status,et1_0.total_time_spend from employee_trainning et1_0 where et1_0.emp_id=?1", nativeQuery = true)
	Optional<List<EmployeeTrainning>> getByEmployeeTrainingByEmployeeId(Long employeeId);

}
