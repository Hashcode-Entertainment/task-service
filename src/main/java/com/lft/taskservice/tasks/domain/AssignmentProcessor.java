package com.lft.taskservice.tasks.domain;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.ports.AssignmentRepository;
import com.lft.taskservice.tasks.ports.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssignmentProcessor implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @TaskLogging
    @Override
    public Assignment assignTaskToUser(Assignment assignment){
        return assignmentRepository.assignTaskToUser(assignment);
    }

    @TaskLogging
    @Override
    public List<Assignment> getAllUserAssignments(Long userId) {
        return assignmentRepository.getAllUserAssignments(userId);
    }

    @Override
    public void changeDeadline(Assignment assignment) {
        assignmentRepository.changeDeadline(assignment.getUserId(), assignment.getTask().getId(), assignment.getDeadline());
    }

    @Override
    public void deleteAssignment(Long taskId, Long userId) {
        assignmentRepository.deleteAssignment(userId, taskId);
    }

    @Override
    public Assignment getInfoOnAssignment(Long userId, Long taskId) {
        return assignmentRepository.getInfoOnAssignment(userId, taskId);
    }

    @Override
    public List<Long> getAllUsersIdsAssignedToTask(Long taskId) {
        return assignmentRepository.getAllUsersIdsAssignedToTask(taskId);
    }

}