package com.dongkap.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "version", nullable = false)
	@Version
	protected int version;

	@Column(name = "is_active", nullable = false)
    protected boolean active = true;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", updatable = false)
    @CreatedDate
    protected Date createdDate;

	@Column(name = "created_by", updatable = false) 
    @CreatedBy
    protected String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date", insertable = false)
    @LastModifiedDate
    protected Date modifiedDate;

    @Column(name = "modified_by", insertable = false)
    @LastModifiedBy
    protected String modifiedBy;

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		modifiedDate = new Date();
		if (createdDate == null) {
			createdDate = new Date();
		}
	}

}
