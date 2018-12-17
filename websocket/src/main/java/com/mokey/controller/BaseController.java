package com.mokey.controller;

import com.mokey.bean.RequestBody;
import com.mokey.bean.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangjianwei
 * @title
 * @Description: <br>
 * <br>
 * @Company: 互动在线
 * @Created on 2018/12/17.
 */
@Controller
public class BaseController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public BaseController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @MessageMapping("/say")
    @SendTo("/topic/show")
    public ResponseBody say(RequestBody requestBody){
        ResponseBody responseBody= new ResponseBody();
        responseBody.setMessage("你好："+requestBody.getParameter());
        return responseBody;
    }

    @Scheduled(fixedRate = 1000)
    public void callback(){
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        simpMessagingTemplate.convertAndSend("/topic/callback","定时推送消息时间："+df.format(new Date()));
    }


}
