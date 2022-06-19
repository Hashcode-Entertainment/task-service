package com.lft.taskservice.tasks.adapters.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lft.taskservice.tasks.adapters.manager.ManagerService;
import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;
import com.lft.taskservice.tasks.ports.AssignmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private ManagerService managerService;
    @MockBean
    private AssignmentService assignmentService;

    @Test
    @DisplayName("Should return list of Tasks assigned to a given user")
    void whenCorrectUserIdProvidedAndTasksAreAssigned_shouldReturnHTTP302() throws Exception {
        List tasks = createTasks(4L);
        Mockito.when(assignmentService.getAllUserAssignments(any())).thenReturn(tasks);
        ResultActions resultActions = mockMvc.perform(get("http://localhost/assignments/666"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Get tasks assigned endpoint test")
    void whenCorrectUserIdProvidedAndNoTasksAreAssigned_shouldReturnEmptyListAndHTTP302() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("http://localhost/assignments/666"))
                .andDo(print())
                .andExpect(status().isFound());
        String emptyList = resultActions.andReturn().getResponse().getContentAsString();
        assertEquals("[]", emptyList);
    }

    @Test
    @DisplayName("Change deadline endpoint test")
    void whenCorrectTaskIdAndUserId_ShouldReturnHTTP201() throws Exception {
        doNothing().when(assignmentService).changeDeadline(any());
        var assignmentAsJSON = objectMapper.writeValueAsString(createAssignment());
        mockMvc.perform(put("http://localhost/assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(assignmentAsJSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Unassign endpoint test")
    void whenCorrectTaskIdAndUserId_shouldDeleteAssisgnmentAndReturnHTTP201() throws Exception {
        doNothing().when(assignmentService).deleteAssignment(any(), any());

        mockMvc.perform(delete("http://localhost/assignments/666/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    protected Assignment createAssignment(){
        return Assignment.builder().id(1L)
                .userId(666L)
                .task(createTask())
                .assignedOn(LocalDateTime.now())
                .deadline(LocalDateTime.MAX)
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

    protected List<Task> createTasks(Long amount) {
        List<Task> tasks = new ArrayList<>();
        for (Long i = 1L; i<amount; i++) {
            tasks.add(Task.builder()
                    .id(i)
                    .name("Task " + i)
                    .description("Task " + i + " description")
                    .programmingLang("Any")
                    .programmingLangVersion("Any")
                    .workspaceUrl("Any")
                    .build());
        }
        return tasks;
    }

}
