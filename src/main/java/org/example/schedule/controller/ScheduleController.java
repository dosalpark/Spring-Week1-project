package org.example.schedule.controller;

import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    //공통으로 사용할 Service 필드 선언 및 생성자
    final private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //일정 등록
    @PostMapping("/schedule")
    //등록된 하나의 값만 전달해줄거기 때문에 @Pathvariable 사용
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto){
        //서비스로 넘겨줘야 함
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    //선택 일정 조회
    //여러 값이 나올 수 있어서 @RequestParam 사용
    @GetMapping("/schedule/select")
    public List<ScheduleResponseDto> getScheduleByUser(@RequestParam String username){
        return scheduleService.getScheduleByUser(username);

    }

    //모든 일정 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule(){
        return scheduleService.getSchedule();

    }
    //선택 일정 수정
    // 스케쥴의 값을 전달하고싶은데 일단 ID로 대기중
    // 하나의 값만 전달해줄거기 때문에 @Pathvariable 사용
    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable Long id, ScheduleRequestDto scheduleRequestDto){
        return scheduleService.updateSchedule(id, scheduleRequestDto);
    }

    //선택 일정 삭제
    //하나의 값만 전달해줄거기 때문에 @Pathvariable 사용
    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id){
        System.out.println("삭제 되었습니다.");
        return scheduleService.deleteSchedule(id);
    }
}
