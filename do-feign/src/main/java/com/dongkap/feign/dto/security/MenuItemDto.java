package com.dongkap.feign.dto.security;

import java.util.HashMap;
import java.util.Map;

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
	private String id;
	private String code;
	private String title;
	private String icon;
    private String link;
    private String type;
	private Integer level;
    private Integer ordering;
    private String orderingStr;
    private Boolean home = false;
    private Boolean group = false;
    private Boolean leaf = false;
    private Map<String, String> i18n = new HashMap<String, String>();
    private Map<String, String> parentMenu = new HashMap<String, String>();

}
