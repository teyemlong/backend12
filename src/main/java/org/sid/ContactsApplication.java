package org.sid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.sid.dao.ContactsRepository;
import org.sid.entities.Contacts;
//import org.sid.dao.EtudiantRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ContactsApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx=SpringApplication.run(ContactsApplication.class, args);
		ContactsRepository contactsRepository=ctx.getBean(ContactsRepository.class);
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		contactsRepository.save(new Contacts("Teyemlong", "Robert", df.parse("1983-11-12"), "teyemlong@gmail.com", 58135729, "tof.png"));
		contactsRepository.save(new Contacts("Youness", "Ezzarouila", df.parse("2018-11-12"), "sobajoo@gmail.com", 4521540, "tof.png"));
		contactsRepository.save(new Contacts("B", "A", df.parse("1983-11-12"), "teyemlong@yahoo.fr", 58135729, "tof.png"));
		
		List<Contacts> contacts=contactsRepository.findAll();
		contacts.forEach(s->System.out.println(s.getNom()));
		contacts.forEach(s->System.out.println(s.getPrenom()));
		contacts.forEach(s->System.out.println(s.getEmail()));
		contacts.forEach(s->System.out.println(s.getDateNaissance()));
	}
}
