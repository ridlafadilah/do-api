package com.dongkap.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.security.MenuDto;
import com.dongkap.feign.dto.security.MenuItemDto;
import com.dongkap.feign.dto.select.SelectDto;
import com.dongkap.feign.dto.select.SelectResponseDto;
import com.dongkap.feign.dto.tree.TreeDto;
import com.dongkap.security.dao.MenuRepo;
import com.dongkap.security.entity.MenuEntity;
import com.dongkap.security.entity.MenuI18nEntity;
import com.dongkap.security.entity.UserEntity;

@Service("menuService")
public class MenuImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("menuRepo")
	private MenuRepo menuRepo;
	
	@Value("${do.locale}")
	private String locale;

	@Transactional
	public ApiBaseResponse doPostMenu(MenuItemDto p_dto, UserEntity user, String p_locale) throws Exception {
		if (p_dto != null) {
			if(p_dto.getI18n() == null)
				throw new SystemErrorException(ErrorCode.ERR_SYS0404);
			MenuI18nEntity menuI18n = new MenuI18nEntity();
			MenuEntity menu;
			if(p_dto.getId() != null) {
				menu = this.menuRepo.findById(p_dto.getId()).orElse(null);
				if (menu == null)
					throw new SystemErrorException(ErrorCode.ERR_SYS0404);					
				menu.setModifiedBy(user.getUsername());
				menu.setModifiedDate(new Date());
				for(final String key: p_dto.getI18n().keySet()) {
					menuI18n = menu.getMenuI18n().stream().filter(i18n -> key.equals(i18n.getLocale())).findFirst().orElse(null);
					if(menuI18n != null) {
						menuI18n.setTitle(p_dto.getI18n().get(key));
						menuI18n.setModifiedBy(user.getUsername());
						menuI18n.setModifiedDate(new Date());
						menuI18n.setMenu(menu);
					} else {
						menuI18n = new MenuI18nEntity();
						menuI18n.setLocale(key);
						menuI18n.setTitle(p_dto.getI18n().get(key));
						menuI18n.setCreatedBy(user.getUsername());
						menuI18n.setCreatedDate(new Date());
						menuI18n.setMenu(menu);
					}
					menu.getMenuI18n().add(menuI18n);
				}
			} else {
				menu = new MenuEntity();
				menu.setCreatedBy(user.getUsername());
				menu.setCreatedDate(new Date());
				for(String key: p_dto.getI18n().keySet()) {
					menuI18n = new MenuI18nEntity();
					menuI18n.setLocale(key);
					menuI18n.setTitle(p_dto.getI18n().get(key));
					menuI18n.setCreatedBy(user.getUsername());
					menuI18n.setCreatedDate(new Date());
					menuI18n.setMenu(menu);
					menu.getMenuI18n().add(menuI18n);
				}				
			}
			menu.setCode(p_dto.getCode());
			menu.setUrl(p_dto.getLink());
			menu.setLevel(p_dto.getLevel());
			menu.setOrdering(p_dto.getOrdering());
			menu.setIcon(p_dto.getIcon());
			menu.setType(p_dto.getType());
			menu.setLeaf(p_dto.getLeaf());
			menu.setHome(p_dto.getHome());
			menu.setGroup(p_dto.getGroup());
			if(p_dto.getParentMenu() != null) {
				MenuEntity menuParent = this.menuRepo.findById(p_dto.getParentMenu().get("id")).orElse(null);
				if (menuParent != null) {
					menu.setParentId(menuParent.getId());
					if(p_dto.getType() == "main")
						menu.setOrderingStr(menuParent.getOrderingStr()+"."+String.format("%03d", p_dto.getOrdering()));
					else
						menu.setOrderingStr(menuParent.getOrderingStr()+"."+"1"+String.format("%02d", p_dto.getOrdering()));
				} else
					throw new SystemErrorException(ErrorCode.ERR_SYS0404);
			} else {
				menu.setParentId(null);
				if(p_dto.getType() == "main")
					menu.setOrderingStr(String.format("%03d", p_dto.getOrdering()));
				else
					menu.setOrderingStr("1"+String.format("%02d", p_dto.getOrdering()));
			}
			this.menuRepo.saveAndFlush(menu);
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
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
		List<MenuDto> allMenus = this.buildMenu(this.menuRepo.loadAllMenuByRoleI18n(role, locale));
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
		return this.buildMenu(this.menuRepo.loadTypeMenuByRoleI18n(role, locale, type));
	}
	
	public List<TreeDto<MenuItemDto>> loadTreeMenu(String type, String locale) throws Exception {
		if(locale == null)
			locale = this.locale;
		try {
			locale = locale.split(",")[0];	
		} catch (Exception e) {
			locale = this.locale;
		}
		List<TreeDto<MenuItemDto>> treeMenuDtos = this.buildTreeMenu(this.menuRepo.loadMenuByType(type), locale);
		return treeMenuDtos;
	}
	
	public List<TreeDto<MenuItemDto>> loadTreeMenuFunctionControl(String type, String role, String locale) throws Exception {
		if(locale == null)
			locale = this.locale;
		try {
			locale = locale.split(",")[0];	
		} catch (Exception e) {
			locale = this.locale;
		}
		List<MenuEntity> menus = this.menuRepo.loadMenuByType(type);
		Set<MenuEntity> functions = this.menuRepo.loadTypeMenuByRoleSet(type, role);
		List<TreeDto<MenuItemDto>> treeMenuDtos = this.buildTreeFunctionMenu(menus, functions, locale);
		return treeMenuDtos;
	}
	
	public SelectResponseDto getSelectRootMainMenus(String type, String locale) throws Exception {
		List<MenuEntity> menus = menuRepo.loadRootMenuByTypeLevelI18n(type, locale, 0, false);
		final SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(Long.valueOf(menus.size()));
		response.setTotalRecord(Long.valueOf(menus.size()));
		menus.forEach(value -> {
			final SelectDto data = new SelectDto();
			value.getMenuI18n().forEach(i18n->{
				data.setLabel(i18n.getTitle());
				data.setValue(value.getId());
				data.setDisabled(!value.isActive());
			});
			response.getData().add(data);
		});
		return response;
	}

	public void deleteMenu(String id) throws Exception {
		MenuEntity menu = this.menuRepo.findById(id).orElse(null);
		if(menu == null)
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
		try {
			this.menuRepo.delete(menu);
		} catch (DataIntegrityViolationException e) {
			throw new SystemErrorException(ErrorCode.ERR_SCR0009);
		} catch (ConstraintViolationException e) {
			throw new SystemErrorException(ErrorCode.ERR_SCR0009);
		}
	}
	
	private Map<String, List<MenuDto>> filterMenu(List<MenuDto> menus) {
		final List<MenuDto> mainMenus = new ArrayList<MenuDto>();
		final List<MenuDto> extraMenus = new ArrayList<MenuDto>();
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
	
	private List<MenuDto> buildMenu(List<MenuEntity> menus) {
		final List<MenuDto> menuDtos = new ArrayList<MenuDto>();
		menus.forEach(menu->{
			if(menu.getLevel() == 0) {
				menuDtos.add(menu.getObject());
			}			
		});
		return menuDtos;
	}
	
	private List<TreeDto<MenuItemDto>> buildTreeMenu(List<MenuEntity> menus, final String locale) {
		final List<TreeDto<MenuItemDto>> treeMenuDtos = new ArrayList<TreeDto<MenuItemDto>>();
		menus.forEach(menu->{
			if(menu.getLevel() == 0) {
				treeMenuDtos.add(this.getTreeObject(menu, locale));
			}			
		});
		return treeMenuDtos;
	}
	
	private List<TreeDto<MenuItemDto>> buildTreeFunctionMenu(List<MenuEntity> menus, final Set<MenuEntity> functions, final String locale) {
		final List<TreeDto<MenuItemDto>> treeMenuDtos = new ArrayList<TreeDto<MenuItemDto>>();
		
		menus.forEach(menu->{
			if(menu.getLevel() == 0) {
				treeMenuDtos.add(this.getTreeObjectFunction(menu, functions, locale));
			}			
		});
		return treeMenuDtos;
	}

	private List<TreeDto<MenuItemDto>> getTreeChildren(MenuEntity menu, final String locale) {
		if(menu.getChildsMenu().size() <= 0 || menu.getLeaf())
			return null;
		final List<TreeDto<MenuItemDto>> treeMenuDtos = new ArrayList<TreeDto<MenuItemDto>>();
		menu.getChildsMenu().forEach(data->{
			treeMenuDtos.add(this.getTreeObject(data, locale));
		});
		return treeMenuDtos;
	}
	
	private TreeDto<MenuItemDto> getTreeObject(MenuEntity menu, final String locale) {
		final TreeDto<MenuItemDto> treeMenuDto = new TreeDto<MenuItemDto>();
		treeMenuDto.setId(menu.getId());
		final Map<String, String> menuI18n = new HashMap<String, String>();
		final Map<String, String> parentMenu = new HashMap<String, String>();
		final MenuItemDto menuItemDto = new MenuItemDto();
		menuItemDto.setCode(menu.getCode());
		menuItemDto.setIcon(menu.getIcon());
		menuItemDto.setLink(menu.getUrl());
		menuItemDto.setType(menu.getType());
		menuItemDto.setLevel(menu.getLevel());
		menuItemDto.setOrdering(menu.getOrdering());
		menuItemDto.setOrderingStr(menu.getOrderingStr());
		menuItemDto.setHome(menu.getHome());
		menuItemDto.setGroup(menu.getGroup());
		menuItemDto.setLeaf(menu.getLeaf());
		menu.getMenuI18n().forEach(i18n->{
			if(i18n.getLocale().equals(locale)) {
				treeMenuDto.setName(i18n.getTitle());
				menuItemDto.setTitle(i18n.getTitle());	
			}
			menuI18n.put(i18n.getLocale(), i18n.getTitle());
		});
		if(menu.getParentMenu() != null) {
			parentMenu.put("id", menu.getParentMenu().getId());
			menu.getParentMenu().getMenuI18n().forEach(i18n->{
				if(i18n.getLocale().equals(locale)) {
					parentMenu.put("title", i18n.getTitle());	
				}
			});
			menuItemDto.setParentMenu(parentMenu);
		}
		menuItemDto.setI18n(menuI18n);
		treeMenuDto.setChildren(this.getTreeChildren(menu, locale));
		treeMenuDto.setItem(menuItemDto);
		treeMenuDto.setDisabled(!menu.isActive());
		return treeMenuDto;
	}

	private List<TreeDto<MenuItemDto>> getTreeChildrenFunction(MenuEntity menu, final Set<MenuEntity> functions, final String locale) {
		if(menu.getChildsMenu().size() <= 0 || menu.getLeaf())
			return null;
		final List<TreeDto<MenuItemDto>> treeMenuDtos = new ArrayList<TreeDto<MenuItemDto>>();
		menu.getChildsMenu().forEach(data->{
			treeMenuDtos.add(this.getTreeObjectFunction(data, functions, locale));
		});
		return treeMenuDtos;
	}
	
	private TreeDto<MenuItemDto> getTreeObjectFunction(final MenuEntity menu, Set<MenuEntity> functions, final String locale) {
		final TreeDto<MenuItemDto> treeMenuDto = new TreeDto<MenuItemDto>();
		treeMenuDto.setId(menu.getId());
		final Map<String, String> menuI18n = new HashMap<String, String>();
		final Map<String, String> parentMenu = new HashMap<String, String>();
		final MenuItemDto menuItemDto = new MenuItemDto();
		menuItemDto.setId(menu.getId());
		menuItemDto.setCode(menu.getCode());
		menuItemDto.setIcon(menu.getIcon());
		menuItemDto.setLink(menu.getUrl());
		menuItemDto.setType(menu.getType());
		menuItemDto.setLevel(menu.getLevel());
		menuItemDto.setOrdering(menu.getOrdering());
		menuItemDto.setOrderingStr(menu.getOrderingStr());
		menuItemDto.setHome(menu.getHome());
		menuItemDto.setGroup(menu.getGroup());
		menuItemDto.setLeaf(menu.getLeaf());
		menu.getMenuI18n().forEach(i18n->{
			if(i18n.getLocale().equals(locale)) {
				treeMenuDto.setName(i18n.getTitle());
				menuItemDto.setTitle(i18n.getTitle());	
			}
			menuI18n.put(i18n.getLocale(), i18n.getTitle());
		});
		if(menu.getParentMenu() != null) {
			parentMenu.put("id", menu.getParentMenu().getId());
			menu.getParentMenu().getMenuI18n().forEach(i18n->{
				if(i18n.getLocale().equals(locale)) {
					parentMenu.put("title", i18n.getTitle());	
				}
			});
			menuItemDto.setParentMenu(parentMenu);
		}
		menuItemDto.setI18n(menuI18n);
		final MenuEntity function = functions.stream().filter(funct -> menu.getId().equals(funct.getId())).findFirst().orElse(new MenuEntity());
		function.getFunction().forEach(funct->{
			if(menu.getId().equals(function.getId()) && funct.getAccess() != null) {
				treeMenuDto.setSelected(true);
			}
		});
		treeMenuDto.setChildren(this.getTreeChildrenFunction(menu, function.getChildsMenu(), locale));
		treeMenuDto.setItem(menuItemDto);
		treeMenuDto.setDisabled(!menu.isActive());
		return treeMenuDto;
	}
	
}