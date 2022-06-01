package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository {

    Task save(Task task);

    Assignment assignTaskToUser(Assignment assignment);

    List<Long> getAllIdsOfTasksAssignedToUser(Long userId);

    Task findById(Long id);

    Assignment changeDeadline(Long userId, Long taskId, LocalDate deadline);

}
