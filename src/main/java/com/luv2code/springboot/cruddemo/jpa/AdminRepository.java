package com.luv2code.springboot.cruddemo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.luv2code.springboot.cruddemo.entity.Admin;
import com.luv2code.springboot.cruddemo.entity.Etudiant;


//@RepositoryRestResource(path="members") // houwa fel assl bech yekhou l Employee w yzidha "s" 
// maaneha http://localhost:8080/employeeshttp://localhost:8080/members
// ama tawa ahna nhebou l path members donc ywali 
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUsername(String username);
	
}
