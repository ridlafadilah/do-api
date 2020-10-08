package com.dongkap.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dongkap.common.entity.BaseAuditEntity;
import com.dongkap.common.utils.SchemaDatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude={"menu"})
@ToString(exclude={"menu"})
@Entity
@Table(name = "sec_menu_i18n", schema = SchemaDatabase.SECURITY)
public class MenuI18nEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870155744883664118L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "menu_i18n_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "locale_code", nullable = false)
	private String locale;

	@Column(name = "title", nullable = false)
	private String title;

	@ManyToOne(targetEntity = MenuEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_uuid", nullable = false, updatable = false)
	private MenuEntity menu;

}