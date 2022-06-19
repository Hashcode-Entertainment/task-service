package com.lft.taskservice.tasks.adapters.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class WorkspaceClient {

    private static final String WORKSPACE_URL = "http://localhost:8080/workspaces";
    private final RestTemplate restTemplate = new RestTemplate();

    public WorkspaceDto createAdminTaskWorkspace(NewTaskDto newTaskDto) {
        var ownerEmail = newTaskDto.getOwnerEmail();
        var newWorkspaceDto = new NewWorkspaceDto(ownerEmail, null);
        return restTemplate.postForObject(WORKSPACE_URL, newWorkspaceDto, WorkspaceDto.class);
    }

    public WorkspaceDto createUserTaskWorkspace(String ownerEmail, UUID adminWorkspaceId) {
        var newWorkspaceDto = new NewWorkspaceDto(ownerEmail, adminWorkspaceId.toString());
        return restTemplate.postForObject(WORKSPACE_URL, newWorkspaceDto, WorkspaceDto.class);
    }

    public void sendFile(UUID id, String taskYmlString) {
        var addFilesRequestDto = new AddFilesRequestDto("task.yml", taskYmlString);
        var request = List.of(addFilesRequestDto);
        restTemplate.postForLocation(WORKSPACE_URL + "/" + id + "/files", request);
    }
}
