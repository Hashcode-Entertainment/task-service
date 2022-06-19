package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.adapters.manager.ManagerService;
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
    private final ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        var taskList = taskService.findAll();
        var taskDtos = taskMapper.mapList(taskList, TaskDto.class);
        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long taskId) {
        var task = taskService.findTaskById(taskId);
        var taskDto = taskMapper.toDto(task);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody NewTaskDto newTaskDto) {
        var task = taskMapper.toDomain(newTaskDto);
        var savedTask = managerService.createTask(task);
        var taskDto = taskMapper.toDto(savedTask);
        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTaskById(@PathVariable("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/taskId")
    public ResponseEntity updateTask(@RequestBody TaskDto updatedTask){
        var task = taskMapper.toDomain(updatedTask);
        var savedTask = taskService.updateTask(task);
        var taskDto = taskMapper.toDto(savedTask);
        return new ResponseEntity(taskDto, HttpStatus.OK);
    }

}
