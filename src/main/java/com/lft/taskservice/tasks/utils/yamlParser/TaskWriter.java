package com.lft.taskservice.tasks.utils.yamlParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lft.taskservice.tasks.domain.Task;

import java.io.File;
import java.io.IOException;

public class TaskWriter {

    public void writeTaskToFile(Task task) throws FailedToWriteTaskToYamlException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            objectMapper.writeValue(new File(task.getWorkspaceUrl() + "\\" + task.getId() + ".yml"), task);
        } catch (IOException e){
            throw new FailedToWriteTaskToYamlException();
        }
    }

}