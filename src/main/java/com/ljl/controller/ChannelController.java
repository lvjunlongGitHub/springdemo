package com.ljl.controller;


import com.ljl.entity.Channel;
import com.ljl.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 渠道表 前端控制器
 * </p>
 *
 * @author lvjunlong
 * @since 2019-08-20
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping("getChannel")
    Channel getChannel(Integer channelId) {
        return channelService.getChannel(channelId);
    }

}

