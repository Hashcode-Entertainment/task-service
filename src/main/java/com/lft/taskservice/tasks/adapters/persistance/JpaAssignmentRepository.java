package com.lft.taskservice.tasks.adapters.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentEntity, Long> {

    @Query("select a.taskId from AssignmentEntity a where a.userId=:userId")
    List<Long> findAllTasksIdByUserId(Long userId);

}
