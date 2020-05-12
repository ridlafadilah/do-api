package com.dongkap.feign.dto.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class AxisChartDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5356989006407136210L;
	private List<String> data = new ArrayList<String>();

}