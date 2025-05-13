package com.vopa.vschool.controller;

import com.vopa.vschool.dto.AssignmentRequest;
import com.vopa.vschool.dto.AssignmentResponse;
import com.vopa.vschool.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    @PostMapping
    public ResponseEntity<AssignmentResponse> assignLesson(@RequestBody AssignmentRequest request) {
        return ResponseEntity.ok(service.assignLesson(request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssignmentResponse>> getIncompleteAssignments(@PathVariable Long studentId,
                                                                             @RequestParam String status) {
        if ("incomplete".equalsIgnoreCase(status)) {
            return ResponseEntity.ok(service.getIncompleteAssignments(studentId));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{assignmentId}/complete")
    public ResponseEntity<AssignmentResponse> markAsComplete(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(service.markAsComplete(assignmentId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<AssignmentResponse>> getTeacherAssignments(@PathVariable Long teacherId) {
        return ResponseEntity.ok(service.getAssignmentsByTeacher(teacherId));
    }
}