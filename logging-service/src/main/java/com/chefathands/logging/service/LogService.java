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
    public List<LogEntry> listByLevel(String level) {return repo.findByLevel(level);}
    public LogEntry save(LogEntry e) { return repo.save(e); }
    public LogEntry logInfo(String message) { return repo.save(new LogEntry("INFO", message));}
    public LogEntry logWarn(String message) { return repo.save(new LogEntry("WARN", message));}
    public LogEntry logError(String message) { return repo.save(new LogEntry("ERROR", message));}
}