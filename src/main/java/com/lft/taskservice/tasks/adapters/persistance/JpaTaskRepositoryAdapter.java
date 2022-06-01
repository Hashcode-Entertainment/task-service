package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;
import com.lft.taskservice.tasks.ports.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Repository
@RequiredArgsConstructor
public class JpaTaskRepositoryAdapter implements TaskRepository {

    private final JpaTaskRepository taskRepository;
    private final JpaTaskMapper taskMapper;
    private final JpaAssignmentRepository assignmentRepository;
    private final JpaAssignmentMapper assignmentMapper;

    @TaskLogging
    public Task save(Task task) {
        var taskEntity = taskMapper.toEntity(task);
        var savedTaskEntity = taskRepository.save(taskEntity);
        return taskMapper.toDomain(savedTaskEntity);
    }

    @TaskLogging
    public Task findById(Long id) {
      var taskEntity = Optional.of(taskRepository.findById(id).get()).orElseThrow(NoSuchElementException::new);
      return taskMapper.toDomain(taskEntity);
    }

    @TaskLogging
    public Assignment assignTaskToUser(Assignment assignment) {
        var assignmentEntity = assignmentMapper.toEntity(assignment);
        var savedAssignmentEntity = assignmentRepository.save(assignmentEntity);
        return assignmentMapper.toDomain(savedAssignmentEntity);
    }

    @TaskLogging
    @Override
    public List<Task> getAllTasksAssignedToUser(Long userId) {
        return taskMapper.mapList(assignmentRepository.findAllTasksAssignedToUser(userId), Task.class);
    }

    @Override
    public void changeDeadline(Long userId, Long taskId, LocalDate deadline) {
        assignmentRepository.updateDeadline(deadline, taskId, userId);
    }

    @Override
    public void deleteAssignment(Long userId, Long taskId) {
        assignmentRepository.deleteAssignment(userId, taskId);
    }

}
