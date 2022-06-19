package com.lft.taskservice.tasks.adapters.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lft.taskservice.tasks.adapters.client.WorkspaceDto;
import com.lft.taskservice.tasks.adapters.manager.ManagerService;
import com.lft.taskservice.tasks.domain.Task;
import com.lft.taskservice.tasks.ports.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    public static final String TASK_NAME_COUNT_NUMBERS = "Count numbers";
    public static final long TASK_ID_1 = 1L;
    public static final String TASK_NAME_EXAMPLE = "Example";
    private final String TASK_URL = "/tasks";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RestTaskMapper restTaskMapper;
    @MockBean
    private TaskService taskService;
    @MockBean
    private ManagerService managerService;

    @Test
    void whenPost_thenStatusIsCreated() throws Exception {
        //Given
        var task = Task.builder()
                .id(TASK_ID_1)
                .name(TASK_NAME_EXAMPLE)
                .build();

        var workspaceDto = new WorkspaceDto(UUID.randomUUID(), "owner@gmail.com", null, "testUrl.com");

        var newTaskDtoAsString = objectMapper.writeValueAsString(task);

        //When
        mockMvc.perform(post(TASK_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskDtoAsString))
                .andDo(print())
                .andExpect(status().isCreated());

        //Then

    }

    @Test
    void whenDelete_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(delete(TASK_URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk());

        //Then
    }

}
