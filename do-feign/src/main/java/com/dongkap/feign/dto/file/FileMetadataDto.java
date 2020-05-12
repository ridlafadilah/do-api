package com.dongkap.feign.dto.file;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class FileMetadataDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -928821648193952510L;
	private String checksum;
	private String fullname;
	private String shortname;
	private String extension;
	private String fullPath;
	private String location;
	private BigDecimal size;
	private Date fileDate;
	private String fileType;

}
