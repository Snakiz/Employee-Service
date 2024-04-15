package com.employeeDetails.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EmployeeTrainning {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "course_Name")
	private String courseName;

	@Column(name = "course_Duration")
	private long courseDuration;

	@Column(name = "total_Time_Spend")
	private long totalTimeSpend;

	private String status;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "empId", referencedColumnName = "employeeId")
	private Employee employee;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(long courseDuration) {
		this.courseDuration = courseDuration;
	}

	public long getTotalTimeSpend() {
		return totalTimeSpend;
	}

	public void setTotalTimeSpend(long totalTimeSpend) {
		this.totalTimeSpend = totalTimeSpend;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeTrainning() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeTrainning(String courseName, long courseDuration, long totalTimeSpend, String status,
			Employee employee) {
		super();
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.totalTimeSpend = totalTimeSpend;
		this.status = status;
		this.employee = employee;
	}

}
