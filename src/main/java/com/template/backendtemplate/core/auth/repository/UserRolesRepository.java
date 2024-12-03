package com.template.backendtemplate.core.auth.repository;

import com.template.backendtemplate.core.auth.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {
    List<UserRolesEntity> findAllByUserId(Long userId);

}
