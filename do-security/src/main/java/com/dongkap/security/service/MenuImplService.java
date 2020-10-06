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
import com.dongkap.feign.dto.security.MenuItemDto;
import com.dongkap.feign.dto.tree.TreeDto;
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
		List<MenuDto> allMenus = this.constructMenu(this.menuRepo.loadAllMenuByRoleI18n(role, locale));
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
	
	public List<TreeDto<MenuItemDto>> loadTreeMenu(String type, String locale) throws Exception {
		if(locale == null)
			locale = this.locale;
		try {
			locale = locale.split(",")[0];	
		} catch (Exception e) {
			locale = this.locale;
		}
		List<TreeDto<MenuItemDto>> treeMenuDtos = this.constructTreeMenu(this.menuRepo.loadTypeMenuI18n(type, locale));
		return treeMenuDtos;
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
				menuDtos.add(menu.getObject());
			}			
		});
		return menuDtos;
	}
	
	private List<TreeDto<MenuItemDto>> constructTreeMenu(List<MenuEntity> menus) {
		List<TreeDto<MenuItemDto>> treeMenuDtos = new ArrayList<TreeDto<MenuItemDto>>();
		menus.forEach(menu->{
			if(menu.getLevel() == 0) {
				treeMenuDtos.add(this.getTreeObject(menu));
			}			
		});
		return treeMenuDtos;
	}

	private List<TreeDto<MenuItemDto>> getTreeChildren(MenuEntity menu) {
		if(menu.getChildsMenu().size() <= 0 || menu.getLeaf())
			return null;
		List<TreeDto<MenuItemDto>> treeMenuDtos = new ArrayList<TreeDto<MenuItemDto>>();
		menu.getChildsMenu().forEach(data->{
			treeMenuDtos.add(this.getTreeObject(data));
		});
		return treeMenuDtos;
	}
	
	private TreeDto<MenuItemDto> getTreeObject(MenuEntity menu) {
		TreeDto<MenuItemDto> treeMenuDto = new TreeDto<MenuItemDto>();
		treeMenuDto.setId(menu.getId());
		MenuItemDto menuItemDto = new MenuItemDto();
		menuItemDto.setCode(menu.getCode());
		menuItemDto.setIcon(menu.getIcon());
		menuItemDto.setLink(menu.getUrl());
		menuItemDto.setType(menu.getType());
		menuItemDto.setHome(menu.getHome());
		menuItemDto.setGroup(menu.getGroup());
		menu.getMenuI18n().forEach(i18n->{
			treeMenuDto.setName(i18n.getTitle());
			menuItemDto.setTitle(i18n.getTitle());
		});
		treeMenuDto.setChildren(this.getTreeChildren(menu));
		treeMenuDto.setItem(menuItemDto);
		treeMenuDto.setDisabled(!menu.isActive());
		return treeMenuDto;
	}
	
}