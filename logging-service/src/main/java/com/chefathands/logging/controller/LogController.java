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

    @GetMapping("/level/{level}")
    public List<LogEntry> byLevel(@PathVariable String level) { return svc.listByLevel(level);}

    @PostMapping("/info")
    public LogEntry logInfo(@RequestBody String message) { return svc.logInfo(message);}

    @PostMapping("/warn")
    public LogEntry logWarn(@RequestBody String message) { return svc.logWarn(message);}

    @PostMapping("/error")
    public LogEntry logError(@RequestBody String message) { return svc.logError(message);}
}