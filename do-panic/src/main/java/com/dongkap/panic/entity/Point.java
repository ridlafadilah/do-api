package com.dongkap.panic.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

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
@Embeddable
public class Point implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5121273209969473537L;
	private Double x;
    private Double y;

}
