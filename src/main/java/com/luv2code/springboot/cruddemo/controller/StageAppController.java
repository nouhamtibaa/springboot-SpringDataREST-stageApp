package com.luv2code.springboot.cruddemo.controller;

import java.io.ByteArrayOutputStream;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luv2code.springboot.cruddemo.entity.Admin;
import com.luv2code.springboot.cruddemo.entity.EmailMessage;
import com.luv2code.springboot.cruddemo.entity.Etudiant;
import com.luv2code.springboot.cruddemo.entity.Etudiant2;
import com.luv2code.springboot.cruddemo.entity.LoginDto;
import com.luv2code.springboot.cruddemo.entity.Sujet;
import com.luv2code.springboot.cruddemo.jpa.AdminRepository;
import com.luv2code.springboot.cruddemo.jpa.EtudiantRepository;
import com.luv2code.springboot.cruddemo.jpa.EtudiantRepository2;
import com.luv2code.springboot.cruddemo.jpa.SujetRepository;
import com.luv2code.springboot.cruddemo.jpa.SujetRepository2;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RequestMethod;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StageAppController {
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	SujetRepository sujetRepository;
	
	@Autowired
	EtudiantRepository2 etudiantRepository2;
	
	@Autowired
	SujetRepository2 sujetRepository2;
	
	@Autowired
	AdminRepository adminRepository;
	
	
	@PostMapping("/upload")
	public @ResponseBody Etudiant createEtudiant(@RequestParam("imageFile") MultipartFile file, Etudiant theEtudiant, @RequestParam("nom") String nom , 
			 @RequestParam("nom") String prenom,  @RequestParam("email") String email,  @RequestParam("password") String password, @RequestParam("confirm") String confirm) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		
		
		Etudiant CV = new Etudiant (fileName, file.getContentType(),file.getBytes() );
		//Etudiant CV2 = new Etudiant (theEtudiant.getNom(),theEtudiant.getPrenom(), theEtudiant.getEmail(), theEtudiant.getPassword(), fileName, file.getContentType(),file.getBytes() );
	
		
	    CV.setEmail(theEtudiant.getEmail());
		CV.setNom(theEtudiant.getNom());
		CV.setPrenom(theEtudiant.getPrenom());
		CV.setPassword(theEtudiant.getPassword());
		CV.setConfirm(theEtudiant.getConfirm());
		CV.compteur=3;
		
		   Etudiant user = etudiantRepository.findByEmail(email);
	     
		
	        
		  if(!theEtudiant.getPassword().equals(theEtudiant.getConfirm()) ){
				
				throw new RuntimeException("Password non confirm! .");
		         }
		  if(user != null)
		  {throw new RuntimeException("userexist! .");}
			  else 
					etudiantRepository.save(CV);
				
	
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/get")
	                .path(CV.getFile_name())
	                .toUriString();
		
		  
		  return theEtudiant;
	}
	
	
	
	@PostMapping("/verifMail")
	public @ResponseBody boolean verifMail (@RequestParam("email") String email) {
        Etudiant user = etudiantRepository.findByEmail(email);
        if(user != null) {
            throw new RuntimeException("User exist.");
        }
		
		return true;
	}
	
	
	@GetMapping("/get/{etudiantId}")
	
	public ResponseEntity getCVEtudiant(@PathVariable int etudiantId) throws IOException {
		Etudiant etudiantCV = etudiantRepository.findById(etudiantId).orElseThrow(() -> new FileNotFoundException("File not found with id " + etudiantId));
		
		 return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(etudiantCV.getFile_type()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + etudiantCV.getFile_name() +"\"")
	                .body(new ByteArrayResource(etudiantCV.getCV() ) );
	
	}

	
	

	
	

	
	@GetMapping("/api/{etudiantId}")
	public Etudiant getEtudiant(@PathVariable int etudiantId) {
		
		Etudiant result = etudiantRepository.findById(etudiantId).get();
		Etudiant etudiant = new Etudiant (result.getId(), result.getNom(), result.getPrenom(), result.getEmail(), result.getPassword() );
		
		return etudiant ;
	}
	
	
	

	
	

	@GetMapping("/ap") 

	public List<Etudiant> geIdtEtudiantt() {
	
		return  etudiantRepository.findAll();

	}
	
	

	@GetMapping("/ap2") 

	public List<Etudiant2> geIdtEtudiantt2() {
	
		return  etudiantRepository2.findAll();

	}
	
	
	
	
	
	@PostMapping("/postSujet")

	public Sujet addSujet(@RequestBody Sujet leSujet) {
		
		leSujet.setId(0);
		
		sujetRepository.save(leSujet);
		
		return leSujet;
	}
	
	
	

	
	
	@GetMapping("/sujets")
	
	public List<Sujet> findAllSujets() {
		
		return sujetRepository.findAll();
	}

	
	
	
	
	
	
	@DeleteMapping("/sujetSupp/{sujetId}")
	public String deleteSujet(@PathVariable int sujetId) {
		
		Optional<Sujet> tempsujet = sujetRepository.findById(sujetId);
		
		// throw exception if null
		
		if (tempsujet == null) {
			throw new RuntimeException("Employee id not found - " +sujetId);
		}
		
		sujetRepository.deleteById(sujetId);
		
		return "Deleted employee id - " + sujetId;
	}
	
	
	
	
	
	
	
	

	
	
	
	@GetMapping("/sujets/{sujetId}")
	public Sujet getSujet(@PathVariable int sujetId) {
		
		Sujet result = sujetRepository.findById(sujetId).get();
		
		
	/*	
		Sujet theEmployee = null;
		if(result.isPresent()) {
			
		theEmployee = result.get();
		}
		
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + sujetId);
		}
		*/
		return result;
	}
	
	
	
	@PostMapping("/addSujet")

	public Sujet addStudentSujet(@RequestBody Sujet leSujet) {
		
		
		leSujet.setId(0);
		sujetRepository.save(leSujet);
		
		return leSujet;
	}
	
	
	/*
	
	@PostMapping("/uploadEtudiant")

	public @ResponseBody Etudiant addEtudiant( Etudiant theEtudiant, @RequestParam("nom") String nom , 
			 @RequestParam("prenom") String prenom,  @RequestParam("email") String email,  @RequestParam("password") String password ) throws IOException {
		
		
		
		
		
		Etudiant CV = new Etudiant ();
		//Etudiant CV2 = new Etudiant (theEtudiant.getNom(),theEtudiant.getPrenom(), theEtudiant.getEmail(), theEtudiant.getPassword(), fileName, file.getContentType(),file.getBytes() );
	
		
	CV.setEmail(theEtudiant.getEmail());
		CV.setNom(theEtudiant.getNom());
		CV.setPrenom(theEtudiant.getPrenom());
		CV.setPassword(theEtudiant.getPassword());
		
		
		Sujet s = getSujet(10);
		
		CV.addSujet(s);
		
		
		sujetRepository.save(s);
		

		etudiantRepository.save(CV);
	
	return theEtudiant;
		
	}
	
	
	
	*/
	
	
	 @PutMapping("/updateEt/{sujetId}")
	    public ResponseEntity<Sujet> updateSujet(@PathVariable int sujetId,
	                                                   @Valid @RequestBody Sujet sujetDetails) throws ResourceNotFoundException {
	       Sujet sujet = sujetRepository.findById(sujetId)
	                .orElseThrow(() -> new ResourceNotFoundException("Sujet not found for this id :: " + sujetId));
            sujet.setTitre(sujetDetails.getTitre());
	        sujet.setDescription(sujetDetails.getDescription());
	        sujet.setMission(sujetDetails.getMission());
	        sujet.setProfil(sujetDetails.getProfil());
	        final Sujet updatedSujet = sujetRepository.save(sujet);
	        return ResponseEntity.ok(updatedSujet);
	    }
	
	 
	 
	 @PutMapping("/updateEtudiant/{etudiantId}")
	    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable int etudiantId, Etudiant etudiant1) throws ResourceNotFoundException {
	    
		
		 Etudiant etudiant= etudiantRepository.findById(etudiantId)
	                .orElseThrow(() -> new ResourceNotFoundException("Sujet not found for this id :: " + etudiantId));
	     etudiant.setEtat(1);
	   /* etudiant.setNom(sujetDetails.getNom());
	    etudiant.setPrenom(sujetDetails.getPrenom());
	    etudiant.setEmail(sujetDetails.getEmail());
	    etudiant.setPassword(sujetDetails.getPassword());
	    etudiant.setConfirm(sujetDetails.getConfirm());
	    etudiant.setCV(sujetDetails.getCV());*/
	    final Etudiant updatedSujet = etudiantRepository.save(etudiant);
	        return ResponseEntity.ok(updatedSujet);
	    }
	 
	
	@PostMapping("/uploadE")

	public @ResponseBody Etudiant addEtudiantCV( Etudiant theEtudiant, @RequestParam("nom") String nom , 
			 @RequestParam("prenom") String prenom,  @RequestParam("email") String email,  @RequestParam("password") String password ) throws IOException {
		
		
		
		
		
		Etudiant CV = new Etudiant ();
		//Etudiant CV2 = new Etudiant (theEtudiant.getNom(),theEtudiant.getPrenom(), theEtudiant.getEmail(), theEtudiant.getPassword(), fileName, file.getContentType(),file.getBytes() );
	
		
	CV.setEmail(theEtudiant.getEmail());
		CV.setNom(theEtudiant.getNom());
		CV.setPrenom(theEtudiant.getPrenom());
		CV.setPassword(theEtudiant.getPassword());
		
		
		Sujet s = getSujet(10);
		
		CV.addSujet(s);
		
		
		sujetRepository.save(s);
		
		
		////Etudiant t = getEtudiant(57);
	//	t.addSujet(s);
		//sujetRepository.save(s);
		//etudiantRepository.save(t);
		
		//etudiantRepository.save(theEtudiant);
	  
		etudiantRepository.save(CV);
	
	return theEtudiant;
		
	}
	
	
	/*
	  public Boolean loginEt(LoginDto loginDto) {
	        Etudiant user = etudiantRepository.findByNom(loginDto.getNom());
	        if(user == null) {
	            throw new RuntimeException("User does not exist.");
	        }
	        if(!user.getPassword().equals(loginDto.getPassword())){
	            throw new RuntimeException("Password mismatch.");
	        }
	        return true;

	    }

	
	  @PostMapping("/verif")
	    public Boolean login(@RequestBody LoginDto loginDto) {
	        return loginEt(loginDto);
	    }
	
	*/
	
	
	
	
	
	 public Boolean login(String email , String password) {
	        Etudiant user = etudiantRepository.findByEmail(email);
	        if(user == null) {
	            throw new RuntimeException("User does not exist.");
	        }
	        if(!user.getPassword().equals(password)){
	            throw new RuntimeException("Password mismatch.");
	        }
	        return true;

	    }
	
	 
	  @PostMapping("/verif")
	    public @ResponseBody Boolean loginet(@RequestParam("email") String email, @RequestParam("password") String password ) {
	        return login(email, password);
	    }
	 
	 
	  
	  
	@PostMapping("/postuler")
	
	 public void postuler( @RequestParam("email")  String email, @RequestParam("id") int id ) throws IOException {
		   
			Etudiant user = etudiantRepository.findByEmail(email);
		
			if(user.compteur > 0) {
			user.compteur--;
	     
			Sujet s = getSujet(id);
		
		    user.addSujet(s);
		    
			sujetRepository.save(s);
			
			user.addSujet(s);
			}
		
			else   {throw new RuntimeException("Password mismatch.");}
		}
		
	  
	@GetMapping("/getEtudiantByEmail/{email}")
	public Etudiant getEtudiantByEmail (@PathVariable String email) {
		Etudiant user = etudiantRepository.findByEmail(email);
		return user;
		
	}
	
	
	
	
	@GetMapping("/conv")

	public String convert(int id) {
		Sujet s = sujetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sujet not found for this id :: " + id));		
		
		String nomSujet = s.getTitre();
		
		return nomSujet;
		}
	  
	  

	
	@Value("${gmail.username}")
	private String username;
	@Value("${gmail.password}")
	private String password;
	
	
	
	@PostMapping("/sendEmail")
	public void sendEmail(@RequestBody EmailMessage emailmessage) throws AddressException, MessagingException, IOException {
		sendmail(emailmessage);
		//return "Email sent successfully";
	}
	
	
private void sendmail(EmailMessage emailmessage) throws AddressException, MessagingException, IOException {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailmessage.getTo_address()));
		msg.setSubject(emailmessage.getSubject());
		msg.setContent(emailmessage.getBody(), "text/html");
		msg.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(emailmessage.getBody(), "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
	/*	MimeBodyPart attachPart = new MimeBodyPart();

		attachPart.attachFile("C:\\talk2amareswaran-downloads\\mysql2.png"); */

	//	multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		// sends the e-mail
		Transport.send(msg);
		
	}
	
	
	
	


public Boolean loginAdmin(String username , String password) {
    Admin user = adminRepository.findByUsername(username);
       if(user == null) {
           throw new RuntimeException("User does not exist.");
       }
       if(!user.getPassword().equals(password)){
           throw new RuntimeException("Password mismatch.");
       }
       return true;

   }

 @PostMapping("/verifAdmin")
   public @ResponseBody Boolean loginAd(@RequestParam("username") String username, @RequestParam("password") String password ) {
       return loginAdmin(username, password);
   }

	
	
	
	
	
	
	
	
	
	
	
	
	
}
	

	
	
	
