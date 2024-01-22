package org.example.schedule.service;

import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    //공통으로 사용 할 Repository 필드 선언 및 생성자 생성
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //일정 등록(완료?)
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        //Schedule Entity에 내용을 담아서 Repo에 전달
        Schedule schedule = new Schedule(scheduleRequestDto);
        //Repository로 전달?
        Schedule addSchedule = scheduleRepository.save(schedule);
        //Controller에게 생성한 일정을 ScheduleResponseDto에 담아서 Controller로 전달
//        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(addSchedule);
//        return scheduleResponseDto;
        return new ScheduleResponseDto(addSchedule);
    }

    //선택한 일정만 조회
    public List<ScheduleResponseDto> getScheduleByUser(String username) {
        //Controller에서 작성한 메소드쿼리로 리스트 만들어서 controller에 전달
        return scheduleRepository.findAllByUserContainsOrderByCreatedAtDesc(username).stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    //모든 일정 조회
    public List<ScheduleResponseDto> getSchedule() {
        //Controller에게 모든 일정 전달
//        return scheduleRepository.findAllOrOrderByCreatedAt().stream().
        return scheduleRepository.findAll().stream().
                map(ScheduleResponseDto::new).
                toList();
    }

    //단건 일정 조회
    public ScheduleResponseDto getChoiceSchedule(Long id) {
        //새로운 Entity 생성
        Schedule schedule = findSchedule(id);
        return new ScheduleResponseDto(schedule);
    }

    //선택 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        //입력한 아이디값 기준으로 수정해서 전달.
        //스케쥴 생성하면서 유저가 선택한 메모를 인스턴스에 저장
        Schedule schedule = findSchedule(id);
        //pw가 null,미일치 확인
        Schedule pwCheckSchedule = pwCheck(scheduleRequestDto, schedule);
        //인스턴스에 저장한 정보를 유저가 입력한 변경한 정보로 변경
        pwCheckSchedule.update(scheduleRequestDto);
//            schedule.setTitleSchedule(scheduleRequestDto.getTitleSchedule());
//            schedule.setBodySchedule(scheduleRequestDto.getBodySchedule());
//            schedule.setUser(scheduleRequestDto.getUser());

            //Controller에게 수정된 일정만 전달.
            return new ScheduleResponseDto(pwCheckSchedule);
//        }
//        return
    }

    //선택 일정 삭제
    public Long deleteSchedule(Long id) {
        //Controller에게 삭제한 값만 전달
        //일단 스케쥴 생성하면서 데이터 찾아옴
        Schedule schedule = findSchedule(id);
        //remove?
        scheduleRepository.delete(schedule);
        return id;
    }


    private Schedule findSchedule(Long id){
        Schedule schedule = new Schedule();
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 스케쥴은 등록되어있지 않습니다."));
    }

    private Schedule pwCheck(ScheduleRequestDto scheduleRequestDto, Schedule schedule){
        //패스워드 미입력시 Exception
        if (scheduleRequestDto.getPw() == null){
            throw new NullPointerException("패스워드가 입력되지 않았습니다.");
        }
        //패스워드 일치하지 않을시 Exception
        if (!scheduleRequestDto.getPw().equals(schedule.getPw())){
            throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
        }
        return schedule;
    }
}

