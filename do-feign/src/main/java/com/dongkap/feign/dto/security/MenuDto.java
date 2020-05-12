package com.dongkap.feign.dto.security;

import java.util.ArrayList;
import java.util.List;

import com.dongkap.feign.dto.common.BaseAuditDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude={"children"})
@ToString(exclude={"children"})
public class MenuDto extends BaseAuditDto {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2624389791249022903L;
	private String code;
	private String title;
    private String icon;
    private String link;
    private String access;
    private String type;
    private Boolean home = false;
    private Boolean group = false;
    private List<MenuDto> children = new ArrayList<MenuDto>();

}
