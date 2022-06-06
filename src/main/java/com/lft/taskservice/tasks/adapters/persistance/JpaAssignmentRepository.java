package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentEntity, Long> {

    @Query("select a.task from AssignmentEntity a where a.userId=:userId")
    List<TaskEntity> findAllTasksAssignedToUser(Long userId);

    @Modifying
    @Query("update AssignmentEntity a set a.deadline = :deadline where a.task.id=:taskId and a.userId = :userId")
    void updateDeadline(@Param("deadline") LocalDate deadline, @Param("taskId") Long taskId, @Param("userId") Long userId);

    @Modifying
    @Query("delete from AssignmentEntity a where a.task.id = :taskId and a.userId = :userId")
    void deleteAssignment(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Query("select a from AssignmentEntity a where a.task.id=:taskId and a.userId=:userId ")
    AssignmentEntity findByUserIdAndTaskId(Long userId, Long taskId);
}
