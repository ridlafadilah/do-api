package com.dongkap.feign.dto.security;

import java.io.Serializable;

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
public class RequestForgotPasswordDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5796428849651043391L;
	private String email;
	private String urlForgotPassword;
	private Boolean mobile = true;

}