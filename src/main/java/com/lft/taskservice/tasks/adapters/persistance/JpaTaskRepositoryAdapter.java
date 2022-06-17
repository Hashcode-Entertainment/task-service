package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Task;
import com.lft.taskservice.tasks.ports.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Repository
@RequiredArgsConstructor
public class JpaTaskRepositoryAdapter implements TaskRepository {

    private final JpaTaskRepository taskRepository;
    private final JpaTaskMapper taskMapper;

    @Override
    public List<Task> findAll() {
        var taskEntities = taskRepository.findAll();
        return taskMapper.mapList(taskEntities, Task.class);
    }

    @TaskLogging
    @Override
    public Task save(Task task) {
        var taskEntity = taskMapper.toEntity(task);
        var savedTaskEntity = taskRepository.save(taskEntity);
        return taskMapper.toDomain(savedTaskEntity);
    }

    @TaskLogging
    @Override
    public Task findById(Long id) {
      var taskEntity = Optional.of(taskRepository.findById(id).get()).orElseThrow(NoSuchElementException::new);
      return taskMapper.toDomain(taskEntity);
    }

    @TaskLogging
    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
