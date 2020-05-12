package com.dongkap.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class CommonService {

	protected Pageable page(Map<String, List<String>> order, int offset, int limit) {
		int page = 0;
		if (limit < 10) limit = 10;
		else if (limit > 250) limit = 250;
		page = offset / limit;
		if (page < 0) page = 0;
		if (order != null && !order.isEmpty()) {
			Sort sort = null;
			for(Map.Entry<String, List<String>> direction : order.entrySet()) {
				if(Direction.ASC.toString().equalsIgnoreCase(direction.getKey())) {
					sort = new Sort(Direction.ASC, direction.getValue());
				} else {
					sort = new Sort(Direction.DESC, direction.getValue());
				}
			}
			return PageRequest.of(page, limit, sort);
		}
		return PageRequest.of(page, limit);
	}
	
}
