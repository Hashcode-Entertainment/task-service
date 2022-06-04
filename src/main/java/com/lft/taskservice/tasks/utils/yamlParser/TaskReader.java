package com.lft.taskservice.tasks.utils.yamlParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lft.taskservice.tasks.domain.Task;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class TaskReader { //todo: implement class responsible for parsing .yml task to java object

    public Task readYamlFile(String address) throws FailedToReadTaskFromYamlException {
        File file = new File(address);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        try {
            Task task = objectMapper.readValue(file, Task.class);
            return task;
        } catch (IOException e) {
            throw new FailedToReadTaskFromYamlException();
        }
    }

}