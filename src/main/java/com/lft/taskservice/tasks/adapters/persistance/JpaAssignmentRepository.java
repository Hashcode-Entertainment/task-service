package com.lft.taskservice.tasks.adapters.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentEntity, Long> {

    @Query("select a.taskId from AssignmentEntity a where a.userId=:userId")
    List<Long> findAllTasksIdByUserId(Long userId);

    @Modifying
    @Query("update AssignmentEntity a set a.deadline = :deadline where a.taskId=:taskId and a.userId = :userId")
    void updateDeadline(@Param("deadline") LocalDate deadline, @Param("taskId") Long taskId, @Param("userId") Long userId);

}
