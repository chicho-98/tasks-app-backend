package com.chicho.tasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chicho.tasks.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
