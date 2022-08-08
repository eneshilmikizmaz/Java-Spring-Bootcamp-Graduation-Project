package com.ekizmaz.payment.service;

import com.ekizmaz.payment.client.ScheduleServiceClient;
import com.ekizmaz.payment.client.TicketServiceClient;
import com.ekizmaz.payment.client.UserServiceClient;
import com.ekizmaz.payment.dto.*;
import com.ekizmaz.payment.entity.Payment;
import com.ekizmaz.payment.dto.TicketSaveDto;
import com.ekizmaz.payment.enums.FirmType;
import com.ekizmaz.payment.enums.Gender;
import com.ekizmaz.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserServiceClient userServiceClient;
    private final ScheduleServiceClient scheduleServiceClient;
    private final TicketServiceClient ticketServiceClient;
    private final ModelMapper modelMapper;


    public PaymentDto get(Long id) {
        Payment payment = paymentRepository.findById(id).get();
        return modelMapper.map(payment,PaymentDto.class);
    }
    public List<PaymentDto> save(PaymentSaveDto paymentSaveDto) throws Exception {
        UserDto userDto=userServiceClient.get(paymentSaveDto.getUserId()).getBody();
        if(userDto.getFirmType()== FirmType.INDIVIDUAL)
        {
            if(paymentSaveDto.getPassengerList().stream().filter(p->p.getGender()== Gender.MALE).count()>2)
                throw new Exception("INDIVIDUAL 2 MALE person rule denied");

            List<Payment> payments= paymentRepository.findByUserId(paymentSaveDto.getUserId()).getContent();
            if((paymentSaveDto.getPassengerList().size()+payments.size())>5)
                throw new Exception("INDIVIDUAL 5 person rule denied");
        }
        else if(userDto.getFirmType()==FirmType.CORPORATE)
        {
            List<Payment> payments= paymentRepository.findByUserId(paymentSaveDto.getUserId()).getContent();
            if((paymentSaveDto.getPassengerList().size()+payments.size())>20)
                throw new Exception("INDIVIDUAL 5 person rule denied");
        }
        else {
            throw new Exception("Firmtype problem");
        }
        ScheduleDto scheduleDto = scheduleServiceClient.get(paymentSaveDto.getSchedule_id()).getBody();

        List<PaymentDto> payments = paymentSaveDto.getPassengerList().stream().map(p->{
            TicketSaveDto ticket=new TicketSaveDto();
            ticket.setGender(p.getGender());
            ticket.setFareAmount(scheduleDto.getFareAmount());
            ticket.setDateOfBooking(scheduleDto.getScheduleDate());
            ticket.setNumberOfSeat(p.getNumberOfSeat());
            ticket.setSchedule_id(paymentSaveDto.getSchedule_id());
            ticket.setUser_id(paymentSaveDto.getUserId());
            TicketDto ticketDto=ticketServiceClient.save(ticket).getBody();
            Payment payment=new Payment();
            payment.setTicketId(ticketDto.getId());
            payment.setPaymentDate(new Date());
            payment.setNumberOfSeat(p.getNumberOfSeat());
            payment.setAmountPaid(scheduleDto.getFareAmount());
            payment.setPaymentType(paymentSaveDto.getPaymentType());
            payment.setUserId(userDto.getId());
            return modelMapper.map( paymentRepository.save(payment), PaymentDto.class);
        }).collect(Collectors.toList());

        return payments;
    }
}
