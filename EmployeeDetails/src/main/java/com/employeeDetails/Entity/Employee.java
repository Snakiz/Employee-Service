package com.employeeDetails.Entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employeeId", unique = true)
    private Long employeeId;

    @Column(name = "employee_Name")
    private String employeeName;

    @Column(name = "employee_Mailid")
    private String employeeMailId;

    @Column(name = "employee_Designation")
    private String employeeDesignation;

    @Column(name = "employee_salary", precision = 10, scale = 2)
    private BigDecimal employeeSalary;

    @Column(name = "reporting_Manager")
    private String reportingManager;

    @OneToMany(mappedBy = "employee",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employee")
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

    public BigDecimal getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(BigDecimal employeeSalary) {
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
                    BigDecimal employeeSalary, String reportingManager, List<EmployeeTrainning> trainingDetails) {
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
