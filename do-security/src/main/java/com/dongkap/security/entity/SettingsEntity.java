package com.dongkap.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@EqualsAndHashCode(callSuper=false, exclude={"user"})
@ToString(exclude={"user"})
@Entity
@Table(name = "sec_settings", schema = SchemaDatabase.SECURITY)
public class SettingsEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "settings_uuid", nullable = false, unique=true)
	private String id;

	@Column(name = "locale_code", nullable = false)
	private String localeCode = "en-US";

	@Column(name = "locale_identifier", nullable = false)
	private String localeIdentifier = "English (United States)";

	@Column(name = "locale_icon")
	private String localeIcon = "flag-icon flag-icon-us";

	@Column(name = "theme", nullable = false)
	private String theme = "default";

	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_uuid", nullable = false, updatable = false)
	private UserEntity user;

}