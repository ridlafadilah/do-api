package com.dongkap.feign.dto.security;

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
@EqualsAndHashCode(callSuper = false, exclude={"children"})
@ToString(exclude={"children"})
public class MenuDto extends MenuItemDto {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2624389791249022903L;
    private String access;
    private List<MenuDto> children = new ArrayList<MenuDto>();

}
