package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.domain.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaTaskRepositoryTest {

    @Autowired
    private JpaTaskRepository taskRepository;

    private JpaTaskMapper taskMapper = new JpaTaskMapper();

    @Test
    @DisplayName("Should update existing task")
    void whenSaveAndTaskAlreadyExists_thenUpdateExistingTask() {
        //Given
        var savedTask = Task.builder()
                .id(1L)
                .name("Before update")
                .build();
        taskRepository.save(taskMapper.toEntity(savedTask));
        var updatedTask = Task.builder()
                .id(1L)
                .name("After update")
                .build();
        //When
        taskRepository.save(taskMapper.toEntity(updatedTask));
        var taskFromRepository = taskRepository.findById(1L);
        //Then
        assertEquals("After update", taskFromRepository.get().getName());
    }

}