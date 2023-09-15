package it.grantatlas.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateDataException extends RuntimeException{

    public DuplicateDataException(String message){
        super(message);
    }
}
