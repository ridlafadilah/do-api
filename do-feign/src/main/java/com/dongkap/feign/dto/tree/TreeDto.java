package com.dongkap.feign.dto.tree;

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
public class TreeDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311285209710646339L;
	private String id;
	private String name;
	private T item;
	private List<TreeDto<T>> children = new ArrayList<TreeDto<T>>();
	private Boolean selected = false;
	private Boolean expanded = false;
	private Boolean disabled = false;

}
