package com.kaiburr.task1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskExecution {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String output;
    private Integer exitCode;

    public TaskExecution(LocalDateTime startTime, LocalDateTime endTime, String output) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.output = output;
        this.exitCode = 0;
    }
}