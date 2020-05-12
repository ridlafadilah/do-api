package com.dongkap.security.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.dongkap.common.entity.BaseAuditEntity;
import com.dongkap.common.utils.SchemaDatabase;
import com.dongkap.feign.dto.security.MenuDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude={"childsMenu", "parentMenu", "menuI18n"})
@ToString(exclude={"childsMenu", "parentMenu", "menuI18n"})
@Entity
@Table(name = "sec_menu", schema = SchemaDatabase.SECURITY)
public class MenuEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870155744883664118L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "menu_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "level")
	private Integer level;

	@Column(name = "ordering")
	private Integer ordering;

	@Column(name = "ordering_str")
	private String orderingStr;

	@Column(name = "icon")
	private String icon;

	@Column(name = "type", nullable = false)
	private String type = "main";

	@Column(name = "is_leaf")
	private Boolean leaf;

	@Column(name = "is_home")
	private Boolean home;

	@Column(name = "is_group")
	private Boolean group;

	@Column(name = "parent_uuid", nullable = true)
	private String parentId;

	@ManyToOne(targetEntity = MenuEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "parent_uuid", nullable = true, insertable = false, updatable = false)
	private MenuEntity parentMenu;

	@OneToMany(mappedBy = "parentMenu", fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@OrderBy("orderingStr ASC")
	private Set<MenuEntity> childsMenu = new HashSet<MenuEntity>();
	
	@OneToMany(mappedBy = "menu", targetEntity = MenuI18nEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<MenuI18nEntity> menuI18n = new HashSet<MenuI18nEntity>();
	
	@OneToMany(mappedBy = "menu", targetEntity = FunctionEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<FunctionEntity> function = new HashSet<FunctionEntity>();
	
	@Transient
	public List<MenuDto> getChildrenI18n() {
		if(childsMenu.size() <= 0 || this.leaf)
			return null;
		List<MenuDto> menuDtos = new ArrayList<MenuDto>();
		childsMenu.forEach(data->{
			menuDtos.add(data.toObjectI18n());
		});
		return menuDtos;
	}
	
	@Transient
	public MenuDto toObjectI18n() {
		MenuDto menuDto = new MenuDto();
		function.forEach(funct->{
			menuDto.setCode(this.code);
			menuDto.setIcon(this.icon);
			menuDto.setLink(this.url);
			menuDto.setAccess(funct.getAccess());
			menuDto.setType(this.type);
			menuDto.setHome(this.home);
			menuDto.setGroup(this.group);
			menuDto.setChildren(this.getChildrenI18n());
		});
		this.menuI18n.forEach(i18n->{
			menuDto.setTitle(i18n.getTitle());
		});
		return menuDto;
	}

}