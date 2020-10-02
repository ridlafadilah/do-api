package com.dongkap.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dongkap.feign.dto.security.MenuDto;
import com.dongkap.security.dao.MenuRepo;
import com.dongkap.security.entity.MenuEntity;

@Service("menuService")
public class MenuImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("menuRepo")
	private MenuRepo menuRepo;
	
	@Value("${do.locale}")
	private String locale;
	
	public Map<String, List<MenuDto>> loadAllMenuByRole(String role, Locale locale) throws Exception {
		return loadAllMenuByRole(role, locale.toLanguageTag());
	}
	
	public Map<String, List<MenuDto>> loadAllMenuByRole(String role, String locale) throws Exception {
		if(locale == null)
			locale = this.locale;
		try {
			locale = locale.split(",")[0];	
		} catch (Exception e) {
			locale = this.locale;
		}
		List<MenuDto> allMenus = constructMenu(this.menuRepo.loadAllMenuByRoleI18n(role, locale));
		return filterMenu(allMenus);
	}
	
	public List<MenuDto> loadMenuByRole(String role, Locale locale, String type) throws Exception {
		return loadMenuByRole(role, locale.toLanguageTag(), type);
	}
	
	public List<MenuDto> loadMenuByRole(String role, String locale, String type) throws Exception {
		if(locale == null)
			locale = this.locale;
		try {
			locale = locale.split(",")[0];	
		} catch (Exception e) {
			locale = this.locale;
		}
		return constructMenu(this.menuRepo.loadTypeMenuByRoleI18n(role, locale, type));
	}
	
	private Map<String, List<MenuDto>> filterMenu(List<MenuDto> menus) {
		List<MenuDto> mainMenus = new ArrayList<MenuDto>();
		List<MenuDto> extraMenus = new ArrayList<MenuDto>();
		menus.forEach(menuDto -> {
			if(menuDto.getType().equals("main")) {
				mainMenus.add(menuDto);
			} else {
				extraMenus.add(menuDto);
			}
		});
		/*List<MenuDto> mainMenus = menus.stream().filter(menuDto -> "main".equals(menuDto.getType())).collect(Collectors.toList());
		List<MenuDto> extraMenus = menus.stream().filter(menuDto -> "extra".equals(menuDto.getType())).collect(Collectors.toList());*/
		Map<String, List<MenuDto>> result = new HashMap<String, List<MenuDto>>();
		result.put("main", mainMenus);
		result.put("extra", extraMenus);
		return result;
	}
	
	private List<MenuDto> constructMenu(List<MenuEntity> menus) {
		List<MenuDto> menuDtos = new ArrayList<MenuDto>();
		menus.forEach(menu->{
			if(menu.getLevel() == 0) {
				menuDtos.add(menu.toObjectI18n());
			}			
		});
		return menuDtos;
	}
	
}