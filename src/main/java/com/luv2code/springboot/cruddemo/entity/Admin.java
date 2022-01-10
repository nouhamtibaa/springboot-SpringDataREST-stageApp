package com.luv2code.springboot.cruddemo.entity;


import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="admin")

public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Nom")
	private String nom;
	
	@Column(name="Prenom")
	private String prenom;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	
	@Column(name="username")
	private String username;
	
	
	@Column(name="confirmer")
	private String confirmer;
	
	
	 /* @OneToMany(mappedBy="admin", // ce mappedBy se refére à la propriété admin dans la classe course 
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Etudiant> etudiants;
	
	
	@OneToMany(mappedBy="admin", // ce mappedBy se refére à la propriété instructor dans la classe course 
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Sujet> sujets;
	*/
	
	
public Admin() {
		
	}


	public Admin(String firstName, String lastName, String email, String password ) {
		this.nom = firstName;
		this.prenom = lastName;
		this.email = email;
		this.password = password;
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getConfirmer() {
		return confirmer;
	}


	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}
	
	
	
	
/*
	public List<Etudiant> getEtudiants() {
	return etudiants;
}

public void setEtudiants(List<Etudiant> etudiants) {
	this.etudiants = etudiants;
}


public List<Sujet> getSujets() {
	return sujets;
}


public void setSujets(List<Sujet> sujets) {
	this.sujets = sujets;
}

	*/
	
}
