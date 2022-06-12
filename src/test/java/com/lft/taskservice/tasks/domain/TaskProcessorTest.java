package com.lft.taskservice.tasks.domain;

import com.lft.taskservice.tasks.ports.AssignmentRepository;
import com.lft.taskservice.tasks.ports.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskProcessorTest {

    public static final long TASK_ID_1 = 1L;

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AssignmentRepository assignmentRepository;
    @InjectMocks
    private TaskProcessor taskProcessor;

    @Test
    void whenSave_thenSavedTaskIdEquals1() {
        //Given
        var task = new Task();

        var savedTask = Task.builder()
                .id(TASK_ID_1)
                .build();

        //When
        when(taskRepository.save(task)).thenReturn(savedTask);
        var processedTask = taskProcessor.save(task);

        //Then
        assertEquals(TASK_ID_1, processedTask.getId());
    }

    @Test
    void whenFindById_thenFoundTaskWithGivenId() {
        //Given
        var task = Task.builder()
                .id(TASK_ID_1)
                .build();

        //When
        when(taskRepository.findById(TASK_ID_1)).thenReturn(task);
        var processedTask = taskProcessor.findTaskById(TASK_ID_1);

        //Then
        assertEquals(TASK_ID_1, processedTask.getId());
    }
}
