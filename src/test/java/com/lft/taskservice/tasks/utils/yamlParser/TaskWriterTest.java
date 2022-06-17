package com.lft.taskservice.tasks.utils.yamlParser;

import com.lft.taskservice.tasks.domain.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskWriterTest {

    private TaskWriter taskWriter = new TaskWriter();
    private TaskReader taskReader = new TaskReader();

    private static final String BASE_FILE_LOCATION = "src/test/resources/Tasks";

    @Test
    @DisplayName("Should write Task to .yml file and correctly retrieve it back")
    void whenIncorrectPathProvided_shouldThrowException () throws FailedToWriteTaskToYamlException, FailedToReadTaskFromYamlException {
        Task task = Task.builder()
                .id(5L)
                .name("Write test")
                .description("Testing writing to file")
                .programmingLang("Any")
                .programmingLangVersion("Any")
                .workspaceUrl("src/test/resources/Tasks")
                .build();

        taskWriter.writeTaskToFile(task);

        Task retrievedTask = taskReader.readYamlFile(BASE_FILE_LOCATION + "/5.yml");
        Task finalTask = retrievedTask;

        assertAll(
                        () -> assertEquals(5L, finalTask.getId()),
                        () -> assertEquals("Write test", finalTask.getName()),
                        () -> assertEquals("Testing writing to file", finalTask.getDescription()),
                        () -> assertEquals("Any", finalTask.getProgrammingLang()),
                        () -> assertEquals("Any", finalTask.getProgrammingLangVersion()),
                        () -> assertEquals("src/test/resources/Tasks", finalTask.getWorkspaceUrl())
                );

    }

}