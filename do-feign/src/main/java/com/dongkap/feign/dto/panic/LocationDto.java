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
public class LocationDto extends BaseAuditDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8090724528887093110L;
	private Double latitude;
	private Double longitude;
	private String formattedAddress;
	private String province;
	private String city;
	private String district;

}