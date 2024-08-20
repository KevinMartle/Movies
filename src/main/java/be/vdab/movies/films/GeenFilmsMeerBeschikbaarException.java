package be.vdab.movies.films;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GeenFilmsMeerBeschikbaarException extends RuntimeException{
    GeenFilmsMeerBeschikbaarException(){
        super("Er zijn geen films meer in stock");
    }
}
