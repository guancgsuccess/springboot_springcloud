package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/3/14 0014.
 */
@Component
@RabbitListener(queues = "success")
public class CustomerThree {
    @RabbitHandler
    public void showMessage(String msg){
        System.out.println("success:"+msg);
    }
}
