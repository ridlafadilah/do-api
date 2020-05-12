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
public class FakeDetailDto extends BaseAuditDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414375187443950132L;
	private String fileChecksum;
	private LocationDto location;
	private DeviceDto device;

}