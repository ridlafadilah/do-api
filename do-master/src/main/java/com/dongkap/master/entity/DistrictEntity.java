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
@EqualsAndHashCode(callSuper=false, exclude={"city", "subDistricts"})
@ToString(exclude={"city", "subDistricts"})
@Entity
@Table(name = "mst_district", schema = SchemaDatabase.MASTER)
public class DistrictEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -761432932360741876L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "code", unique = true)
	private String districtCode;

	@Column(name = "name", nullable = false)
	private String districtName;
	
	@OneToMany(mappedBy = "district", targetEntity = SubDistrictEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<SubDistrictEntity> subDistricts = new HashSet<SubDistrictEntity>();

	@ManyToOne(targetEntity = CityEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = false, insertable = false, updatable = false)
	private CityEntity city;

}