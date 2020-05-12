package com.dongkap.feign.dto.panic;

import com.dongkap.feign.dto.common.BaseAuditDto;

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
public class DeviceDto extends BaseAuditDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1059600685381694217L;
	private String deviceID;
	private String deviceName;

}