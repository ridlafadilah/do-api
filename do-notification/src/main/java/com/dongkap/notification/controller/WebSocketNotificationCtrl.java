package com.dongkap.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.aspect.ResponseSuccess;
import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.common.utils.SuccessCode;
import com.dongkap.notification.service.WebSocketNotificationService;

@RequestMapping("/api/notification")
@RestController
public class WebSocketNotificationCtrl extends BaseControllerException {

    @Autowired
    private WebSocketNotificationService service;

	@ResponseSuccess(SuccessCode.OK_SCR001)
    @RequestMapping(value = "/trx/post/broadcast/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendBroadcast(@RequestBody String notification) throws Exception {
		service.broadcast(notification);
		return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
