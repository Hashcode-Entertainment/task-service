package com.lft.taskservice.tasks.utils.yamlParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lft.taskservice.tasks.domain.Task;

public class TaskConverter {

    public String convertTaskToYmlString(Task task) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        var taskYamlString = "";
        try {
            taskYamlString = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return taskYamlString;
    }

}
