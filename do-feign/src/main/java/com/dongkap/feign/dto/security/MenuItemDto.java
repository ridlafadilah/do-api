package com.dongkap.feign.dto.security;

import com.dongkap.feign.dto.common.BaseAuditDto;

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
public class MenuItemDto extends BaseAuditDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1056030686541157137L;
	private String code;
	private String title;
	private String icon;
    private String link;
    private String access;
    private String type;
    private Boolean home = false;
    private Boolean group = false;

}
