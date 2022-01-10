package com.luv2code.springboot.cruddemo.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




@Entity
@Table(name="sujets")
public class Sujet2 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Mission")
	private String miission;
	
	@Column(name="Profil")
	private String profil;

	
	@Column(name="titre")
	private String titre;
	
	/*
	@JoinColumn(name="admin_id")
	private Admin admin;
	*/
	
	
	/*@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="etudiant_sujet",
			joinColumns=@JoinColumn(name="sujet_id"),
			inverseJoinColumns=@JoinColumn(name="etudiant_id")
			)	*/
	 @ManyToMany( mappedBy = "sujets")
	  
	private List<Etudiant> etudiants;
	
	
	public Sujet2() {
	
	}
	
	


	public Sujet2(int id, String description, String mission, String profil) {
		
		this.id = id;
		this.description = description;
		this.miission = mission;
		this.profil = profil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMission() {
		return miission;
	}

	public void setMission(String mission) {
		this.miission = mission;
	}


	/*
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

*/
	
	
/*	
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
*/
	
	
	
	
	public String getProfil() {
		return profil;
	}




	public void setProfil(String profil) {
		this.profil = profil;
	}




	public String getTitre() {
		return titre;
	}




	public void setTitre(String titre) {
		this.titre = titre;
	}




	
	
	public void addEtudiant( Etudiant theStudent) {
		if (etudiants == null) {
			etudiants= new ArrayList<>();
		}
        etudiants.add(theStudent);
		
	}
	
	
	
}
