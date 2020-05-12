package com.dongkap.notification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dongkap.common.entity.BaseAuditEntity;
import com.dongkap.common.utils.SchemaDatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString()
@Entity
@Table(name = "notif_subscription", schema = SchemaDatabase.NOTIFICATION)
public class SubscriptionEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 954468395568766465L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "notif_subscription_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "endpoint", nullable = false, unique = true)
	private String endpoint;

	@Column(name = "expiration_time")
	private Long expirationTime;

	@Column(name = "p256dh", nullable = false)
	private String p256dh;

	@Column(name = "auth", nullable = false)
	private String auth;

	@Column(name = "platform")
	private String platform;

	@Column(name = "user_name", nullable = false)
	private String username;

	@Column(name = "subscribed", nullable = false)
	private boolean subscribed = false;

}
