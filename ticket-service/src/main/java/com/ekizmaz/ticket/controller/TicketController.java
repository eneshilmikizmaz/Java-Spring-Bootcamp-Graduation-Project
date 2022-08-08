package com.ekizmaz.ticket.controller;

import com.ekizmaz.ticket.dto.TicketDto;
import com.ekizmaz.ticket.dto.TicketSaveDto;
import com.ekizmaz.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ticketService.get(id));
    }
    @PostMapping
    public ResponseEntity<TicketDto> post(@RequestBody TicketSaveDto ticketSaveDto) {
        return ResponseEntity.ok(ticketService.save(ticketSaveDto));
    }
}
