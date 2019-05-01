package com.kevin.wxservice;

import com.kevin.wxservice.service.WxAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxserviceApplicationTests {

    @Autowired
    private WxAccountService wxAccountService;

    @Test
    public void contextLoads() {

    }

}
