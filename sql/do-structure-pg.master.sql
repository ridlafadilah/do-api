CREATE TABLE master.mst_country (
	country_uuid varchar(36) NOT NULL,
	country_code varchar(3) NOT NULL,
	country_name varchar(150) NOT NULL,
	capital varchar(150) NOT NULL,
	phone_prefix varchar(10),
	"flag" varchar(100),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (country_uuid)
);
CREATE TABLE master.mst_province (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	country_uuid varchar(36) NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE master.mst_city (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	province_id int NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE master.mst_district (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	city_id int NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE master.mst_subdistrict (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	district_id int NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE master.mst_zipcode (
	"id" int NOT NULL,
	zipcode varchar(100),
	subdistrict_id int,
	district_id int,
	city_id int,
	province_id int,
	country_uuid varchar(36),
	PRIMARY KEY ("id")
);
CREATE TABLE master.mst_currency (
	currency_uuid varchar(36) NOT NULL,
	currency_code varchar(3) NOT NULL,
	currency_name varchar(150) NOT NULL,
	currency_sign varchar(10),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (currency_uuid)
);
CREATE TABLE master.mst_r_country_currency (
	country_uuid varchar(36) NOT NULL,
	currency_uuid varchar(36) NOT NULL
);
CREATE TABLE master.mst_language (
	language_uuid varchar(36) NOT NULL,
	language_code varchar(10) NOT NULL,
	language_identifier varchar(100) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (language_uuid)
);
CREATE TABLE master.mst_locale (
	locale_uuid varchar(36) NOT NULL,
	locale_code varchar(10) NOT NULL,
	locale_identifier varchar(100) NOT NULL,
	locale_icon varchar(100) DEFAULT 'flag-icon flag-icon-us',
	locale_default boolean DEFAULT false NOT NULL,
	locale_enabled boolean DEFAULT true NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (locale_uuid)
);
CREATE TABLE master.mst_parameter_group (
	parameter_group_uuid varchar(36) NOT NULL,
	parameter_group_code varchar(50) NOT NULL,
	parameter_group_name varchar(100) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (parameter_group_uuid)
);
CREATE TABLE master.mst_parameter (
	parameter_uuid varchar(36) NOT NULL,
	parameter_code varchar(50) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	parameter_group_uuid varchar(36) NOT NULL,
	PRIMARY KEY (parameter_uuid)
);
CREATE TABLE master.mst_parameter_i18n (
	parameter_i18n_uuid varchar(36) NOT NULL,
	parameter_uuid varchar(36) NOT NULL,
	locale_code varchar(10),
	parameter_value text NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (parameter_i18n_uuid)
);
ALTER TABLE master.mst_city
	ADD FOREIGN KEY (province_id) 
	REFERENCES master.mst_province ("id");

ALTER TABLE master.mst_district
	ADD FOREIGN KEY (city_id) 
	REFERENCES master.mst_city ("id");

ALTER TABLE master.mst_province
	ADD FOREIGN KEY (country_uuid) 
	REFERENCES master.mst_country (country_uuid);

ALTER TABLE master.mst_r_country_currency
	ADD FOREIGN KEY (country_uuid) 
	REFERENCES master.mst_country (country_uuid);

ALTER TABLE master.mst_r_country_currency
	ADD FOREIGN KEY (currency_uuid) 
	REFERENCES master.mst_currency (currency_uuid);

ALTER TABLE master.mst_subdistrict
	ADD FOREIGN KEY (district_id) 
	REFERENCES master.mst_district ("id");

ALTER TABLE master.mst_zipcode
	ADD FOREIGN KEY (city_id) 
	REFERENCES master.mst_city ("id");

ALTER TABLE master.mst_zipcode
	ADD FOREIGN KEY (country_uuid) 
	REFERENCES master.mst_country (country_uuid);

ALTER TABLE master.mst_zipcode
	ADD FOREIGN KEY (district_id) 
	REFERENCES master.mst_district ("id");

ALTER TABLE master.mst_zipcode
	ADD FOREIGN KEY (province_id) 
	REFERENCES master.mst_province ("id");

ALTER TABLE master.mst_zipcode
	ADD FOREIGN KEY (subdistrict_id) 
	REFERENCES master.mst_subdistrict ("id");

ALTER TABLE master.mst_parameter
	ADD FOREIGN KEY (parameter_group_uuid) 
	REFERENCES master.mst_parameter_group (parameter_group_uuid);

ALTER TABLE master.mst_parameter_i18n
	ADD FOREIGN KEY (parameter_uuid) 
	REFERENCES master.mst_parameter (parameter_uuid);
