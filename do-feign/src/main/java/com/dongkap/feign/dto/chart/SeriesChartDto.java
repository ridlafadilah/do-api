package com.dongkap.feign.dto.chart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
public class SeriesChartDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508014356324597638L;
	private String name;
	private Map<String, Object> data = new HashMap<String, Object>();

}