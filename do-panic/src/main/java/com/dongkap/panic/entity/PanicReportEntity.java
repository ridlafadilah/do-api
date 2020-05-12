package com.dongkap.panic.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@EqualsAndHashCode(callSuper=false, exclude={"panicDetails"})
@ToString(exclude={"panicDetails"})
@Entity
@Table(name = "panic_report", schema = SchemaDatabase.PANIC)
public class PanicReportEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@Column(name = "panic_code", nullable = false, unique = true, length = 50)
	private String panicCode;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "fullname", nullable = false, length = 75)
	private String name;
	
	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "id_number", nullable = false)
	private String idNumber;

	@Column(name = "month_report", nullable = false)
	private String month;

	@Column(name = "year_report", nullable = false)
	private Integer year;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "latest_latitude", nullable = false)),
            @AttributeOverride(name = "y", column = @Column(name = "latest_longitude", nullable = false))
    })
	private Point latestCoordinate;

	@Column(name = "latest_formatted_address", nullable = true)
	private String latestFormattedAddress;

	@Column(name = "latest_province", nullable = true)
	private String latestProvince;

	@Column(name = "latest_city", nullable = true)
	private String latestCity;

	@Column(name = "latest_district", nullable = true)
	private String latestDistrict;

	@Column(name = "latest_file_checksum", nullable = true)
	private String latestFileChecksum;

	@Column(name = "latest_device_id", nullable = true)
	private String latestDeviceID;

	@Column(name = "latest_device_name", nullable = true)
	private String latestDeviceName;

	@Column(name = "emergency_category", nullable = true)
	private String emergencyCategory;

	@Column(name = "status", nullable = true)
	private String status;
	
	@OneToMany(mappedBy = "panicReport", targetEntity = PanicDetailEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private Set<PanicDetailEntity> panicDetails = new HashSet<PanicDetailEntity>();

}