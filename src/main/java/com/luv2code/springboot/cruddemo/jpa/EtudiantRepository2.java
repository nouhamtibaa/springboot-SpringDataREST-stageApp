package com.luv2code.springboot.cruddemo.jpa;

import java.util.List;


//import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.luv2code.springboot.cruddemo.entity.Etudiant;
import com.luv2code.springboot.cruddemo.entity.Etudiant2;


//@RepositoryRestResource(path="upload") // houwa fel assl bech yekhou l Employee w yzidha "s" 
// maaneha http://localhost:8080/employeeshttp://localhost:8080/members
// ama tawa ahna nhebou l path members donc ywali 
public interface EtudiantRepository2 extends JpaRepository<Etudiant2, Integer> {
	
	 //@Query(value = "select u from etudiant u where u.nom=?1 ", nativeQuery = true)
	 @Query(value = "SELECT c.id, c.nom, c.prenom FROM etudiant AS c", nativeQuery = true)
	 // @Query(value = "SELECT c.id, c.nom, c.prenom, c.CV FROM etudiant c where c.nom=?1", nativeQuery = true)
	// @Query(value = "SELECT * FROM etudiant c where c.nom=?1", nativeQuery = true)
	  //List<Etudiant> findAllEtudiant(String nom);
	  List<Etudiant2> findAllEtudiant();
	
	Etudiant2 findByEmail(String email);
	Etudiant2 findByNom(String nom);
}
