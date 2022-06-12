package com.lft.taskservice.tasks.adapters.persistance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaAssignmentRepositoryTest {

    @Autowired
    JpaAssignmentRepository assignmentRepository;

    @Autowired
    JpaTaskRepository taskRepository;

    @DisplayName("Empty database is initialized. Should return no records")
    @Test
    void shouldReturnNoRecords() {
        assertEquals(0, assignmentRepository.findAllTasksAssignedToUser(1L).size());
    }
    @Test
    @DirtiesContext
    @DisplayName("Saving assignment to database")
    void shouldSaveAssignmentToTheDatabase() {
        populateTasks();
        assignmentRepository.save(
                AssignmentEntity.builder()
                        .task(taskRepository.getReferenceById(1L))
                        .userId(666L)
                        .deadline(LocalDate.now())
                        .assignedOn(LocalDate.now())
                        .build());
        assertEquals(1, assignmentRepository.findAll().size());
    }


    @Test
    @DirtiesContext
    @DisplayName("")
    void whenDeleteAssignment_shouldDeleteAssignment(){
        assignTask();
        assertEquals(1, assignmentRepository.findAll().size());
        assignmentRepository.deleteAssignment(666L, 1L);
        assertEquals(0, assignmentRepository.findAll().size());
    }

    void populateTasks(){
        taskRepository.save(new TaskEntity());
    }

    void assignTask(){
        populateTasks();
        assignmentRepository.save(AssignmentEntity.builder()
                .task(taskRepository.getReferenceById(1L))
                .userId(666L)
                .deadline(LocalDate.now())
                .assignedOn(LocalDate.now())
                .build());
    }

}
