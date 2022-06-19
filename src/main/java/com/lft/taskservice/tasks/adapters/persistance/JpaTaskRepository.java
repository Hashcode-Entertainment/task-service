package com.lft.taskservice.tasks.adapters.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> { }
