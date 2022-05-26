package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.domain.Task;
import com.lft.taskservice.tasks.ports.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
@RequiredArgsConstructor
public class JpaTaskRepositoryAdapter implements TaskRepository {

    private final JpaTaskRepository taskRepository;
    private final JpaTaskMapper taskMapper;

    public Task save(Task task) {
        var taskEntity = taskMapper.toEntity(task);
        var savedTaskEntity = taskRepository.save(taskEntity);
        return taskMapper.toDomain(savedTaskEntity);
    }
}
