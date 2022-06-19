package com.lft.taskservice.tasks.adapters.manager;

import com.lft.taskservice.tasks.adapters.client.UserClient;
import com.lft.taskservice.tasks.adapters.client.UserDto;
import com.lft.taskservice.tasks.adapters.client.WorkspaceClient;
import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;
import com.lft.taskservice.tasks.ports.AssignmentService;
import com.lft.taskservice.tasks.ports.TaskService;
import com.lft.taskservice.tasks.utils.TaskConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final UserClient userClient;
    private final WorkspaceClient workspaceClient;
    private final TaskService taskService;
    private final AssignmentService assignmentService;
    private final TaskConverter taskConverter;


    public List<UserDto> getAllUsersAssignedToTask(Long taskId) {
        List<Long> userIds = assignmentService.getAllUsersIdsAssignedToTask(taskId);
        List<UserDto> userDtos = new ArrayList<>();
        for (Long id: userIds) {
            userDtos.add(userClient.getUserById(id));
        }

        return userDtos;
    }

    public Assignment assignTaskToUser(Assignment assignment) {
        var taskToBeAssigned = taskService.findTaskById(assignment.getTask().getId());
        var user = userClient.getUserById(assignment.getUserId());

        var userTaskWorkspace = workspaceClient.createUserTaskWorkspace(user.getEmail(),
                taskToBeAssigned.getWorkspaceId());

        assignment.setTask(taskToBeAssigned);
        assignment.setUserWorkspaceUrl(userTaskWorkspace.getUrl());

        return assignmentService.assignTaskToUser(assignment);
    }

    public Task createTask(Task task) {
        var taskWorkspace = workspaceClient.createAdminTaskWorkspace(task);

        var taskYmlString = taskConverter.convertTaskToYmlString(task);
        workspaceClient.sendFile(taskWorkspace.getId(), taskYmlString);

        task.setWorkspaceUrl(taskWorkspace.getUrl());
        task.setWorkspaceId(taskWorkspace.getId());
        task.setOwnerEmail(task.getOwnerEmail());

        return taskService.save(task);
    }
}
