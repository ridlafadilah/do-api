package com.dongkap.feign.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;

import com.dongkap.feign.dto.notification.PushNotificationDto;
import com.dongkap.feign.dto.notification.SubscriptionDto;

public interface WebPushNotificationService {
	
	public void subscribe(SubscriptionDto subscription, String username);
	
	public void notify(PushNotificationDto message, String username) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException;

}
