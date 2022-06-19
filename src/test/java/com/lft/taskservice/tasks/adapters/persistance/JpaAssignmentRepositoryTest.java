package com.lft.taskservice.tasks.adapters.persistance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaAssignmentRepositoryTest {

    public static final LocalDateTime DEADLINE = LocalDateTime.now();
    public static final LocalDateTime ASSIGNED_ON = LocalDateTime.now();

    @Autowired
    JpaAssignmentRepository assignmentRepository;

    @Autowired
    JpaTaskRepository taskRepository;

    @DisplayName("Empty database is initialized. Should return no records")
    @Test
    void shouldReturnNoRecords() {
        assertEquals(0, assignmentRepository.getAllUserAssignments(1L).size());
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
                        .deadline(DEADLINE)
                        .assignedOn(ASSIGNED_ON)
                        .build());
        assertEquals(1, assignmentRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    @DisplayName("Should return all tasks assigned to a user")
    void shouldReturnAllTasksAssignedToUser() {
        assignTask();
        assertEquals(1, assignmentRepository.getAllUserAssignments(666L).size());
    }

    @Test
    @DirtiesContext
    @DisplayName("Deleting saved assignment")
    void whenDeleteAssignment_shouldDeleteAssignment(){
        assignTask();
        assertEquals(1, assignmentRepository.findAll().size());
        assignmentRepository.deleteAssignment(666L, 1L);
        assertEquals(0, assignmentRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    @DisplayName("Should find assignment if valid userId and taskId are given")
    void whenValidTaskIdAndUserId_shouldFindAssignment(){
        assignTask();
        assertEquals(ASSIGNED_ON, assignmentRepository.findByUserIdAndTaskId(666L, 1L).getAssignedOn());
    }

    @Test
    @DirtiesContext
    @DisplayName("Should delete all assignments associated with task if valid taskId is given")
    void whenValidTaskId_deleteAllAssociatedAssignments(){
        for (int i = 3; i > 0; i--){
            assignTask();
        }
        assertEquals(3, assignmentRepository.getAllUserAssignments(666L).size());
        assignmentRepository.deleteAllAssignmentsAssociatedWithTaskId(1L);
        assertEquals(0, assignmentRepository.getAllUserAssignments(666L).size());
    }

    //////////////////////////////////////////////////////  UTILS ////////////////////////////////////////////////////

    void populateTasks(){
        taskRepository.save(new TaskEntity());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
        System.out.println(taskRepository.findAll());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
    }

    void assignTask(){
        populateTasks();
        assignmentRepository.save(AssignmentEntity.builder()
                .task(taskRepository.getReferenceById(1L))
                .userId(666L)
                .deadline(DEADLINE)
                .assignedOn(ASSIGNED_ON)
                .build());
    }

}
