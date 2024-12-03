package com.template.backendtemplate.core.auth.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}
