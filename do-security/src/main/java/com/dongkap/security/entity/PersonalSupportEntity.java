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
@EqualsAndHashCode(callSuper=false, exclude={"personalInfo"})
@ToString(exclude={"personalInfo"})
@Entity
@Table(name = "sec_personal_support", schema = SchemaDatabase.SECURITY)
public class PersonalSupportEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "personal_support_uuid", nullable = false, unique=true)
	private String id;

	@Column(name = "reference_name", nullable = false)
	private String referenceName;
	
	@Column(name = "reference_address", nullable = false)
	private String referenceAddress;
	
	@Column(name = "reference_phone_number", nullable = false)
	private String referencePhoneNumber;
	
	@Column(name = "relationship", nullable = false)
	private String relationship;

	@OneToOne(targetEntity = PersonalInfoEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "personal_info_uuid", nullable = false, updatable = false)
	private PersonalInfoEntity personalInfo;

}