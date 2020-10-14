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
public class ForgotPasswordDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8133839452021875038L;
	private String verificationId;
	private String verificationCode;
	private String email;
	private String newPassword;
	private String confirmPassword;

}