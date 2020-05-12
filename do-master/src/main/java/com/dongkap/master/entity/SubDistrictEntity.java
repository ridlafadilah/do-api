package com.dongkap.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@EqualsAndHashCode(callSuper=false, exclude={"district"})
@ToString(exclude={"district"})
@Entity
@Table(name = "mst_subdistrict", schema = SchemaDatabase.MASTER)
public class SubDistrictEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -761432932360741876L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "code", unique = true)
	private String subDistrictCode;

	@Column(name = "name", nullable = false)
	private String subDistrictName;

	@ManyToOne(targetEntity = DistrictEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", nullable = false, insertable = false, updatable = false)
	private DistrictEntity district;

}