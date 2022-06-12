package com.lft.taskservice.tasks.domain;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.ports.AssignmentRepository;
import com.lft.taskservice.tasks.ports.TaskRepository;
import com.lft.taskservice.tasks.ports.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskProcessor implements TaskService {

    private final TaskRepository taskRepository;
    private final AssignmentRepository assignmentRepository;

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

    @Override
    public void deleteTaskById(Long taskId) {
        //TODO: liaise with other microservices to ensure that deleting a task from database results in deleting task from any associated workspaces
        assignmentRepository.deleteAllAssignmentsToGivenTask(taskId);
        taskRepository.deleteTaskById(taskId);
    }

}
