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

    void populateTasks(){
        taskRepository.save(new TaskEntity());
    }

}
