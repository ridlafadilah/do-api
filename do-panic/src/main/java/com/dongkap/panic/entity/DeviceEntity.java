package com.dongkap.panic.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@EqualsAndHashCode(callSuper=false, exclude={"panicDetails", "fakeDetails"})
@ToString(exclude={"panicDetails", "fakeDetails"})
@Entity
@Table(name = "panic_device", schema = SchemaDatabase.PANIC)
public class DeviceEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
    @Column(name = "device_id", nullable = false, unique=true)
	private String deviceID;
	
	@Column(name = "device_name", nullable = false)
	private String deviceName;
	
	@OneToMany(mappedBy = "device", targetEntity = PanicDetailEntity.class, fetch = FetchType.LAZY)
	private Set<PanicDetailEntity> panicDetails = new HashSet<PanicDetailEntity>();
	
	@OneToMany(mappedBy = "device", targetEntity = FakeDetailEntity.class, fetch = FetchType.LAZY)
	private Set<FakeDetailEntity> fakeDetails = new HashSet<FakeDetailEntity>();

}