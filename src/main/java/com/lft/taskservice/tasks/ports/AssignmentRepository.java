package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Assignment;

import java.time.LocalDateTime;
import java.util.List;

public interface AssignmentRepository {

    void deleteAllAssignmentsToGivenTask(Long taskId);

    Assignment getInfoOnAssignment(Long userId, Long taskId);

    void deleteAssignment(Long userId, Long taskId);

    void changeDeadline(Long userId, Long taskId, LocalDateTime deadline);

    List<Assignment> getAllUserAssignments(Long userId);

    Assignment assignTaskToUser(Assignment assignment);

    List<Long> getAllUsersIdsAssignedToTask(Long taskId);

}