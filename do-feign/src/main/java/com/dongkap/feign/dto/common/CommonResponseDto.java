package com.dongkap.feign.dto.common;

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
@EqualsAndHashCode
@ToString
public class CommonResponseDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5084710113051457542L;
	protected Long totalFiltered;
	protected Long totalRecord;
	protected List<T> data = new ArrayList<T>();

}
