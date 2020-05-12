package com.dongkap.feign.dto.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
public class FilterDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1183080114772374130L;
	private int offset;
	private int limit;
	private Map<String, Object> keyword = new HashMap<String, Object>();
	private Map<String, List<String>> order = new TreeMap<String, List<String>>();

}
