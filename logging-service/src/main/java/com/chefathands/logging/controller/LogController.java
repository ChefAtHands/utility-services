package com.chefathands.logging.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.chefathands.logging.service.LogService;
import com.chefathands.logging.model.LogEntry;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final LogService svc;
    public LogController(LogService svc) { this.svc = svc; }

    @GetMapping
    public List<LogEntry> all() { return svc.list(); }

    @PostMapping
    public LogEntry add(@RequestBody LogEntry e) { return svc.save(e); }
}