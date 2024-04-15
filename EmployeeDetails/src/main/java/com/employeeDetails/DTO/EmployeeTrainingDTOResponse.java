package com.employeeDetails.DTO;

public class EmployeeTrainingDTOResponse {
	private String courseName;

	private long courseDuration;

	private long totalTimeSpend;

	private String status;

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
    
	
	
}
