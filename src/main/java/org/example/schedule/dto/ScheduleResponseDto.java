package org.example.schedule.dto;

import lombok.Getter;
import org.example.schedule.entity.Schedule;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String titleSchedule;
    private String bodySchedule;
    private String user;

    public ScheduleResponseDto(Long id, String titleSchedule, String bodySchedule, String user) {
        this.id = id;
        this.titleSchedule = titleSchedule;
        this.bodySchedule = bodySchedule;
        this.user = user;
    }

    public ScheduleResponseDto(Schedule addSchedule) {
        this.id = addSchedule.getId();
        this.titleSchedule = addSchedule.getTitleSchedule();;
        this.bodySchedule = addSchedule.getBodySchedule();;
        this.user = addSchedule.getUser();
    }
}
