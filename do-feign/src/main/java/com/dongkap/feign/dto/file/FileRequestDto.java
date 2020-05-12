package com.dongkap.feign.dto.file;

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
public class FileRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4241307247599396547L;
	private String fullname;
	private String file;
	private String extension;
	private String fileType;

}
