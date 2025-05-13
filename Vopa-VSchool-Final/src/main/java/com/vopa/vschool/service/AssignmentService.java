package com.vopa.vschool.service;

import com.vopa.vschool.dto.AssignmentRequest;
import com.vopa.vschool.dto.AssignmentResponse;
import com.vopa.vschool.model.Assignment;
import com.vopa.vschool.model.AssignmentStatus;
import com.vopa.vschool.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository repository;

    public AssignmentResponse assignLesson(AssignmentRequest request) {
        Assignment assignment = new Assignment();
        assignment.setTeacherId(request.getTeacherId());
        assignment.setStudentId(request.getStudentId());
        assignment.setLessonId(request.getLessonId());
        assignment.setAssignedDate(LocalDate.now());
        assignment.setDueDate(request.getDueDate());
        assignment.setStatus(AssignmentStatus.ASSIGNED);
        repository.save(assignment);
        return toResponse(assignment);
    }

    public List<AssignmentResponse> getIncompleteAssignments(Long studentId) {
        return repository.findByStudentIdAndStatus(studentId, AssignmentStatus.ASSIGNED)
                         .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AssignmentResponse markAsComplete(Long assignmentId) {
        Assignment assignment = repository.findById(assignmentId)
                                          .orElseThrow(() -> new RuntimeException("Not found"));
        assignment.setStatus(AssignmentStatus.COMPLETED);
        assignment.setCompletedDate(LocalDate.now());
        repository.save(assignment);
        return toResponse(assignment);
    }

    public List<AssignmentResponse> getAssignmentsByTeacher(Long teacherId) {
        return repository.findByTeacherId(teacherId)
                         .stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AssignmentResponse toResponse(Assignment assignment) {
        AssignmentResponse response = new AssignmentResponse();
        response.setAssignmentId(assignment.getId());
        response.setTeacherId(assignment.getTeacherId());
        response.setStudentId(assignment.getStudentId());
        response.setLessonId(assignment.getLessonId());
        response.setAssignedDate(assignment.getAssignedDate());
        response.setDueDate(assignment.getDueDate());
        response.setCompletedDate(assignment.getCompletedDate());
        response.setStatus(assignment.getStatus().name());
        return response;
    }
}