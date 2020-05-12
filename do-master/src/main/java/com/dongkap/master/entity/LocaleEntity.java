package com.dongkap.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@EqualsAndHashCode(callSuper=false)
@ToString()
@Entity
@Table(name = "mst_locale", schema = SchemaDatabase.MASTER)
public class LocaleEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965292952914303956L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "locale_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "locale_code", unique = true)
	private String localeCode;

	@Column(name = "locale_identifier", nullable = false)
	private String identifier;

	@Column(name = "locale_icon")
	private String icon;

	@Column(name = "locale_default")
	private boolean localeDefault;

	@Column(name = "locale_enabled")
	private boolean localeEnabled;

}