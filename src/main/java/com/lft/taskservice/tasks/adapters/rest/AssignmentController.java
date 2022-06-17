package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.ports.AssignmentService;
import com.lft.taskservice.tasks.ports.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "assignments")
public class AssignmentController {

    private final RestAssignmentMapper assignmentMapper;
    private final RestTaskMapper taskMapper;
    private final AssignmentService assignmentService;
    private final TaskService taskService;
    private final WorkspaceClient workspaceClient;
    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<AssignmentDto> assignTaskToUser(@RequestBody NewAssignmentDto newAssignmentDto) {
        var assignment = assignmentMapper.toDomain(newAssignmentDto);
        var taskToBeAssigned = taskService.findTaskById(newAssignmentDto.getTaskId());
        var user = userClient.getUserById(newAssignmentDto.getUserId());

        var userTaskWorkspace = workspaceClient.createUserTaskWorkspace(user.getEmail(),
                taskToBeAssigned.getWorkspaceId());

        assignment.setTask(taskToBeAssigned);
        assignment.setWorkspaceUrl(userTaskWorkspace.getUrl());

        var savedAssignment = assignmentService.assignTaskToUser(assignment);
        var newAssignment = assignmentMapper.toDto(savedAssignment);
        return new ResponseEntity<>(newAssignment, HttpStatus.CREATED);
    }

    @GetMapping("{taskId}/users")
    public ResponseEntity<List<Long>> getAllUsersAssignedToTask(@PathVariable Long taskId) {
        List<Long> usersIds = assignmentService.getAllUsersIdsAssignedToTask(taskId);
        return new ResponseEntity<>(usersIds, HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<TaskDto>> getAllTasksAssignedToUser(@PathVariable("userId") Long userId){
        var tasks = assignmentService.getAllTasksAssignedToUser(userId);
        List<TaskDto> taskDtos = taskMapper.mapList(tasks, TaskDto.class);
        return new ResponseEntity<>(taskDtos, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity changeDeadlineOfExistingAssignment(@RequestBody NewAssignmentDto newAssignmentDto) {
        var assignmentWithUpdatedDeadline = assignmentMapper.toDomain(newAssignmentDto);
        assignmentService.changeDeadline(assignmentWithUpdatedDeadline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{userId}/{taskId}")
    public ResponseEntity unassignTaskFromUser(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId){
        assignmentService.deleteAssignment(taskId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{userId}/{taskId}")
    public ResponseEntity<AssignmentDto> getInfoOnAssignment(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        var assignment = assignmentMapper.toDto(assignmentService.getInfoOnAssignment(userId, taskId));
        return new ResponseEntity(assignment, HttpStatus.FOUND);
    }
}
