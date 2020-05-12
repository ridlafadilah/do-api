package com.dongkap.master.entity;

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
@EqualsAndHashCode(callSuper=false, exclude = { "parameter" })
@ToString(exclude = { "parameter" })
@Entity
@Table(name = "mst_parameter_i18n", schema = SchemaDatabase.MASTER)
public class ParameterI18nEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965292952914303956L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "parameter_i18n_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "parameter_value", nullable = false)
	private String parameterValue;

	@Column(name = "locale_code")
	private String localeCode;

	@ManyToOne(targetEntity = ParameterEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "parameter_uuid", nullable = false, updatable = false)
	private ParameterEntity parameter;

}