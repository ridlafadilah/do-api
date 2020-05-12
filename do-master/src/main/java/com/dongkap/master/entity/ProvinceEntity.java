package com.dongkap.master.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@EqualsAndHashCode(callSuper=false, exclude={"country", "cities"})
@ToString(exclude={"country", "cities"})
@Entity
@Table(name = "mst_province", schema = SchemaDatabase.MASTER)
public class ProvinceEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965292952914303956L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "code", unique = true)
	private String provinceCode;

	@Column(name = "name", nullable = false)
	private String provinceName;
	
	@OneToMany(mappedBy = "province", targetEntity = CityEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<CityEntity> cities = new HashSet<CityEntity>();

	@ManyToOne(targetEntity = CountryEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "country_uuid", nullable = false, insertable = false, updatable = false)
	private CountryEntity country;

}