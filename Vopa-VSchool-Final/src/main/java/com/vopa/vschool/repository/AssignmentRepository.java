package com.vopa.vschool.repository;

import com.vopa.vschool.model.Assignment;
import com.vopa.vschool.model.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByStudentIdAndStatus(Long studentId, AssignmentStatus status);
    List<Assignment> findByTeacherId(Long teacherId);
}