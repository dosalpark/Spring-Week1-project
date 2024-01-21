package org.example.schedule.repository;

import org.example.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    List<Schedule> findAllOrOrderByCreatedAt();
    List<Schedule> findAllByUserContainsOrderByCreatedAtDesc(String username);

}
