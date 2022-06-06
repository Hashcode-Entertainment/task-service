package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.ports.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/assign/{userId}")
    public ResponseEntity<List<TaskDto>> getAllTasksAssignedToUser(@PathVariable("userId") Long userId){
        var tasks = taskService.getAllTasksAssignedToUser(userId);
        List<TaskDto> taskDtos = tasks.stream().map(task -> taskMapper.toDto(task)).collect(Collectors.toList());
        return new ResponseEntity<>(taskDtos, HttpStatus.FOUND);
    }

}
