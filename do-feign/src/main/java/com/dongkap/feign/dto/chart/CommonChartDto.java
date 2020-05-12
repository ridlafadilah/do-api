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
public class CommonChartDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2876355732754633094L;
	private LegendChartDto legend;
	private AxisChartDto axis;
	private List<SeriesChartDto> series = new ArrayList<SeriesChartDto>();

}