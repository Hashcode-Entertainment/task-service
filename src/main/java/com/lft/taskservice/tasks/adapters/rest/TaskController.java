package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.ports.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "tasks")
public class TaskController {

    private final RestTaskMapper taskMapper;
    private final TaskService taskService;
    private final WorkspaceClient workspaceClient;

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        var taskList = taskService.findAll();
        var taskDtos = taskMapper.mapList(taskList, TaskDto.class);
        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody NewTaskDto newTaskDto) {
        var task = taskMapper.toDomain(newTaskDto);
        var taskWorkspace = workspaceClient.createAdminTaskWorkspace(newTaskDto);
        task.setWorkspaceUrl(taskWorkspace.getUrl());
        task.setOwnerEmail(newTaskDto.getOwnerEmail());
        var savedTask = taskService.save(task);
        var taskDto = taskMapper.toDto(savedTask);

        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTaskById(@PathVariable("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);
        return new ResponseEntity(HttpStatus.OK);
    }



}
