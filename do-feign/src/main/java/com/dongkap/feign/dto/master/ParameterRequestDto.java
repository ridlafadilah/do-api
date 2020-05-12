package com.dongkap.feign.dto.master;

import java.io.Serializable;
import java.util.Map;

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
public class ParameterRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125190677227153892L;
	private String parameterCode;
	private String parameterGroupCode;
	private Map<String, String> parameterValues;
	

}