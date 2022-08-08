package com.ekizmaz.ticket.service;

import com.ekizmaz.ticket.client.ScheduleServiceClient;
import com.ekizmaz.ticket.client.UserServiceClient;
import com.ekizmaz.ticket.dto.ScheduleDto;
import com.ekizmaz.ticket.dto.TicketDto;
import com.ekizmaz.ticket.dto.TicketSaveDto;
import com.ekizmaz.ticket.dto.UserDto;
import com.ekizmaz.ticket.entity.Ticket;
import com.ekizmaz.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserServiceClient userServiceClient;
    private final ScheduleServiceClient scheduleServiceClient;
    private final ModelMapper modelMapper;

    public TicketDto get(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        TicketDto ticketDto=modelMapper.map(ticket, TicketDto.class);
        UserDto userDto =userServiceClient.get(ticket.getUser_id()).getBody();
        ScheduleDto scheduleDto=scheduleServiceClient.get(ticket.getSchedule_id()).getBody();
        ticketDto.setUserName(userDto.getName()+" "+ userDto.getSurname());
        ticketDto.setPlan(scheduleDto.getStartingPoint()+" -> "+ scheduleDto.getDestination());
        //Admin'den yolculuk planı gelicek Source - Destination
        return ticketDto;
    }

    @Transactional
    public TicketDto save(TicketSaveDto ticketSaveDto) {
        Ticket ticket = modelMapper.map(ticketSaveDto, Ticket.class);
        ticket = ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketDto.class);
    }

/*    @Transactional
    public AccountDto update(String id, AccountDto accountDto) {
        Assert.isNull(id, "Id cannot be null");
        Optional<Account> account = accountRepository.findById(id);
        Account accountToUpdate = account.map(it -> {
            it.setName(accountDto.getName());
            it.setSurname(accountDto.getSurname());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(accountRepository.save(accountToUpdate), AccountDto.class);
    }*/

    @Transactional
    public void delete(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        ticketRepository.delete(ticket);
    }

    public List<TicketDto> findAll(Pageable pageable) {
        List<TicketDto> ticketDtoList=ticketRepository.findAll(pageable)
                .stream()
                .map(user -> modelMapper.map(user, TicketDto.class))
                .collect(Collectors.toList());
        return ticketDtoList;
    }
}
