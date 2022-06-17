package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Assignment;

import java.util.List;

public interface AssignmentService {

    Assignment assignTaskToUser(Assignment assignment);

    List<Assignment> getAllUserAssignments(Long userId);

    void changeDeadline(Assignment assignment);

    void deleteAssignment(Long taskId, Long userId);

    Assignment getInfoOnAssignment(Long userId, Long taskId);

    List<Long> getAllUsersIdsAssignedToTask(Long taskId);
}
