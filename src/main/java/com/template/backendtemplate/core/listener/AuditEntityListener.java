package com.template.backendtemplate.core.listener;

import com.template.backendtemplate.core.entity.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditEntityListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setCreatedDate(LocalDateTime.now());
            baseEntity.setUpdatedDate(null);
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setUpdatedDate(LocalDateTime.now());
        }
    }
}
