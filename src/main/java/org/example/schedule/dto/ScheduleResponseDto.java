package org.example.schedule.dto;

import lombok.Getter;
import org.example.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String titleSchedule;
    private String bodySchedule;
    private String user;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.titleSchedule = schedule.getTitleSchedule();
        this.bodySchedule = schedule.getBodySchedule();
        this.user = schedule.getUser();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
