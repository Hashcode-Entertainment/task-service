package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RestAssignmentMapperTest {

    public static final long TASK_ID_1 = 1L;
    public static final long USER_ID_1 = 1L;
    public static final LocalDate DEADLINE = LocalDate.of(2012, 1, 12);
    public static final int ASSIGNMENT_DTOS_COUNT = 2;
    private final RestAssignmentMapper mapper = new RestAssignmentMapper();

    @Test
    void whenToDomain_thenResultPropertiesEqual() {
        //Given
        var newAssignmentDto = NewAssignmentDto.builder()
                .taskId(TASK_ID_1)
                .userId(USER_ID_1)
                .deadline(DEADLINE)
                .build();

        //When
        var assignment = mapper.toDomain(newAssignmentDto);

        //Then
        assertAll(
                () -> assertEquals(TASK_ID_1, assignment.getTask().getId()),
                () -> assertEquals(USER_ID_1, assignment.getUserId()),
                () -> assertEquals(DEADLINE, assignment.getDeadline())
        );

    }

    @Test
    void whenToDto_thenResultPropertiesEqual() {
        //Given
        var task = Task.builder()
                .id(TASK_ID_1)
                .build();

        var assignment = Assignment.builder()
                .task(task)
                .userId(USER_ID_1)
                .deadline(DEADLINE)
                .build();

        //When
        var assignmentDto = mapper.toDto(assignment);

        //Then
        assertAll(
                () -> assertEquals(TASK_ID_1, assignmentDto.getTask().getId()),
                () -> assertEquals(USER_ID_1, assignmentDto.getUserId()),
                () -> assertEquals(DEADLINE, assignmentDto.getDeadline())
        );
    }

    @Test
    void whenMapListOfDomainObjects_thenResultsCountEqual() {
        //Given
        var assignmentList = List.of(
                new Assignment(),
                new Assignment()
        );

        //When
        var assignmentDtoList = mapper.mapList(assignmentList, AssignmentDto.class);

        //Then
        assertAll(
                () -> assertEquals(ASSIGNMENT_DTOS_COUNT, assignmentDtoList.size())
        );
    }
}
