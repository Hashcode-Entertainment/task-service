package com.lft.taskservice.tasks.utils.yamlParser;

import com.lft.taskservice.tasks.domain.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class TaskReaderTest {

    private TaskReader taskReader = new TaskReader();

    private static final String INVALID_FILE_LOCATION = "Tasks/invalid_test_task.yaml";
    public static final String VALID_FILE_LOCATION = "Tasks/valid_test_task.yaml";

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



}