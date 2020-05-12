CREATE TABLE security.oauth_access_token (
	token_id varchar(255),
	"token" bytea,
	authentication_id varchar(255) NOT NULL,
	user_name varchar(255),
	client_id varchar(255),
	authentication bytea,
	refresh_token varchar(255),
	PRIMARY KEY (authentication_id)
);
CREATE TABLE security.oauth_approvals (
	userId varchar(255),
	clientId varchar(255),
	"scope" varchar(255),
	status varchar(10),
	expiresAt timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lastModifiedAt timestamp
);
CREATE TABLE security.oauth_client_details (
	client_id varchar(255) NOT NULL,
	resource_ids varchar(255),
	client_secret varchar(255),
	"scope" varchar(255),
	authorized_grant_types varchar(255),
	web_server_redirect_uri varchar(255),
	authorities varchar(255),
	access_token_validity int,
	refresh_token_validity int,
	additional_information varchar(4096),
	autoapprove varchar(255),
	PRIMARY KEY (client_id)
);
CREATE TABLE security.oauth_client_token (
	token_id varchar(255),
	"token" bytea,
	authentication_id varchar(255) NOT NULL,
	user_name varchar(255),
	client_id varchar(255),
	PRIMARY KEY (authentication_id)
);
CREATE TABLE security.oauth_code (
	code varchar(255),
	authentication bytea
);
CREATE TABLE security.oauth_refresh_token (
	token_id varchar(255),
	"token" bytea,
	authentication bytea
);

CREATE TABLE security.sec_corporate (
	corporate_uuid varchar(36) NOT NULL,
	corporate_id varchar(50) NOT NULL,
	corporate_name varchar(255) NOT NULL,
	corporate_non_expired boolean DEFAULT true NOT NULL,
	email varchar(150),
	address text,
	telp_number varchar(20),
	fax_number varchar(20),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (corporate_uuid)
);
CREATE TABLE security.sec_function (
	function_uuid varchar(36) NOT NULL,
	menu_uuid varchar(36) NOT NULL,
	role_uuid varchar(36) NOT NULL,
	"access" varchar(30) DEFAULT 'read' NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (function_uuid)
);
CREATE TABLE security.sec_menu (
	menu_uuid varchar(36) NOT NULL,
	code varchar(200),
	url text,
	"level" int NOT NULL,
	"ordering" int NOT NULL,
	ordering_str varchar(100),
	icon varchar(100),
	"type" varchar(50) DEFAULT 'main' NOT NULL,
	is_leaf boolean DEFAULT false NOT NULL,
	is_home boolean DEFAULT false NOT NULL,
	is_group boolean DEFAULT false NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	parent_uuid varchar(36),
	PRIMARY KEY (menu_uuid)
);
CREATE TABLE security.sec_menu_i18n (
	menu_i18n_uuid varchar(36) NOT NULL,
	menu_uuid varchar(36) NOT NULL,
	locale_code varchar(10),
	title varchar(100) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (menu_i18n_uuid)
);

