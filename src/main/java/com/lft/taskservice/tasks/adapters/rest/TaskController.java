package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.ports.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "tasks")
public class TaskController {

    private final RestTaskMapper taskMapper;
    private final RestAssignmentMapper assignmentMapper;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody NewTaskDto newTaskDto) {
        var task = taskMapper.toDomain(newTaskDto);
        var savedTask = taskService.save(task);
        var taskDto = taskMapper.toDto(savedTask);

        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @PostMapping("/assign")
    public ResponseEntity<AssignmentDto> assignTaskToUser(@RequestBody NewAssignmentDto newAssignmentDto) {
        var assignment = assignmentMapper.toDomain(newAssignmentDto);
        var savedAssignment = taskService.assignTaskToUser(assignment);
        var newAssignment = assignmentMapper.toDto(savedAssignment);
        return new ResponseEntity<>(newAssignment, HttpStatus.CREATED);
    }

}
