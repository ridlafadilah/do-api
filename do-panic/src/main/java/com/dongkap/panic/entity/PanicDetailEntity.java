package com.dongkap.panic.entity;

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
@EqualsAndHashCode(callSuper=false, exclude={"panicReport", "device", "location"})
@ToString(exclude={"panicReport", "device", "location"})
@Entity
@Table(name = "panic_detail", schema = SchemaDatabase.PANIC)
public class PanicDetailEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "panic_detail_uuid", nullable = false, unique=true)
	private String id;

	@Column(name = "file_checksum", nullable = true)
	private String fileChecksum;

	@ManyToOne(targetEntity = PanicReportEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "panic_code", nullable = false, updatable = false)
	private PanicReportEntity panicReport;

	@ManyToOne(targetEntity = DeviceEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "device_id", nullable = false, updatable = false)
	private DeviceEntity device;

	@ManyToOne(targetEntity = LocationEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "location_uuid", nullable = false, updatable = false)
	private LocationEntity location;

}