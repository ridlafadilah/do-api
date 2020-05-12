package com.dongkap.feign.dto.select;

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
public class SelectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2907366297576163951L;
	private String label;
	private String value;
	private Boolean disabled;
	private String icon;

}
