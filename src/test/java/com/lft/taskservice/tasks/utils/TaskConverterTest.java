package com.lft.taskservice.tasks.utils;

import com.lft.taskservice.tasks.domain.Task;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskConverterTest {

    private TaskConverter taskConverter = new TaskConverter();

    @Test
    void whenConvertTaskToYmlString_thenSuccess() {
        //Given
        var task = createTask();
        //When
        var processedTask = taskConverter.convertTaskToYmlString(task);
        String yamlStringTask = returnYamlTask();
        //Then
        assertEquals(yamlStringTask, processedTask);
    }

    private Task createTask() {
        return Task.builder()
                .id(1L)
                .name("Sample Task")
                .description("Description of sample Task")
                .programmingLang("Any")
                .programmingLangVersion("Any")
                .workspaceUrl("Sample URL")
                .ownerEmail("email@email.com")
                .workspaceId(UUID.fromString("f9d71009-5561-4288-8113-4fa4c01ef2a6"))
                .build();
    }

    private String returnYamlTask() {
        return """
                ---
                id: 1
                ownerEmail: "email@email.com"
                name: "Sample Task"
                description: "Description of sample Task"
                programmingLang: "Any"
                programmingLangVersion: "Any"
                workspaceId: "f9d71009-5561-4288-8113-4fa4c01ef2a6"
                workspaceUrl: "Sample URL"
                """;
    }

}

