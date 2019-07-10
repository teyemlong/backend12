/**
 * 
 */
package org.sid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Robert
 *
 */
/*
Si on en arrive à cette exception, c'est qu'il y a eu une erreur interne
Si la requête était mal formée, elle aurait déclenché 400 BAD_REQUEST automatiquement
**/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactBadRequestException extends RuntimeException {
	
	public ContactBadRequestException(String message) {
        super(message);
    }

}
