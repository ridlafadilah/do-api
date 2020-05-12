CREATE TABLE "file".file_metadata ( 
	file_metadata_uuid varchar(36) NOT NULL,
	file_checksum varchar(36) NOT NULL,
	file_full_name text NOT NULL,
	file_short_name text,
	file_extension varchar(100),
	file_full_path text,
	file_location text,
	file_size int,
	file_date timestamp,
	file_type varchar(255),
	file_download_count int,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (file_metadata_uuid)
);
ALTER TABLE "file".file_metadata ADD CONSTRAINT file_checksum UNIQUE (file_checksum);
