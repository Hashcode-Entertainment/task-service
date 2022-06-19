package com.lft.taskservice.tasks.domain;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.ports.AssignmentRepository;
import com.lft.taskservice.tasks.ports.TaskRepository;
import com.lft.taskservice.tasks.ports.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskProcessor implements TaskService {

    private final TaskRepository taskRepository;
    private final AssignmentRepository assignmentRepository;

    @TaskLogging
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @TaskLogging
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @TaskLogging
    @Override
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @TaskLogging
    @Override
    public void deleteTaskById(Long taskId) {
        assignmentRepository.deleteAllAssignmentsToGivenTask(taskId);
        taskRepository.deleteTaskById(taskId);
    }

    @TaskLogging
    @Override
    public Task updateTask(Task task){
        return taskRepository.updateTask(task);
    }

}