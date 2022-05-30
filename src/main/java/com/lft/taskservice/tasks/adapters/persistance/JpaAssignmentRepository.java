package com.lft.taskservice.tasks.adapters.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentEntity, Long> {

}
