package com.ekizmaz.payment.client;

import com.ekizmaz.payment.dto.TicketDto;
import com.ekizmaz.payment.dto.TicketSaveDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ticketService", url = "http://localhost:8088")
public interface TicketServiceClient {
    @RequestMapping("/ticket/{id}")
    ResponseEntity<TicketDto> get(@PathVariable("id") Long id);
    @RequestMapping(method = RequestMethod.POST,path = "/ticket")
    ResponseEntity<TicketDto> save(@RequestBody TicketSaveDto ticketSaveDto);
}
