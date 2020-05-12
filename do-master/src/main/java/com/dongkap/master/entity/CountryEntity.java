package com.dongkap.master.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
@EqualsAndHashCode(callSuper=false, exclude={"provinces"})
@ToString(exclude={"provinces"})
@Entity
@Table(name = "mst_country", schema = SchemaDatabase.MASTER)
public class CountryEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965292952914303956L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "country_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "country_code", unique = true)
	private String countryCode;

	@Column(name = "country_name", nullable = false)
	private String countryName;

	@Column(name = "capital", nullable = false)
	private String capital;

	@Column(name = "phone_prefix")
	private String phonePrefix;

	@Column(name = "flag")
	private String flag;
	
	@OneToMany(mappedBy = "country", targetEntity = ProvinceEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<ProvinceEntity> provinces = new HashSet<ProvinceEntity>();

}