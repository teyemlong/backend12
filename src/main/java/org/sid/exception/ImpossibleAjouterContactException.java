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
Si la requête était mal formée, elle aurait déclenché 500 INTERNAL_SERVER_ERROR automatiquement
**/
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImpossibleAjouterContactException extends RuntimeException{
	
	public ImpossibleAjouterContactException(String message) {
        super(message);
    }

}
