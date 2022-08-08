package com.ekizmaz.admin.service;

import com.ekizmaz.admin.dto.ScheduleDto;
import com.ekizmaz.admin.dto.ScheduleSaveDto;
import com.ekizmaz.admin.entity.Schedule;
import com.ekizmaz.admin.enums.Vehicle;
import com.ekizmaz.admin.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    public ScheduleDto get(Long id) {
        return modelMapper.map(scheduleRepository.findById(id),ScheduleDto.class);
    }
    public ScheduleDto save(ScheduleSaveDto scheduleSaveDto) {
        Schedule schedule =modelMapper.map(scheduleSaveDto,Schedule.class);
        if(schedule.getVehicle()==Vehicle.BUS)
            schedule.setCapacity(45);
        else if(schedule.getVehicle()==Vehicle.PLANE)
            schedule.setCapacity(120);
        schedule=scheduleRepository.save(schedule);
        return modelMapper.map(schedule,ScheduleDto.class);
    }
    public ScheduleDto update(Long id) {
        return new ScheduleDto();
    }
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        scheduleRepository.delete(schedule);
    }
}
