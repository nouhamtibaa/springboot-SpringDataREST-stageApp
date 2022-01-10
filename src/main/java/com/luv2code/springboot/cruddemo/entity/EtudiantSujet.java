package com.luv2code.springboot.cruddemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EtudiantSujet implements Serializable{

	@Column(name= "etudiantId")
	int etudiantId;
	
	
	@Column(name= "sujetId")
	int sujetId;
	
}
