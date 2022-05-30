package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.domain.Assignment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RestAssignmentMapper {

    private final ModelMapper mapper = new ModelMapper();

    Assignment toDomain(AssignmentDto assignmentDto) {
        return mapper.map(assignmentDto, Assignment.class);
    }

    AssignmentDto toDto(Assignment assignment) {
        return mapper.map(assignment, AssignmentDto.class);
    }

}
