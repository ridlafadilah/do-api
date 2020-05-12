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
@EqualsAndHashCode(callSuper=false)
@ToString()
@Entity
@Table(name = "mst_parameter_group", schema = SchemaDatabase.MASTER)
public class ParameterGroupEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965292952914303956L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "parameter_group_uuid", nullable = false, unique = true)
	private String id;

	@Column(name = "parameter_group_code", unique = true)
	private String parameterGroupCode;

	@Column(name = "parameter_group_name", nullable = false)
	private String parameterGroupName;
	
	@OneToMany(mappedBy = "parameterGroup", targetEntity = ParameterEntity.class, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<ParameterEntity> parameters = new HashSet<ParameterEntity>();

}