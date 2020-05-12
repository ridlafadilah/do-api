CREATE TABLE "notification".notif_subscription ( 
	notif_subscription_uuid varchar(36) NOT NULL,
	endpoint text NOT NULL,
	expiration_time int,
	p256dh text NOT NULL,
	auth text NOT NULL,
	platform varchar(255),
	user_name varchar(255) NOT NULL,
	subscribed boolean DEFAULT false NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (notif_subscription_uuid)
);
ALTER TABLE "notification".notif_subscription ADD CONSTRAINT endpoint UNIQUE (endpoint);