package com.ekizmaz.ticket.client;

import com.ekizmaz.ticket.dto.ScheduleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "scheduleService", url = "http://localhost:8088")
public interface ScheduleServiceClient {
    @RequestMapping("/schedule/{id}")
    ResponseEntity<ScheduleDto> get(@PathVariable("id") Long id);
}
