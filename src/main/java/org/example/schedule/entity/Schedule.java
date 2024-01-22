package org.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.schedule.dto.ScheduleRequestDto;

import java.security.PrivateKey;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class Schedule extends Timestemped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String titleSchedule;

    @Column(nullable = false, length = 500)
    private String bodySchedule;

    @Column(nullable = false, length = 500)
    private String user;

    @Column(nullable = false, length = 500)
    private String pw;

    public Schedule (ScheduleRequestDto scheduleRequestDto){
        this.titleSchedule = scheduleRequestDto.getTitleSchedule();
        this.bodySchedule = scheduleRequestDto.getBodySchedule();
        this.user = scheduleRequestDto.getUser();
        this.pw = scheduleRequestDto.getPw();

    }

    public void update(ScheduleRequestDto scheduleRequestDto){
        this.titleSchedule = scheduleRequestDto.getTitleSchedule();
        this.bodySchedule = scheduleRequestDto.getBodySchedule();
        this.user = scheduleRequestDto.getUser();
        this.pw = scheduleRequestDto.getPw();
    }


}
