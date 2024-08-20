package be.vdab.movies.films;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmNietGevondenException extends RuntimeException{
    FilmNietGevondenException(){
        super("Film bestaat niet");
    }
}