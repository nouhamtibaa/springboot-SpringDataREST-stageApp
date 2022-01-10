package com.luv2code.springboot.cruddemo.entity;


import java.sql.Blob;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;







@Entity
@Table(name="etudiant")
public class Etudiant2 {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Nom")
	private String nom;
	
	@Column(name="Prenom")
	private String prenom;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Lob
	@Column(name="CV")
	private byte[] CV;
	
	
	@Column(name = "file_name")
	private String file_name;
	
	@Column(name = "file_type")
	private String file_type;
	
  
	@Column(name="confirm")
	private String confirm;
	
	@Column(name="etat")
	private int etat;
	
	@Column(name="compteur")
	public int compteur;
	
	/*@JoinColumn(name="admin_id")
	private Admin admin;
	*/
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="etudiant_sujet",
			joinColumns=@JoinColumn(name="etudiant_id"),
			inverseJoinColumns=@JoinColumn(name="sujet_id")
			)

	private List<Sujet2> sujets;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	
	
		
	// define constructors
	
	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Etudiant2() {
		
	}

	
	
	public Etudiant2(int id , String firstName, String lastName) {
		this.id = id;
		this.nom = firstName;
		this.prenom = lastName;
		
		
	}
	
	
	public Etudiant2(int id , String firstName, String lastName, String email, String password) {
		this.id = id;
		this.nom = firstName;
		this.prenom = lastName;
		this.email = email;
		this.password = password;
		
		
	}
	
	public Etudiant2(int id , String firstName, String lastName, String email, String password, List<Sujet2> sujets) {
		this.id = id;
		this.nom = firstName;
		this.prenom = lastName;
		this.email = email;
		this.password = password;
		this.sujets = sujets;
		
	}
	
	public Etudiant2(String firstName, String lastName, String email, String password , byte[] cv) {
		this.nom = firstName;
		this.prenom = lastName;
		this.email = email;
		this.password = password;
		this.CV = cv;
	}

	
	public Etudiant2(String firstName, String lastName, String email, String password , String file_name, String file_type, byte[] picByte) {
		this.nom = firstName;
		this.prenom = lastName;
		this.email = email;
		this.password = password;
		this.file_name = file_name;
		this.file_type = file_type;
		this.CV = picByte;
	}
	
	
	
	public Etudiant2(String file_name, String file_type, byte[] picByte) {
		this.file_name = file_name;
		this.file_type = file_type;
		this.CV = picByte;
	}
	
	
	
	
	public Etudiant2(int id, String nom, String prenom, String email, String password, byte[] cV, String file_name,
			String file_type, List<Sujet2> sujets) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		CV = cV;
		this.file_name = file_name;
		this.file_type = file_type;
		this.sujets = sujets;
	}

	// define getter/setter
	
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

	
	
	

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public String getFile_name() {
		return file_name;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
		public byte[] getCV() {
		return CV;
	}

	
	public void setCV(byte[] cV) {
		CV = cV;
	}
	

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password="
				+ password + ", CV=" + CV + "]";
	}


	
	public void addSujet( Sujet2 lessujets) {
		if (sujets == null) {
			sujets= new ArrayList<>();
		}
		sujets.add(lessujets);
		
	}

 public List<Sujet2> getSujets() {
		return sujets;
	}

	public void setSujets(List<Sujet2> sujets) {
		this.sujets = sujets;
	}

		
}
