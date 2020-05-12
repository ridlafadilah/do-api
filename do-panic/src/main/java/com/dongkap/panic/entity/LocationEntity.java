package com.dongkap.panic.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@EqualsAndHashCode(callSuper=false, exclude={"panicDetails", "fakeDetails"})
@ToString(exclude={"panicDetails", "fakeDetails"})
@Entity
@Table(name = "panic_location", schema = SchemaDatabase.PANIC)
public class LocationEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442773369159964802L;
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
    @Column(name = "location_uuid", nullable = false, unique=true)
	private String id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "latitude", nullable = false)),
            @AttributeOverride(name = "y", column = @Column(name = "longitude", nullable = false))
    })
	private Point coordinate;

	@Column(name = "formatted_address", nullable = true)
	private String formattedAddress;

	@Column(name = "province", nullable = true)
	private String province;

	@Column(name = "city", nullable = true)
	private String city;

	@Column(name = "district", nullable = true)
	private String district;
	
	@OneToMany(mappedBy = "location", targetEntity = PanicDetailEntity.class, fetch = FetchType.LAZY)
	private Set<PanicDetailEntity> panicDetails = new HashSet<PanicDetailEntity>();
	
	@OneToMany(mappedBy = "location", targetEntity = FakeDetailEntity.class, fetch = FetchType.LAZY)
	private Set<FakeDetailEntity> fakeDetails = new HashSet<FakeDetailEntity>();

}