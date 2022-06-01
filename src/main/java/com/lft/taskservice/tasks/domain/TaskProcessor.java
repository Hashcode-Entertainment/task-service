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

    @TaskLogging
    @Override
    public Assignment assignTaskToUser(Assignment assignment){
        return taskRepository.assignTaskToUser(assignment);
    }

    @Override
    public List<Task> getAllTasksAssignedToUser(Long userId) {
        var ids = taskRepository.getAllIdsOfTasksAssignedToUser(userId);
        List<Task> tasks = new ArrayList<>();
        for (Long id: ids) {
            tasks.add(taskRepository.findById(id));
        }
        return tasks;
    }

    @Override
    public Assignment changeDeadline(Assignment assignment) {
        return taskRepository.changeDeadline(assignment.getUserId(), assignment.getTaskId(), assignment.getDeadline());
    }

}
