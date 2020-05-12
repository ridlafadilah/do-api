package com.dongkap.common.http;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

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
public class ApiBaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5750204327237137628L;
	private String respStatusCode = "success";
	private Map<String, String> respStatusMessage = new TreeMap<String, String>();

}
