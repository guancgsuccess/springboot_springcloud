package com.tensquare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TensquareRbmApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 直接模式
	 */
//	@Test
//	public void contextLoads1() {
//		rabbitTemplate.convertAndSend("aistar","我是直接模式!");
//	}

	/**
	 * 分列模式
	 */
//	@Test
//	public void contextLoads2() {
//		rabbitTemplate.convertAndSend("guan","","分裂模式");
//	}

	/**
	 * 主题模式
	 */
	@Test
	public void contextLoads3() {
		rabbitTemplate.convertAndSend("topic4","good.log","主题模式");
	}

}
