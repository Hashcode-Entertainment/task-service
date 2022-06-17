package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;

import java.util.List;

public interface AssignmentService {

    Assignment assignTaskToUser(Assignment assignment);

    List<Task> getAllTasksAssignedToUser(Long userId);

    void changeDeadline(Assignment assignment);

    void deleteAssignment(Long taskId, Long userId);

    Assignment getInfoOnAssignment(Long userId, Long taskId);

    List<Long> getAllUsersIdsAssignedToTask(Long taskId);
}
