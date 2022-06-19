package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.domain.Assignment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaAssignmentMapperTest {

    public static final long USER_ID_1 = 1L;
    public static final LocalDateTime DEADLINE = LocalDateTime.of(2022, 1, 12, 12, 24);
    public static final LocalDateTime ASSIGNED_ON = LocalDateTime.of(2021, 2, 15, 2, 44);
    public static final int ASSIGNMENTS_COUNT = 2;

    private JpaAssignmentMapper mapper = new JpaAssignmentMapper();

    @Test
    void whenToDomain_thenPropertiesEqual() {
        //Given
        var assignmentEntity = AssignmentEntity.builder()
                .userId(USER_ID_1)
                .deadline(DEADLINE)
                .assignedOn(ASSIGNED_ON)
                .build();

        //When
        var assignment = mapper.toDomain(assignmentEntity);

        //Then
        assertAll(
                () -> assertEquals(USER_ID_1, assignment.getUserId()),
                () -> assertEquals(DEADLINE, assignment.getDeadline()),
                () -> assertEquals(ASSIGNED_ON, assignment.getAssignedOn())
        );
    }

    @Test
    void whenToEntity_thenPropertiesEqual() {
        //Given
        var assignment = Assignment.builder()
                .userId(USER_ID_1)
                .deadline(DEADLINE)
                .assignedOn(ASSIGNED_ON)
                .build();

        //When
        var assignmentEntity = mapper.toEntity(assignment);

        //Then
        assertAll(
                () -> assertEquals(USER_ID_1, assignmentEntity.getUserId()),
                () -> assertEquals(DEADLINE, assignmentEntity.getDeadline()),
                () -> assertEquals(ASSIGNED_ON, assignmentEntity.getAssignedOn())
        );
    }

    @Test
    void whenMapListOfEntities_thenResultsCountEquals() {
        //Given
        var assignmentEntityList = List.of(
                new AssignmentEntity(),
                new AssignmentEntity());

        //When
        var assignmentList = mapper.mapList(assignmentEntityList, Assignment.class);

        //Then
        assertEquals(ASSIGNMENTS_COUNT, assignmentList.size());
    }

    @Test
    void whenMapListOfDomainObjects_thenResultsCountEquals() {
        //Given
        var assignmentList = List.of(
                new Assignment(),
                new Assignment());

        //When
        var assignmentEntityList = mapper.mapList(assignmentList, AssignmentEntity.class);

        //Then
        assertEquals(ASSIGNMENTS_COUNT, assignmentEntityList.size());
    }

}
