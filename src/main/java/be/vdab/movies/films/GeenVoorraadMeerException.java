package be.vdab.movies.films;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
class GeenVoorraadMeerException extends RuntimeException {
    GeenVoorraadMeerException(){
        super("Geen voorraad meer");
    }
}
