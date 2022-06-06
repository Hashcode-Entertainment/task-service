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

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTaskById(@PathVariable("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/assign")
    public ResponseEntity<AssignmentDto> assignTaskToUser(@RequestBody NewAssignmentDto newAssignmentDto) {
        var assignment = assignmentMapper.toDomain(newAssignmentDto);
        var taskToBeAssigned = taskService.findTaskById(newAssignmentDto.getTaskId());
        assignment.setTask(taskToBeAssigned);
        var savedAssignment = taskService.assignTaskToUser(assignment);
        var newAssignment = assignmentMapper.toDto(savedAssignment);
        return new ResponseEntity<>(newAssignment, HttpStatus.CREATED);
    }

    @GetMapping("/assign/{userId}")
    public ResponseEntity<List<TaskDto>> getAllTasksAssignedToUser(@PathVariable("userId") Long userId){
        var tasks = taskService.getAllTasksAssignedToUser(userId);
        List<TaskDto> taskDtos = taskMapper.mapList(tasks, TaskDto.class);
        return new ResponseEntity<>(taskDtos, HttpStatus.FOUND);
    }

    @PutMapping("/assign")
    public ResponseEntity changeDeadlineOfExistingAssignment(@RequestBody NewAssignmentDto newAssignmentDto) {
        var assignmentWithUpdatedDeadline = assignmentMapper.toDomain(newAssignmentDto);
        taskService.changeDeadline(assignmentWithUpdatedDeadline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/assign/{userId}/{taskId}")
    public ResponseEntity unassignTaskFromUser(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId){
        taskService.deleteAssignment(taskId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/assignments/{userId}/{taskId}")
    public ResponseEntity<AssignmentDto> getInfoOnAssignment(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        var assignment = assignmentMapper.toDto(taskService.getInfoOnAssignment(userId, taskId));
        return new ResponseEntity(assignment, HttpStatus.FOUND);
    }

}
