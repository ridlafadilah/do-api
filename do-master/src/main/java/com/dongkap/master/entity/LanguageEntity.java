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
@Table(name = "mst_language", schema = SchemaDatabase.MASTER)
public class LanguageEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965292952914303956L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "language_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "language_code", unique = true)
	private String languageCode;

	@Column(name = "language_identifier", nullable = false)
	private String identifier;

}