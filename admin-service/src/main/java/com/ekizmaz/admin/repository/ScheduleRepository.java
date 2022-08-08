package com.ekizmaz.admin.repository;

import com.ekizmaz.admin.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
