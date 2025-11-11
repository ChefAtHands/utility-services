package com.chefathands.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chefathands.logging.model.LogEntry;

public interface LogRepository extends JpaRepository<LogEntry, Long> {}