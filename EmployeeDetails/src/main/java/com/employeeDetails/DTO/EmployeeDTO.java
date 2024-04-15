package com.employeeDetails.DTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {
    private Long employeeId;
    private String employeeName;
    private String employeeMailId;
    private String employeeDesignation;
    private float employeeSalary;
    private String reportingManager;
    private List<EmployeeTrainingDTOResponse> employeeTrainingDetails=new ArrayList<EmployeeTrainingDTOResponse>();
    
    
    // Constructors
    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeName, String employeeMailId, String employeeDesignation, float employeeSalary, String reportingManager) {
        this.employeeName = employeeName;
        this.employeeMailId = employeeMailId;
        this.employeeDesignation = employeeDesignation;
        this.employeeSalary = employeeSalary;
        this.reportingManager = reportingManager;
    }

    // Getters and Setters
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

	public List<EmployeeTrainingDTOResponse> getEmployeeTrainingDetails() {
		return employeeTrainingDetails;
	}

	public void setEmployeeTrainingDetails(List<EmployeeTrainingDTOResponse> employeeTrainingDetails) {
		this.employeeTrainingDetails = employeeTrainingDetails;
	}


    
    
}
