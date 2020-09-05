package com.qxy;

import com.qxy.aware.MessageSendPublish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventPublishTest {

    @Autowired
    MessageSendPublish messageSendPublish;

    @Test
    public void test() {
        messageSendPublish.sendMessage("112345");
    }
}
