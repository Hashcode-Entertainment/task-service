package com.lft.taskservice.tasks.domain;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.ports.TaskRepository;
import com.lft.taskservice.tasks.ports.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskProcessor implements TaskService {

    private final TaskRepository taskRepository;

    @TaskLogging
    @Override
    public Task save(Task task) {
        // TODO: Get url from Microservice-2 response
        String url = "https://testurl.com/abcde";

        task.setWorkspaceUrl(url);
        return taskRepository.save(task);
    }

    @Override
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @TaskLogging
    @Override
    public Assignment assignTaskToUser(Assignment assignment){
        return taskRepository.assignTaskToUser(assignment);
    }

    @Override
    public List<Task> getAllTasksAssignedToUser(Long userId) {
        var tasks = taskRepository.getAllTasksAssignedToUser(userId);
        return tasks;
    }

    @Override
    public void changeDeadline(Assignment assignment) {
        taskRepository.changeDeadline(assignment.getUserId(), assignment.getTask().getId(), assignment.getDeadline());
    }

    @Override
    public void deleteAssignment(Long taskId, Long userId) {
        taskRepository.deleteAssignment(userId, taskId);
    }
}
