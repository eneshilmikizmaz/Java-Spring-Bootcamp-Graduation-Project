package com.ekizmaz.notification.listener;

import com.ekizmaz.notification.dto.EmailDto;
import com.ekizmaz.notification.dto.SmsDto;
import com.ekizmaz.notification.entity.Email;
import com.ekizmaz.notification.entity.Sms;
import com.ekizmaz.notification.repository.EmailRepository;
import com.ekizmaz.notification.repository.SmsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

    private final EmailRepository emailRepository;
    private final SmsRepository smsRepository;
    private final ModelMapper modelMapper;

    @RabbitListener(queues = "ticket.notification")
    public void notificationListener(Message notification) {
        var type=notification.getMessageProperties().getHeaders().get("__TypeId__");
        if(type.equals("com.ekizmaz.user.dto.EmailDto"))
        {
            try {
                String str = new String(notification.getBody());
                EmailDto emailDto = new ObjectMapper().readValue(str,EmailDto.class);
                log.info(" email sent to " + emailDto.getEmail());
                emailRepository.save(modelMapper.map(emailDto, Email.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                SmsDto smsDto = new ObjectMapper().readValue(new String(notification.getBody()),SmsDto.class);
                log.info(" sms sent to " + smsDto.getPhoneNumber());
                smsRepository.save(modelMapper.map(smsDto, Sms.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
