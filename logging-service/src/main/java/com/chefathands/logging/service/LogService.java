package com.chefathands.logging.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.chefathands.logging.repository.LogRepository;
import com.chefathands.logging.model.LogEntry;

@Service
public class LogService {
    private final LogRepository repo;
    public LogService(LogRepository repo) { this.repo = repo; }
    public List<LogEntry> list() { return repo.findAll(); }
    public LogEntry save(LogEntry e) { return repo.save(e); }
}