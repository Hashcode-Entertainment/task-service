package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;

import java.time.LocalDate;
import java.util.List;

public interface AssignmentRepository {

    void deleteAllAssignmentsToGivenTask(Long taskId);

    Assignment getInfoOnAssignment(Long userId, Long taskId);

    void deleteAssignment(Long userId, Long taskId);

    void changeDeadline(Long userId, Long taskId, LocalDate deadline);

    List<Task> getAllTasksAssignedToUser(Long userId);

    Assignment assignTaskToUser(Assignment assignment);

    List<Long> getAllUsersIdsAssignedToTask(Long taskId);
}
