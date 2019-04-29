package com.tensquare.sms.listener;

import com.tensquare.sms.util.SMSCode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListner {

    @RabbitHandler
    public void sendSms(Map<String,String> map){
        System.out.println("手机号码:"+map.get("mobile"));
        System.out.println("验证码:"+map.get("checkcode"));
        try {
            SMSCode.sendCode(map.get("mobile"),map.get("checkcode"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
