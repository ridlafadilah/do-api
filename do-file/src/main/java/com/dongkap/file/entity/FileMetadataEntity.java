package com.dongkap.file.entity;

import java.math.BigDecimal;
import java.util.Date;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="file_metadata", schema = SchemaDatabase.FILE)
public class FileMetadataEntity extends BaseAuditEntity {

	private static final long serialVersionUID = 657390142518153080L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "file_metadata_uuid", nullable = false, unique = true)
	private String id;	
	
	@Column(name="file_checksum", nullable = false, unique = true)
	private String checksum;	
	
	@Column(name="file_full_name")
	private String fullname;
	
	@Column(name="file_short_name")
	private String shortname;
	
	@Column(name="file_extension")
	private String extension;
	
	@Column(name="file_full_path")
	private String fullPath;
	
	@Column(name="file_location")
	private String location;
	
	@Column(name="file_size")
	private BigDecimal size;
	
	@Column(name="file_date")
	private Date fileDate;
	
	@Column(name="file_type")
	private String fileType;
	
	@Column(name="file_download_count")
	private Integer downloadCount;
	
}
