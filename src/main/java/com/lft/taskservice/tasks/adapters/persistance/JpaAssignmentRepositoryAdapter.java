package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.ports.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class JpaAssignmentRepositoryAdapter implements AssignmentRepository {

    private final JpaAssignmentRepository assignmentRepository;
    private final JpaAssignmentMapper assignmentMapper;
    private final JpaTaskMapper taskMapper;

    @Override
    public void deleteAllAssignmentsToGivenTask(Long taskId) {
        assignmentRepository.deleteAllAssignmentsAssociatedWithTaskId(taskId);
    }

    @TaskLogging
    @Override
    public Assignment assignTaskToUser(Assignment assignment) {
        var assignmentEntity = assignmentMapper.toEntity(assignment);
        var savedAssignmentEntity = assignmentRepository.save(assignmentEntity);
        return assignmentMapper.toDomain(savedAssignmentEntity);
    }

    @Override
    public List<Long> getAllUsersIdsAssignedToTask(Long taskId) {
        return assignmentRepository.getAllUsersIdsAssignedToTask(taskId);
    }

    @TaskLogging
    @Override
    public List<Assignment> getAllUserAssignments(Long userId) {
        return assignmentMapper.mapList(assignmentRepository.getAllUserAssignments(userId), Assignment.class);
    }

    @Override
    public void changeDeadline(Long userId, Long taskId, LocalDate deadline) {
        assignmentRepository.updateDeadline(deadline, taskId, userId);
    }

    @Override
    public void deleteAssignment(Long userId, Long taskId) {
        assignmentRepository.deleteAssignment(userId, taskId);
    }

    @Override
    public Assignment getInfoOnAssignment(Long userId, Long taskId) {
        return assignmentMapper.toDomain(assignmentRepository.findByUserIdAndTaskId(userId, taskId));
    }
}
