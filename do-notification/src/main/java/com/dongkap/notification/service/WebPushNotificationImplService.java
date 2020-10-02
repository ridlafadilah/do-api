package com.dongkap.notification.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dongkap.feign.dto.notification.NotificationPayloadDto;
import com.dongkap.feign.dto.notification.PushNotificationDto;
import com.dongkap.feign.dto.notification.SubscriptionDto;
import com.dongkap.feign.service.WebPushNotificationService;
import com.dongkap.notification.dao.SubscriptionRepo;
import com.dongkap.notification.entity.SubscriptionEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@Service("webPushNotificationService")
public class WebPushNotificationImplService implements WebPushNotificationService {
	
	@Value("${do.vapid.private-key}")
	private String privateKey;
	
	@Value("${do.vapid.public-key}")
	private String publicKey;
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;

	@Autowired
	private ObjectMapper objectMapper;
	
	private static PushService pushService = new PushService();
    private static final int ONE_DAY_DURATION_IN_SECONDS = 86400;
    private static Long DEFAULT_TTL = new Long(28) * ONE_DAY_DURATION_IN_SECONDS;
    
    @PostConstruct
    public void initKeys() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		pushService.setPublicKey(publicKey);
		pushService.setPrivateKey(privateKey);    	
    }

	public void subscribe(SubscriptionDto subscription, String username) {
		SubscriptionEntity subs = subscriptionRepo.findByEndpoint(subscription.getEndpoint());
		if(subs == null) {
			subs = new SubscriptionEntity();
			subs.setEndpoint(subscription.getEndpoint());
			subs.setExpirationTime(DEFAULT_TTL);
			subs.setP256dh(subscription.getKeys().getP256dh());
			subs.setAuth(subscription.getKeys().getAuth());
			subs.setUsername(username);
			subscriptionRepo.save(subs);
		}
	}

	public void notify(PushNotificationDto message, String username) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		List<SubscriptionEntity> subs = subscriptionRepo.findByUsername(message.getTo());
		for (SubscriptionEntity subscription: subs) {
			NotificationPayloadDto payload = new NotificationPayloadDto();
			payload.getNotification().setTitle(message.getTitle());
			payload.getNotification().setBody(message.getBody());
			payload.getNotification().setTag(message.getTag());
			payload.getNotification().setIcon(message.getIcon());
			payload.getNotification().setData(objectMapper.writeValueAsString(message.getData()));
			Notification notification = new Notification(
					subscription.getEndpoint(),
					subscription.getP256dh(),
					subscription.getAuth(),
					objectMapper.writeValueAsBytes(payload));
			pushService.sendAsync(notification);
		};
	}

}
