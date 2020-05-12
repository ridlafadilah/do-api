package com.dongkap.feign.dto.panic;

import java.util.ArrayList;
import java.util.List;

import com.dongkap.feign.dto.file.FileMetadataDto;

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
public class PanicReportDto extends BasePanicReportDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	private String panicCode;
	private String username;
	private String name;
	private String gender;
	private String phoneNumber;
	private Integer age;
	private String idNumber;
	private String month;
	private Integer year;
	private String emergencyCategory;
	private String status;
	private FileMetadataDto fileMetadata;
	private List<PanicDetailDto> panicDetails = new ArrayList<PanicDetailDto>();

}