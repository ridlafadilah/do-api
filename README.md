# do-api [![Actions Status](https://github.com/ridlafadilah/do-api/workflows/Dongkap%20CI/badge.svg)](https://github.com/ridlafadilah/do-api/actions)
Dongkap | Web Service Application

```

   __| |   ___    _ __     __ _  | | __   __ _   _ __  
  / _` |  / _ \  | '_ \   / _` | | |/ /  / _` | | '_ \ 
 | (_| | | (_) | | | | | | (_| | |   <  | (_| | | |_) |
  \__,_|  \___/  |_| |_|  \__, | |_|\_\  \__,_| | .__/ 
                          |___/                 |_|    

```

## Concept
A simple monolith application built on the microservices framework


## Features
* __Spring Security__
  * OAuth2
  * JWT
  * Header Signature (Hmac SHA-256)

* __Social Sign On__
  * Google

* __Google reCAPTCHA v2__ ( _for validation_ )

* __Notification__
  * Web Push Notification
  * Spring Mail

* __Web Socket__

* __Standard API Response (i18n)__


## Environment setup
Make sure following software is installed on your PC.
* [OpenJDK 12](https://adoptopenjdk.net/?variant=openjdk12&jvmVariant=hotspot)
* [Maven 3.6.3](https://maven.apache.org/download.cgi) or later
* [PostgreSQL 10](https://www.postgresql.org/download/) or later


## Compile & Test
* __Databases__ : [SQL Files](https://github.com/ridlafadilah/do-api/tree/master/sql)

  Database Name : ``do`` 
  
  User : ``dongkap``
  
	```
	psql -d do -f "do-structure-pg.schema.sql" -U dongkap
	psql -d do -f "do-structure-pg.security.sql" -U dongkap
	psql -d do -f "do-structure-pg.master.sql" -U dongkap
	psql -d do -f "do-structure-pg.file.sql" -U dongkap
	psql -d do -f "do-structure-pg.notification.sql" -U dongkap
	```
	```
	psql -d do -f "do-data.security.sql" -U dongkap
	psql -d do -f "do-data.security-temp.sql" -U dongkap
	psql -d do -f "do-data.master.sql" -U dongkap
	psql -d do -f "do-data.master.terms-conditions.sql" -U dongkap
	psql -d do -f "do-data.master.privacy-policy.sql" -U dongkap
	psql -d do -f "do-data-support.sql" -U dongkap
	```
* __Applications__

  Sample Environment variable to set.
  
  * __DATABASE_HOST__ : localhost
  * __DATABASE_NAME__ : do
  * __DATABASE_PASSWORD__ : ***
  * __DATABASE_PORT__ : 5432
  * __DATABASE_USERNAME__ : dongkap
  * __DONGKAP_REDIRECT_URI__ : http://localhost:4242/auth/callback
  * __GOOGLE_OAUTH_CLIENT_ID__ : ***  [Google API Console](https://console.developers.google.com/)
  * __GOOGLE_OAUTH_CLIENT_SECRET__ : ***  [Google API Console](https://console.developers.google.com/)
  * __MAIL_HOST__ : smtp.example.com
  * __MAIL_PASSWORD__ : ***
  * __MAIL_PERSONAL__ : Unknown
  * __MAIL_PORT__ : 587
  * __MAIL_USERNAME__ : unknown@example.com
  * __RECAPTCHA_SECRET_KEY__ : *** [Google reCAPTCHA v2](https://www.google.com/recaptcha/admin)
  * __RECAPTCHA_SITE_KEY__ : *** [Google reCAPTCHA v2](https://www.google.com/recaptcha/admin)

	```
	do-api$ mvn clean install
	```
	```
	do-main$ mvn clean install spring-boot:run
	```
* __Test__
  
  Please testing with [Dongkap Angular](https://github.com/ridlafadilah/do-ngx)

## How can I support?
  * Star Dongkap GitHub repo :star:
  * Create pull requests, submit bugs, suggest new features
  * If this project help you reduce time to develop, you can give me a cup of coffee :smiley:
    
    [![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](http://paypal.me/ridlafadilah)
	
	[![Buy Me A Coffee](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/ridlafadilah)