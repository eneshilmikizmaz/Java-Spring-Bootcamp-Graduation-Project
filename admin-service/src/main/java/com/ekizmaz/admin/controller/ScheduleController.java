package com.ekizmaz.admin.controller;

import com.ekizmaz.admin.dto.ScheduleDto;
import com.ekizmaz.admin.dto.ScheduleSaveDto;
import com.ekizmaz.admin.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(scheduleService.get(id));
    }
    @PostMapping
    public ResponseEntity<ScheduleDto> post(@RequestBody ScheduleSaveDto scheduleSaveDto) {
        return ResponseEntity.ok(scheduleService.save(scheduleSaveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> post(@PathVariable("id") Long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok("Ok");
    }

}
