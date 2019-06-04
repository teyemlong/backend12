/**
 * 
 */
package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.dao.ContactsRepository;
import org.sid.entities.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
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
	public List<Contacts> getContacts(){
		return contactsRepository.findAll();
	}
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.GET)
	public Optional<Contacts> getContact(@PathVariable("id")Long id){
		return contactsRepository.findById(id);
		
	}
	@RequestMapping(value="/contacts",method=RequestMethod.POST)
	public Contacts saveContact(@RequestBody Contacts contacts){
		return contactsRepository.save(contacts);
		
	}
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		contactsRepository.deleteById(id);
		return true;
				
		
	}
	
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.PUT)
	public Contacts EditContact(@PathVariable Long id,@RequestBody Contacts c){
		c.setId(id);
		return contactsRepository.save(c);
		
	}
	
	@RequestMapping(value="/chercherContacts",method=RequestMethod.GET)
	public Page<Contacts> chercherContactsParMC(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		return contactsRepository.chercherParMC("%"+mc+"%", new PageRequest(page, size) );
	}

}
