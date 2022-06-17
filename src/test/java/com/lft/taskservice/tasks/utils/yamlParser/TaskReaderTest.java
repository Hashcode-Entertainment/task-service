package com.lft.taskservice.tasks.utils.yamlParser;

import com.lft.taskservice.tasks.domain.Task;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Ignore
class TaskReaderTest {

    private TaskReader taskReader = new TaskReader();

    private static final String INVALID_FILE_LOCATION = "src/test/resources/Tasks/invalid_test_task.yaml";
    public static final String VALID_FILE_LOCATION = "src/test/resources/Tasks/valid_test_task.yaml";

    @Test
    @DisplayName("Should throw exception when invalid file path is provided")
    void whenIncorrectPathProvided_shouldThrowException () throws FailedToReadTaskFromYamlException {
        assertThrows(FailedToReadTaskFromYamlException.class, () -> taskReader.readYamlFile("invalid_path"));
    }

    @Test
    @DisplayName("Should throw exception when unable to read from file")
    void whenFailedToReadFromFile_shouldThrowException () {
        assertThrows(FailedToReadTaskFromYamlException.class, () -> taskReader.readYamlFile(INVALID_FILE_LOCATION));
    }

    @Test
    @DisplayName("Should create a Task when correct .yml file provided")
    void whenCorrectYmlFile_shouldCreateTask () throws FailedToReadTaskFromYamlException {
        Task task = null;
        task = taskReader.readYamlFile(VALID_FILE_LOCATION);
        Task finalTask = task;
        assertAll(
                () -> assertEquals(1L, finalTask.getId()),
                () -> assertEquals("Triangle area", finalTask.getName()),
                () -> assertEquals("Write a class calculating area of the triangle", finalTask.getDescription()),
                () -> assertEquals("Java", finalTask.getProgrammingLang()),
                () -> assertEquals("17", finalTask.getProgrammingLangVersion()),
                () -> assertEquals("fake/directory", finalTask.getWorkspaceUrl())
        );
    }


}
