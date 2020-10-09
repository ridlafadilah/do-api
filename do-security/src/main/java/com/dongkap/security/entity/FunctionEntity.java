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
@EqualsAndHashCode(callSuper=false, exclude={"menu", "role"})
@ToString(exclude={"menu", "role"})
@Entity
@Table(name = "sec_function", schema = SchemaDatabase.SECURITY)
public class FunctionEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870155744883664118L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "function_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "access")
	private String access;

	@Column(name = "menu_uuid", nullable = false)
	private String menuId;

	@Column(name = "role_uuid", nullable = false)
	private String roleId;

	@ManyToOne(targetEntity = MenuEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_uuid", nullable = false, insertable = false, updatable = false)
	private MenuEntity menu;

	@ManyToOne(targetEntity = RoleEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_uuid", nullable = false, insertable = false, updatable = false)
	private RoleEntity role;

}