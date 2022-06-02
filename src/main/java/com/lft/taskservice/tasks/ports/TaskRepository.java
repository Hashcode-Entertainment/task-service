package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository {

    Task save(Task task);

    Assignment assignTaskToUser(Assignment assignment);

    List<Task> getAllTasksAssignedToUser(Long userId);

    Task findById(Long id);

    void changeDeadline(Long userId, Long taskId, LocalDate deadline);

    void deleteAssignment(Long userId, Long taskId);

    Assignment getInfoOnAssignment(Long userId, Long taskId);

    void deleteTaskById(Long taskId);

    void deleteAllAssignmentsToGivenTask(Long taskId);

}
