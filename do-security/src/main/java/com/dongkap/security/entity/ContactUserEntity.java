package com.dongkap.security.entity;

import javax.persistence.CascadeType;
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
@EqualsAndHashCode(callSuper=false, exclude={"user", "personalInfo"})
@ToString(exclude={"user", "personalInfo"})
@Entity
@Table(name = "sec_contact_user", schema = SchemaDatabase.SECURITY)
public class ContactUserEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "contact_user_uuid", nullable = false, unique=true)
	private String id;

	@Column(name = "fullname", nullable = false, length = 75)
	private String name;
	
	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "country", nullable = true)
	private String country;

	@Column(name = "province", nullable = true)
	private String province;

	@Column(name = "city", nullable = true)
	private String city;

	@Column(name = "district", nullable = true)
	private String district;

	@Column(name = "sub_district", nullable = true)
	private String subDistrict;

	@Column(name = "zipcode", nullable = true)
	private String zipcode;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "image", nullable = true)
	private String image;

	@Column(name = "description", nullable = true)
	private String description;

	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_uuid", nullable = false, updatable = false)
	private UserEntity user;
	
	@OneToOne(mappedBy = "contactUser", targetEntity = PersonalInfoEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private PersonalInfoEntity personalInfo;

}