package com.example.demo.repository;

import java.security.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permission, Long> {}
