package com.dongkap.panic.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.feign.dto.chart.CommonChartDto;
import com.dongkap.panic.service.StatisticsPanicReportImplService;

@RestController
@RequestMapping("/api/panic")
public class StatisticsPanicReportController extends BaseControllerException {

	@Autowired
	private StatisticsPanicReportImplService statisticsPanicReportService;

	@RequestMapping(value = "/vw/auth/statistics-area/v.1/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<CommonChartDto> getStatisticsArea(Authentication authentication,
			@PathVariable(required = true) String year,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<CommonChartDto>(this.statisticsPanicReportService.getStatisticsArea(Integer.parseInt(year), authentication, locale), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/auth/statistics-gender/v.1/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<CommonChartDto> getStatisticsGender(Authentication authentication,
			@PathVariable(required = true) String year,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<CommonChartDto>(this.statisticsPanicReportService.getStatisticsGender(Integer.parseInt(year), authentication, locale), HttpStatus.OK);
	}


	@RequestMapping(value = "/vw/auth/statistics-periode/v.1/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<CommonChartDto> getStatisticsPeriode(Authentication authentication,
			@PathVariable(required = true) String year,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<CommonChartDto>(this.statisticsPanicReportService.getStatisticsPeriode(Integer.parseInt(year), authentication, locale), HttpStatus.OK);
	}

}
