package com.dongkap.feign.dto.notification;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SubscriptionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 954468395568766465L;
	private String endpoint;
	private Long expirationTime;
	private KeysDto keys;

}
