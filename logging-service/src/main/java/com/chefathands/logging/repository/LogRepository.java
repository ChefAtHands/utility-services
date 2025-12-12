package com.chefathands.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chefathands.logging.model.LogEntry;
import java.util.List;

public interface LogRepository extends JpaRepository<LogEntry, Long> {
    List<LogEntry> findByLevel(String level);
}