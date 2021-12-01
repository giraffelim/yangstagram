package com.giraffelim.entity.audit

import com.giraffelim.annotation.AllOpen
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
@AllOpen
abstract class BaseEntity(
    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    var modifiedDate: LocalDateTime? = null
)