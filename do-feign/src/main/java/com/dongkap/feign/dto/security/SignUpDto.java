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
public class SignUpDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1742415621743889509L;
	private String fullname;
	private String username;
	private String email;
	private String password;
	private String confirmPassword;
	private String terms;
	private String recaptcha;

}