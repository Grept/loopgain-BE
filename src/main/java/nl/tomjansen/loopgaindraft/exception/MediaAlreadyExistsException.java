package nl.tomjansen.loopgaindraft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A file with this name already exists.")
public class MediaAlreadyExistsException extends RuntimeException{
}