CREATE TABLE security.sec_r_user_corporate (
	user_uuid varchar(36) NOT NULL,
	corporate_uuid varchar(36) NOT NULL
);
CREATE TABLE security.sec_r_user_role (
	user_uuid varchar(36) NOT NULL,
	role_uuid varchar(36) NOT NULL
);
CREATE TABLE security.sec_role (
	role_uuid varchar(36) NOT NULL,
	role_name varchar(50) NOT NULL,
	description text,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (role_uuid)
);
CREATE TABLE security.sec_user (
	user_uuid varchar(36) NOT NULL,
	username varchar(25) NOT NULL,
	"password" text NOT NULL,
	account_enabled boolean DEFAULT true NOT NULL,
	account_non_expired boolean DEFAULT true NOT NULL,
	account_non_locked boolean DEFAULT true NOT NULL,
	credentials_non_expired boolean DEFAULT true NOT NULL,
	email varchar(150) NOT NULL,
	verification_code varchar(100),
	raw text,
	authority_default varchar(100),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (user_uuid)
);
CREATE TABLE security.sec_contact_user (
	contact_user_uuid varchar(36) NOT NULL,
	fullname varchar(75) NOT NULL,
	address text,
	country varchar(200),
	province varchar(200),
	city varchar(200),
	district varchar(200),
	sub_district varchar(200),
	zipcode varchar(200),
	phone_number varchar(20),
	image varchar(250),
	description text,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	user_uuid varchar(36) NOT NULL,
	PRIMARY KEY (contact_user_uuid)
);
CREATE TABLE security.sec_personal_info (
	personal_info_uuid varchar(36) NOT NULL,
	id_number varchar(50) NOT NULL,
	gender varchar(20) NOT NULL,
	place_of_birth varchar(50) NOT NULL,
	date_of_birth date NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	contact_user_uuid varchar(36) NOT NULL,
	PRIMARY KEY (personal_info_uuid)
);
CREATE TABLE security.sec_personal_support (
	personal_support_uuid varchar(36) NOT NULL,
	reference_name varchar(200) NOT NULL,
	reference_address text,
	reference_phone_number varchar(20),
	relationship varchar(50),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	personal_info_uuid varchar(36) NOT NULL,
	PRIMARY KEY (personal_support_uuid)
);
CREATE TABLE security.sec_settings (
	settings_uuid varchar(36) NOT NULL,
	locale_code varchar(10) DEFAULT 'en-US' NOT NULL,
	locale_identifier varchar(100) DEFAULT 'English (United States)' NOT NULL,
	locale_icon varchar(100) DEFAULT 'flag-icon flag-icon-us',
	theme varchar(10) DEFAULT 'default' NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	user_uuid varchar(36) NOT NULL,
	PRIMARY KEY (settings_uuid)
);

ALTER TABLE security.sec_user ADD CONSTRAINT username UNIQUE (username);
ALTER TABLE security.sec_user ADD CONSTRAINT email UNIQUE (email);
ALTER TABLE security.sec_corporate ADD CONSTRAINT corporate_id UNIQUE (corporate_id);

ALTER TABLE security.sec_personal_info
	ADD FOREIGN KEY (contact_user_uuid) 
	REFERENCES security.sec_contact_user (contact_user_uuid);

ALTER TABLE security.sec_personal_support
	ADD FOREIGN KEY (personal_info_uuid) 
	REFERENCES security.sec_personal_info (personal_info_uuid);

ALTER TABLE security.sec_function
	ADD FOREIGN KEY (role_uuid) 
	REFERENCES security.sec_role (role_uuid);

ALTER TABLE security.sec_function
	ADD FOREIGN KEY (menu_uuid) 
	REFERENCES security.sec_menu (menu_uuid);

ALTER TABLE security.sec_menu
	ADD FOREIGN KEY (parent_uuid) 
	REFERENCES security.sec_menu (menu_uuid);

ALTER TABLE security.sec_menu_i18n
	ADD FOREIGN KEY (menu_uuid) 
	REFERENCES security.sec_menu (menu_uuid);

ALTER TABLE security.sec_r_user_corporate
	ADD FOREIGN KEY (corporate_uuid) 
	REFERENCES security.sec_corporate (corporate_uuid);

ALTER TABLE security.sec_r_user_corporate
	ADD FOREIGN KEY (user_uuid) 
	REFERENCES security.sec_user (user_uuid);

ALTER TABLE security.sec_r_user_role
	ADD FOREIGN KEY (role_uuid) 
	REFERENCES security.sec_role (role_uuid);

ALTER TABLE security.sec_r_user_role
	ADD FOREIGN KEY (user_uuid) 
	REFERENCES security.sec_user (user_uuid);

ALTER TABLE security.sec_contact_user
	ADD FOREIGN KEY (user_uuid) 
	REFERENCES security.sec_user (user_uuid);

ALTER TABLE security.sec_settings
	ADD FOREIGN KEY (user_uuid) 
	REFERENCES security.sec_user (user_uuid);