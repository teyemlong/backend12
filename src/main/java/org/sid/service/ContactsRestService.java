/**
 * 
 */
package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.dao.ContactsRepository;
import org.sid.entities.Contacts;
import org.sid.exception.ContactNotFoundException;
import org.sid.exception.ImpossibleAjouterContactException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Robert
 *
 */
@RestController
@CrossOrigin("*")
public class ContactsRestService {
	@Autowired
	private ContactsRepository contactsRepository;
	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public List<Contacts> getContacts() {
		List<Contacts> contacts= contactsRepository.findAll();
		 if(contacts.isEmpty()) throw new ContactNotFoundException("Aucun Contact n'est disponible dans la base de données");
		return contacts;
	}
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.GET)
	public Optional<Contacts> getContact(@PathVariable("id")Long id) {
		
		 Optional<Contacts> contact = contactsRepository.findById(id);
		 if(!contact.isPresent())  throw new ContactNotFoundException("Le Contact correspondant à l'id " + id + " n'existe pas");
		return contact;
		
	}
	@RequestMapping(value="/contacts",method=RequestMethod.POST)
	public ResponseEntity<Contacts> saveContact(@RequestBody Contacts contacts) {
		
		Contacts contact= contactsRepository.save(contacts);
		if(contact == null) throw new ImpossibleAjouterContactException("Impossible d'ajouter ce Contact");		
		return new ResponseEntity<Contacts>(contact, HttpStatus.CREATED);
		
	}
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		contactsRepository.deleteById(id);
		return true;
				
		
	}
	
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.PUT)
	public Contacts EditContact(@PathVariable Long id,@RequestBody Contacts c) {
		c.setId(id);
		return contactsRepository.save(c);		
		
	}
	
	@RequestMapping(value="/chercherContacts",method=RequestMethod.GET)
	public Page<Contacts> chercherContactsParMC(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return contactsRepository.chercherParMC("%"+mc+"%", new PageRequest(page, size) );
	}

}
