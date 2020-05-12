package com.dongkap.feign.dto.master;

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
public class ParameterDto extends ParameterGroupDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125190677227153892L;
	private String parameterCode;

}