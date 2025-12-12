package com.chefathands.logging.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "log_entry")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String level;
    private String message;
    @Column(name = "created", columnDefinition = "datetime2")
    private Instant created = Instant.now();

    public LogEntry() {}
    public LogEntry(String level, String message) { this.level = level; this.message = message; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Instant getCreated() { return created; }
    public void setCreated(Instant created) { this.created = created; }
}