package com.vopa.vschool.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherId;
    private Long studentId;
    private Long lessonId;

    private LocalDate assignedDate;
    private LocalDate dueDate;
    private LocalDate completedDate;
    
    

    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getTeacherId() {
		return teacherId;
	}



	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}



	public Long getStudentId() {
		return studentId;
	}



	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}



	public Long getLessonId() {
		return lessonId;
	}



	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}



	public LocalDate getAssignedDate() {
		return assignedDate;
	}



	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}



	public LocalDate getDueDate() {
		return dueDate;
	}



	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}



	public LocalDate getCompletedDate() {
		return completedDate;
	}



	public void setCompletedDate(LocalDate completedDate) {
		this.completedDate = completedDate;
	}



	public AssignmentStatus getStatus() {
		return status;
	}



	public void setStatus(AssignmentStatus status) {
		this.status = status;
	}



	@Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    // Getters and Setters
}