package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Assignment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestAssignmentMapper {

    private final ModelMapper mapper = new ModelMapper();

    Assignment toDomain(NewAssignmentDto newAssignmentDto) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(newAssignmentDto, Assignment.class);
    }

    AssignmentDto toDto(Assignment assignment) {
        return mapper.map(assignment, AssignmentDto.class);
    }

    @TaskLogging
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}
