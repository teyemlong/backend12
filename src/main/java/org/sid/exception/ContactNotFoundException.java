/**
 * 
 */
package org.sid.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author Robert
 *
 */

/*
Si on en arrive à cette exception, c'est qu'il y a eu une erreur interne
Si la requête était mal formée, elle aurait déclenché 404 NOT FOUND automatiquement
**/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException  extends RuntimeException{
	
	 public ContactNotFoundException(String message) {
	        super(message);
	    }

}
