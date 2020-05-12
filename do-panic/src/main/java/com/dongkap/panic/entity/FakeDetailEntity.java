package com.dongkap.panic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(callSuper=false, exclude={"fakeReport", "device", "location"})
@ToString(exclude={"fakeReport", "device", "location"})
@Entity
@Table(name = "fake_detail", schema = SchemaDatabase.PANIC)
public class FakeDetailEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "fake_detail_uuid", nullable = false, unique=true)
	private String id;

	@Column(name = "file_checksum", nullable = true)
	private String fileChecksum;

	@ManyToOne(targetEntity = FakeReportEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "fake_code", nullable = false, updatable = false)
	private FakeReportEntity fakeReport;

	@ManyToOne(targetEntity = DeviceEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = false, updatable = false)
	private DeviceEntity device;

	@OneToOne(targetEntity = LocationEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_uuid", nullable = false, updatable = false)
	private LocationEntity location;

}