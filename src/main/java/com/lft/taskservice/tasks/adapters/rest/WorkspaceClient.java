package com.lft.taskservice.tasks.adapters.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkspaceClient {

    private static final String WORKSPACE_URL = "http://localhost:8080/workspaces";
    private final RestTemplate restTemplate = new RestTemplate();

    public WorkspaceDto createTaskWorkspace() {
        var newWorkspaceDto = new NewWorkspaceDto("admin", null);
        return restTemplate.postForObject(WORKSPACE_URL, newWorkspaceDto, WorkspaceDto.class);
    }
}
