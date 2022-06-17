package com.lft.taskservice.tasks.adapters.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentEntity, Long> {

    @Query("select a from AssignmentEntity a where a.userId=:userId")
    List<AssignmentEntity> getAllUserAssignments(Long userId);

    @Modifying
    @Query("update AssignmentEntity a set a.deadline = :deadline where a.task.id=:taskId and a.userId = :userId")
    void updateDeadline(@Param("deadline") LocalDateTime deadline, @Param("taskId") Long taskId, @Param("userId") Long userId);

    @Modifying
    @Query("delete from AssignmentEntity a where a.task.id = :taskId and a.userId = :userId")
    void deleteAssignment(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Query("select a from AssignmentEntity a where a.task.id=:taskId and a.userId=:userId ")
    AssignmentEntity findByUserIdAndTaskId(Long userId, Long taskId);

    @Modifying
    @Query("delete from AssignmentEntity a where a.task.id = :taskId")
    void deleteAllAssignmentsAssociatedWithTaskId(@Param("taskId") Long taskId);

    @Query("select a.userId from AssignmentEntity a where a.task.id =:taskId")
    List<Long> getAllUsersIdsAssignedToTask(Long taskId);
}
