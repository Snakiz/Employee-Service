package com.employeeDetails.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
   
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
		
	@Column(name = "employeeId",unique = true)
	private Long employeeId;

	@Column(name = "employee_Name") 
	private String employeeName;

	@Column(name = "employee_Mailid")
	private String employeeMailId;

	@Column(name = "employee_Designation")
	private String employeeDesignation;

	@Column(name = "employee_Salary") 
	private float employeeSalary;

	@Column(name = "reporting_Manager") 
	private String reportingManager;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeTrainning> trainingDetails;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeMailId() {
		return employeeMailId;
	}

	public void setEmployeeMailId(String employeeMailId) {
		this.employeeMailId = employeeMailId;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public float getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(float employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
    
	
	public List<EmployeeTrainning> getTrainingDetails() {
		return trainingDetails;
	}

	public void setTrainingDetails(List<EmployeeTrainning> trainingDetails) {
		this.trainingDetails = trainingDetails;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long employeeId, String employeeName, String employeeMailId, String employeeDesignation,
			float employeeSalary, String reportingManager, List<EmployeeTrainning> trainingDetails) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeMailId = employeeMailId;
		this.employeeDesignation = employeeDesignation;
		this.employeeSalary = employeeSalary;
		this.reportingManager = reportingManager;
		this.trainingDetails = trainingDetails;
	}
	
	
	
}
