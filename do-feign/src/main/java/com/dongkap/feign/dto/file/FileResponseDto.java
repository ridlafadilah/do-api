package com.dongkap.feign.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class FileResponseDto extends FileMetadataDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 855119062786486464L;
	private Integer downloadCount;

}
