package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{
//@Query("SELECT ad FROM Admin ad WHERE ad.username = :un")
//	Optional<Admin> findByusername(@Param("un") String username);
}
