package com.lft.taskservice.tasks.adapters.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;
import com.lft.taskservice.tasks.ports.AssignmentService;
import com.lft.taskservice.tasks.ports.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.TestClass;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssignmentController.class)
class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RestAssignmentMapper restAssignmentMapper;
    @MockBean
    private RestTaskMapper restTaskMapper;
    @MockBean
    private TaskService taskService;
    @MockBean
    private AssignmentService assignmentService;

    protected Assignment createAssignment(){
        return Assignment.builder().id(1L)
                .userId(666L)
                .task(createTask())
                .assignedOn(LocalDate.now())
                .deadline(LocalDate.MAX)
                .build();
    }

    protected Task createTask() {
        return Task.builder()
                .id(1L)
                .name("Test task")
                .description("Mock task")
                .programmingLang("Any")
                .programmingLangVersion("Any")
                .workspaceUrl("Any")
                .build();
    }


}